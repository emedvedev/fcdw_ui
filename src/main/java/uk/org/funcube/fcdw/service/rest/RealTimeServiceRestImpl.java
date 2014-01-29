// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.service.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import uk.org.funcube.fcdw.server.shared.RealTimeInfo;
import uk.org.funcube.fcdw.server.shared.SoftwareState;
import uk.org.funcube.fcdw.server.shared.StringPair;
import uk.org.funcube.fcdw.server.shared.ValMinMax;
import uk.org.funcube.fcdw.web.controller.RealtimeController;


@Controller
@RequestMapping(value = "/ui/realtime")
public class RealTimeServiceRestImpl extends AbstractService {

	private static final String PA_MILLI_WATT_FORMAT = "%4.1f mW";
	private static final String MILLI_VOLT_FORMAT = "%4d mV";
	private static final String TEMPERATURE_FORMAT = "%4d C";
	private static final String SOL_TEMPERATURE_FORMAT = "%5.1f C";
	private static final String ANTS_TEMPERATURE_FORMAT = "%5.1f C";
	private static final String PA_TEMPERATURE_FORMAT = "%4.1f C";
	private static final String PA_MILLI_AMPS_FORMAT = "%4.1f mA";
	private static final String MILLI_AMPS_FORMAT = "%4d mA";
	private static final String N_A = "N/A";
	private static final String UNDEPLOYED = "Undeployed";
	private static final String DEPLOYED = "Deployed";

	private static final Logger LOGGER = Logger.getLogger(RealtimeController.class.getName());

	@Autowired
	private HexFrameDao hexFrameDao;
	
	@Autowired
	private MinMaxDao minMaxDao;

	private Long satelliteId;
	
	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RealTimeInfo getLatest(
			@PathVariable(value = "satelliteId") Long theSatelliteId) {
		
		this.satelliteId = theSatelliteId;
		
		List<MinMax> minMaxValues = minMaxDao.findBySatelliteId(satelliteId);

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("realtime");

		final HexFrame latestFrame = hexFrameDao.getLatest(satelliteId);

		if (latestFrame == null) {
			return new RealTimeInfo();
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

		List<ValMinMax> epsValues = new ArrayList<ValMinMax>();
		List<ValMinMax> asibValues = new ArrayList<ValMinMax>();
		List<ValMinMax> rfValues = new ArrayList<ValMinMax>();
		List<ValMinMax> paValues = new ArrayList<ValMinMax>();
		List<ValMinMax> antsValues = new ArrayList<ValMinMax>();
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
		epsValues.add(new ValMinMax("Boost Converter Temp X", String.format(TEMPERATURE_FORMAT, unWrap(eps.getC9(), 20)), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(9).getMinimum());
		maxValue = format("%4d", minMaxValues.get(9).getMaximum());
		epsValues.add(new ValMinMax("Boost Converter Temp Y", String.format(TEMPERATURE_FORMAT, unWrap(eps.getC10(), 20)), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(10).getMinimum());
		maxValue = format("%4d", minMaxValues.get(10).getMaximum());
		epsValues.add(new ValMinMax("Boost Converter Temp Z", String.format(TEMPERATURE_FORMAT, unWrap(eps.getC11(), 20)), minValue, maxValue));
		
		minValue = format("%4d", minMaxValues.get(11).getMinimum());
		maxValue = format("%4d", minMaxValues.get(11).getMaximum());
		epsValues.add(new ValMinMax("Battery Temp", String.format(TEMPERATURE_FORMAT, unWrap(eps.getC12(),10)), minValue, maxValue));
		
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

		minValue = format("%4d", minMaxValues.get(24).getMinimum());
		maxValue = format("%4d", minMaxValues.get(24).getMaximum());
		asibValues.add(new ValMinMax("3.3 Bus Current", String.format(MILLI_AMPS_FORMAT, eps.getC25()), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(25).getMinimum() * 6);
		maxValue = format("%4d", minMaxValues.get(25).getMaximum() * 6);
		asibValues.add(new ValMinMax("5.0 Bus voltage", String.format(MILLI_VOLT_FORMAT, (int) (6.0 * eps.getC26())), minValue, maxValue));

		RF rf = realTime.getRF();

		minValue = format("%4d", minMaxValues.get(31).getMinimum() * 6);
		maxValue = format("%4d", minMaxValues.get(31).getMaximum() * 6);
		rfValues.add(new ValMinMax("Receiver Doppler", String.format("%4d", rf.getC1()), N_A, N_A));

		minValue = format("%4d", minMaxValues.get(32).getMinimum() * 6);
		maxValue = format("%4d", minMaxValues.get(32).getMaximum() * 6);
		rfValues.add(new ValMinMax("Receiver RSSI", String.format("%4d", rf.getC2()), N_A, N_A));
		
		minValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(33).getMinimum(), -0.857, 193.672);		
		maxValue = scaleOffsetAndFormat("%5.1f", minMaxValues.get(33).getMaximum(), -0.857, 193.672);
		rfValues.add(new ValMinMax("Temperature", String.format(SOL_TEMPERATURE_FORMAT, scaleAndOffset(rf.getC3(), -0.857, 193.672)), maxValue, minValue));


		minValue = format("%4d", minMaxValues.get(34).getMinimum());
		maxValue = format("%4d", minMaxValues.get(34).getMaximum());
		rfValues.add(new ValMinMax("Receive Current", String.format(MILLI_AMPS_FORMAT, rf.getC4()), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(35).getMinimum());
		maxValue = format("%4d", minMaxValues.get(35).getMaximum());
		rfValues.add(new ValMinMax("Transmit Current 3.3V bus", String.format(MILLI_AMPS_FORMAT, rf.getC5()), minValue, maxValue));

		minValue = format("%4d", minMaxValues.get(36).getMinimum());
		maxValue = format("%4d", minMaxValues.get(36).getMaximum());
		rfValues.add(new ValMinMax("Transmit Current 5.0V bus", String.format(MILLI_AMPS_FORMAT, rf.getC6()), minValue, maxValue));

		paValues.add(new ValMinMax("Forward Power", String.format(PA_MILLI_WATT_FORMAT, getPaPower(rf.getC8())), N_A, N_A));
		paValues.add(new ValMinMax("Reverse Power", String.format(PA_MILLI_WATT_FORMAT, getPaPower(rf.getC7())), N_A, N_A));

		minValue = format("%4d", (long)getPaTemp(minMaxValues.get(39).getMinimum()));
		maxValue = format("%4d", (long)getPaTemp(minMaxValues.get(39).getMaximum()));
		
		paValues.add(new ValMinMax("Device Temperature", String.format(PA_TEMPERATURE_FORMAT, getPaTemp(rf.getC9().intValue())), maxValue, minValue));
		
		minValue = format("%4d", (long)getPaCurrent(minMaxValues.get(40).getMinimum()));
		maxValue = format("%4d", (long)getPaCurrent(minMaxValues.get(40).getMaximum()));
		paValues.add(new ValMinMax("Bus Current", String.format(PA_MILLI_AMPS_FORMAT, getPaCurrent(rf.getC10())), minValue, maxValue));

		Antenna antenna = realTime.getAntenna();

		maxValue = String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(minMaxValues.get(41).getMinimum().intValue()));
		minValue = String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(minMaxValues.get(41).getMaximum().intValue()));
		antsValues.add(new ValMinMax("Antenna Temp 0", String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(antenna.getAntennaTemp0()
				.intValue())), minValue, maxValue));
		maxValue = String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(minMaxValues.get(42).getMinimum().intValue()));
		minValue = String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(minMaxValues.get(42).getMaximum().intValue()));
		antsValues.add(new ValMinMax("Antenna Temp 1", String.format(ANTS_TEMPERATURE_FORMAT, getAntsTemp(antenna.getAntennaTemp1()
				.intValue())), minValue, maxValue));
		
		antsValues.add(new ValMinMax("Antenna Deployment VHF-A", (antenna.isAntennaDeployment0()) ? DEPLOYED : UNDEPLOYED, "", ""));
		antsValues.add(new ValMinMax("Antenna Deployment UHF-A", (antenna.isAntennaDeployment1()) ? DEPLOYED : UNDEPLOYED, "", ""));
		antsValues.add(new ValMinMax("Antenna Deployment VHF-B", (antenna.isAntennaDeployment2()) ? DEPLOYED : UNDEPLOYED, "", ""));
		antsValues.add(new ValMinMax("Antenna Deployment UHF-B", (antenna.isAntennaDeployment3()) ? DEPLOYED : UNDEPLOYED, "", ""));

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
		
		RealTimeInfo realtimeInfo 
			= new RealTimeInfo(realTime.getSequenceNumber(), 
					SDTF.format(createdDate),
					epsValues, asibValues, rfValues, paValues, antsValues, swValues,
					siteList);
		
		return realtimeInfo;
		
	}

	private Long unWrap(Long value, int i) {
		
		if (value > 255 - i) {
			return value - 255;
		}
		
		return value;
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
}
