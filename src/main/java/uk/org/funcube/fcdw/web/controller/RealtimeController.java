// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.dao.HexFrameDao;
import uk.org.funcube.fcdw.server.dao.MinMaxDao;
import uk.org.funcube.fcdw.server.model.HexFrame;
import uk.org.funcube.fcdw.server.model.MinMax;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.Antenna;
import uk.org.funcube.fcdw.server.shared.DTMF;
import uk.org.funcube.fcdw.server.shared.EPS;
import uk.org.funcube.fcdw.server.shared.RF;
import uk.org.funcube.fcdw.server.shared.RealTime;
import uk.org.funcube.fcdw.server.shared.SoftwareState;
import uk.org.funcube.fcdw.server.shared.StringPair;
import uk.org.funcube.fcdw.server.shared.ValMinMax;

@Controller
@RequestMapping("realtime")
public class RealtimeController extends AbstractService {

	private static final String PA_MILLI_WATT_FORMAT = "%4.1f mW";
	private static final String MILLI_VOLT_FORMAT = "%4d mV";
	private static final String TEMPERATURE_FORMAT = "%4d &deg;C";
	private static final String SOL_TEMPERATURE_FORMAT = "%5.1f &deg;C";
	private static final String ANTS_TEMPERATURE_FORMAT = "%5.1f &deg;C";
	private static final String PA_TEMPERATURE_FORMAT = "%4.1f &deg;C";
	private static final String PA_MILLI_AMPS_FORMAT = "%4.1f mA";
	private static final String MILLI_AMPS_FORMAT = "%4d mA";
	private static final String N_A = "N/A";
	private static final String UNDEPLOYED = "Undeployed";
	private static final String DEPLOYED = "Deployed";

	private double minPV1 = 0.0;
	private double maxPV1 = 0.0;

	private static final Logger LOGGER = Logger.getLogger(RealtimeController.class.getName());

	@Autowired
	private HexFrameDao hexFrameDao;
	
	@Autowired
	private MinMaxDao minMaxDao;
	
	
	private Long satelliteId;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView realtime(@QueryParam(value = "satelliteId") Long satelliteId) {
		
		List<MinMax> minMaxValues = minMaxDao.findBySatelliteId(satelliteId);

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("realtime");

		this.satelliteId = satelliteId;
		final HexFrame latestFrame = hexFrameDao.getLatest(satelliteId);

		if (latestFrame == null) {
			mv.addObject("satelliteId", satelliteId);
			return mv;
		}
		
		final Long hfCount = hexFrameDao.countAll(satelliteId);
		final String packetCount = String.format("%d (%5.1fMB)", hfCount, (double)hfCount * 2048 / 8 / 1000000);
		
		String latitude = "";
		
		final double latitudeValue = Double.parseDouble(latestFrame.getLatitude());
		if (latitudeValue > 0.0) {
			latitude = String.format("%5.1f N", latitudeValue);
		} else {
			latitude = String.format("%5.1f S", Math.abs(latitudeValue));
		}
		
		String longitude = "";
		final double longitudeValue = Double.parseDouble(latestFrame.getLongitude());
		if (longitudeValue > 180.0) {
			longitude = String.format("%5.1f W", 360.0 - longitudeValue);
		} else {
			longitude = String.format("%5.1f E", longitudeValue);
		}

		final Date createdDate = latestFrame.getCreatedDate();
		final String hexString = latestFrame.getHexString();

		final int frameId = Integer.parseInt(hexString.substring(0, 2), 16);
		final int sensorId = frameId % 2;

		final String binaryString = convertHexBytePairToBinary(hexString.substring(2, hexString.length()));

		int frameType = frameId & 63;

		RealTime realTime = new RealTime(satelliteId.intValue(), frameType, sensorId, createdDate, binaryString);

		Set<UserEntity> users = latestFrame.getUsers();

		List<String> siteList = new ArrayList<String>();

		for (UserEntity user : users) {
			siteList.add(user.getSiteId());
		}

		calculateMinMax(0, createdDate);

		List<ValMinMax> epsValues = new ArrayList<ValMinMax>();
		List<ValMinMax> asibValues = new ArrayList<ValMinMax>();
		List<ValMinMax> rfValues = new ArrayList<ValMinMax>();
		List<ValMinMax> paValues = new ArrayList<ValMinMax>();
		List<StringPair> antsValues = new ArrayList<StringPair>();
		List<StringPair> swValues = new ArrayList<StringPair>();

		EPS eps = realTime.getEps();
		
		String minValue = "";
		String maxValue = "";
		
		minValue = format("%4d", minMaxValues.get(0).getMinimum());
		maxValue = format("%4d", minMaxValues.get(0).getMaximum());
		epsValues.add(new ValMinMax("Solar Panel Voltage X", String.format(MILLI_VOLT_FORMAT, eps.getC1()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(1).getMinimum());
		maxValue = format("%4d", minMaxValues.get(1).getMaximum());
		epsValues.add(new ValMinMax("Solar Panel Voltage Y", String.format(MILLI_VOLT_FORMAT, eps.getC2()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(2).getMinimum());
		maxValue = format("%4d", minMaxValues.get(2).getMaximum());
		epsValues.add(new ValMinMax("Solar Panel Voltage Z", String.format(MILLI_VOLT_FORMAT, eps.getC3()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(3).getMinimum());
		maxValue = format("%4d", minMaxValues.get(3).getMaximum());
		epsValues.add(new ValMinMax("Total Photo Current", String.format(MILLI_AMPS_FORMAT, eps.getC4()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(4).getMinimum());
		maxValue = format("%4d", minMaxValues.get(4).getMaximum());
		epsValues.add(new ValMinMax("Battery Voltage", String.format(MILLI_VOLT_FORMAT, eps.getC5()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(5).getMinimum());
		maxValue = format("%4d", minMaxValues.get(5).getMaximum());
		epsValues.add(new ValMinMax("Total System Current", String.format(MILLI_AMPS_FORMAT, eps.getC6()), minValue, maxValue));
		
		epsValues.add(new ValMinMax("Reboot Count", String.format("%4d", eps.getC7()), N_A, N_A));
		epsValues.add(new ValMinMax("EPS Software Errors", String.format("%4d", eps.getC8()), N_A, N_A));
		
		minValue = format("%4d", minMaxValues.get(8).getMinimum());
		maxValue = format("%4d", minMaxValues.get(8).getMaximum());
		epsValues.add(new ValMinMax("Boost Converter Temp X", String.format(TEMPERATURE_FORMAT, eps.getC9()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(9).getMinimum());
		maxValue = format("%4d", minMaxValues.get(9).getMaximum());
		epsValues.add(new ValMinMax("Boost Converter Temp Y", String.format(TEMPERATURE_FORMAT, eps.getC10()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(10).getMinimum());
		maxValue = format("%4d", minMaxValues.get(10).getMaximum());
		epsValues.add(new ValMinMax("Boost Converter Temp Z", String.format(TEMPERATURE_FORMAT, eps.getC11()), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(11).getMinimum());
		maxValue = format("%4d", minMaxValues.get(11).getMaximum());
		epsValues.add(new ValMinMax("Battery Temp", String.format(TEMPERATURE_FORMAT, eps.getC12()), minValue, maxValue));
		
		epsValues.add(new ValMinMax("Latch Up Count 5v1", String.format("%4d", eps.getC13()), N_A, N_A));
		epsValues.add(new ValMinMax("Latch Up Count 3.3v1", String.format("%4d", eps.getC14()), N_A, N_A));
		epsValues.add(new ValMinMax("Reset Cause", String.format("%4d", eps.getC15()), N_A, N_A));
		epsValues.add(new ValMinMax("Power Point Tracking Mode", String.format("%4d", eps.getC16()), N_A, N_A));

		asibValues.add(new ValMinMax("Sun Sensor X+", String.format("%4.2f", SOL_ILLUMINATION[eps.getC17().intValue()]), N_A, N_A));
		asibValues.add(new ValMinMax("Sun Sensor Y+", String.format("%4.2f", SOL_ILLUMINATION[eps.getC18().intValue()]), N_A, N_A));
		asibValues.add(new ValMinMax("Sun Sensor Z+", String.format("%4.2f", SOL_ILLUMINATION[eps.getC19().intValue()]), N_A, N_A));
		
		minValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(19).getMinimum(), -0.2073, 158.239);		
		maxValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(19).getMaximum(), -0.2073, 158.239);
		asibValues.add(new ValMinMax("Solar Panel Temp X+", String.format(SOL_TEMPERATURE_FORMAT,
				scaleAndOffset(eps.getC20(), -0.2073, 158.239)), maxValue, minValue));
		
		minValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(20).getMinimum(), -0.2083, 159.227);		
		maxValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(20).getMaximum(), -0.2083, 159.227);
		asibValues.add(new ValMinMax("Solar Panel Temp X-", String.format(SOL_TEMPERATURE_FORMAT,
				scaleAndOffset(eps.getC21(), -0.2083, 159.227)), maxValue, minValue));
		
		minValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(21).getMinimum(), -0.2076, 158.656);		
		maxValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(21).getMaximum(), -0.2076, 158.656);
		asibValues.add(new ValMinMax("Solar Panel Temp Y+", String.format(SOL_TEMPERATURE_FORMAT,
				scaleAndOffset(eps.getC22(), -0.2076, 158.656)), maxValue, minValue));
		
		minValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(22).getMinimum(), -0.2087, 159.045);		
		maxValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(22).getMaximum(), -0.2087, 159.045);
		asibValues.add(new ValMinMax("Solar Panel Temp Y-", String.format(SOL_TEMPERATURE_FORMAT,
				scaleAndOffset(eps.getC23(), -0.2087, 159.045)), maxValue, minValue));

		minValue = format("%4d", minMaxValues.get(23).getMinimum() * 4);
		maxValue = format("%4d", minMaxValues.get(23).getMaximum() * 4);
		asibValues.add(new ValMinMax("3.3 Bus Voltage", String.format(MILLI_VOLT_FORMAT, (int) (4.0 * eps.getC24())), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(25).getMinimum());
		maxValue = format("%4d", minMaxValues.get(25).getMaximum());
		asibValues.add(new ValMinMax("3.3 Bus Current", String.format(MILLI_AMPS_FORMAT, eps.getC25()), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(25).getMinimum() * 6);
		maxValue = format("%4d", minMaxValues.get(25).getMaximum() * 6);
		asibValues.add(new ValMinMax("5.0 Bus voltage", String.format(MILLI_VOLT_FORMAT, (int) (6.0 * eps.getC26())), minValue, maxValue));

		RF rf = realTime.getRF();

		rfValues.add(new ValMinMax("Receiver Doppler", String.format("%4d kHz", rf.getC1()), N_A, N_A));
		rfValues.add(new ValMinMax("Receiver RSSI", String.format("%4d", rf.getC2()), N_A, N_A));
		
		minValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(28).getMinimum(), -0.857, 193.672);		
		maxValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(28).getMaximum(), -0.857, 193.672);
		rfValues.add(new ValMinMax("Temperature", String.format(SOL_TEMPERATURE_FORMAT, scaleAndOffset(rf.getC3(), -0.857, 193.672)), maxValue, minValue));


		minValue = format("%4d", minMaxValues.get(29).getMinimum());
		maxValue = format("%4d", minMaxValues.get(29).getMaximum());
		rfValues.add(new ValMinMax("Receive Current", String.format(MILLI_AMPS_FORMAT, rf.getC4()), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(30).getMinimum());
		maxValue = format("%4d", minMaxValues.get(30).getMaximum());
		rfValues.add(new ValMinMax("Transmit Current 3.3V bus", String.format(MILLI_AMPS_FORMAT, rf.getC5()), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(31).getMinimum());
		maxValue = format("%4d", minMaxValues.get(31).getMaximum());
		rfValues.add(new ValMinMax("Transmit Current 5.0V bus", String.format(MILLI_AMPS_FORMAT, rf.getC6()), minValue, maxValue));

		paValues.add(new ValMinMax("Forward Power", String.format(PA_MILLI_WATT_FORMAT, getPaPower(rf.getC8())), N_A, N_A));
		paValues.add(new ValMinMax("Reverse Power", String.format(PA_MILLI_WATT_FORMAT, getPaPower(rf.getC7())), N_A, N_A));

		minValue = format("%4d", (long)getPaTemp(minMaxValues.get(32).getMinimum()));
		maxValue = format("%4d", (long)getPaTemp(minMaxValues.get(32).getMaximum()));
		paValues.add(new ValMinMax("Device Temperature", String.format(PA_TEMPERATURE_FORMAT, getPaTemp(rf.getC9().intValue())), maxValue, minValue));
		
		minValue = format("%4d", (long)getPaCurrent(minMaxValues.get(33).getMinimum()));
		maxValue = format("%4d", (long)getPaCurrent(minMaxValues.get(33).getMaximum()));
		paValues.add(new ValMinMax("Bus Current", String.format(PA_MILLI_AMPS_FORMAT, getPaCurrent(rf.getC10())), minValue, maxValue));

		Antenna antenna = realTime.getAntenna();

		antsValues.add(new StringPair("Antenna Temp 0", String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(antenna.getAntennaTemp0()
				.intValue()))));
		antsValues.add(new StringPair("Antenna Temp 1", String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(antenna.getAntennaTemp1()
				.intValue()))));
		antsValues.add(new StringPair("Antenna Deployment VHF-A", (antenna.isAntennaDeployment0()) ? DEPLOYED : UNDEPLOYED));
		antsValues.add(new StringPair("Antenna Deployment UHF-A", (antenna.isAntennaDeployment1()) ? DEPLOYED : UNDEPLOYED));
		antsValues.add(new StringPair("Antenna Deployment VHF-B", (antenna.isAntennaDeployment2()) ? DEPLOYED : UNDEPLOYED));
		antsValues.add(new StringPair("Antenna Deployment UHF-B", (antenna.isAntennaDeployment3()) ? DEPLOYED : UNDEPLOYED));

		DTMF dtmf = realTime.getDTMF();

		SoftwareState softwareState = realTime.getSoftwareState();

		swValues.add(new StringPair("Sequence Number", String.format("%10d", realTime.getSequenceNumber())));
		swValues.add(new StringPair("Command Count", String.format("%10d", dtmf.getCommandCount())));
		swValues.add(new StringPair("Last Command", getLastDTMFCommand((int) dtmf.getLastCommand())));
		swValues.add(new StringPair("Command Success", (softwareState.getC1()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid ASIB", (softwareState.getC2()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid EPS", (softwareState.getC3()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid PA", (softwareState.getC4()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid RF", (softwareState.getC5()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid MSE", (softwareState.getC6()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid ANTS Bus-B", (softwareState.getC7()) ? "Yes" : "No"));
		swValues.add(new StringPair("Data Valid ANTS Bus-A", (softwareState.getC8()) ? "Yes" : "No"));
		swValues.add(new StringPair("In Eclipse Mode", (softwareState.getC9()) ? "Yes" : "No"));
		swValues.add(new StringPair("In Safe Mode", (softwareState.getC10()) ? "Yes" : "No"));
		swValues.add(new StringPair("Hardware ABF", (softwareState.getC11()) ? "On" : "Off"));
		swValues.add(new StringPair("Software ABF", (softwareState.getC12()) ? "On" : "Off"));
		swValues.add(new StringPair("Deployment Wait At Next Boot", (softwareState.getC13()) ? "Yes" : "No"));

		mv.addObject("createdDate", SDTF.format(createdDate));
		mv.addObject("epsValues", epsValues);
		mv.addObject("asibValues", asibValues);
		mv.addObject("rfValues", rfValues);
		mv.addObject("paValues", paValues);
		mv.addObject("antsValues", antsValues);
		mv.addObject("swValues", swValues);
		mv.addObject("siteList", siteList);
		mv.addObject("satelliteId", satelliteId);
		mv.addObject("latitude", latitude);
		mv.addObject("longitude", longitude);
		mv.addObject("packetCount", packetCount);
		mv.addObject("showStatus", new Long(1));
		return mv;
	}

	private String scaleOffsetAndFormat(String format, Long value, double multiplier,
			double offset) {
		if (value == 99999 || value == -99999) {
			return N_A;
		}
		
		double calculatedValue = scaleAndOffset(value, multiplier, offset);
		
		return String.format(format, calculatedValue);
	}

	private String format(String format, Long value) {
		if (value >= 99999 || value <= -99999) {
			return N_A;
		}
		
		return String.format(format, value);
	}

	private void calculateMinMax(int satelliteId, Date createdDate) {
		int orbitSeconds = 104 * 60 * 1000;
		final List<HexFrame> orbitFrames = hexFrameDao.getOrbitFrames(satelliteId, new Date(createdDate.getTime() - orbitSeconds));
		minPV1 = 0.0;
		maxPV1 = 0.0;

	}

	public void setHexFrameDao(HexFrameDao hexFrameDao) {
		this.hexFrameDao = hexFrameDao;
	}

	public void setMinMaxDao(MinMaxDao minMaxDao) {
		this.minMaxDao = minMaxDao;
	}

}
