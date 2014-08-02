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
import uk.org.funcube.fcdw.server.dao.RealTimeDao;
import uk.org.funcube.fcdw.server.dao.SatelliteStatusDao;
import uk.org.funcube.fcdw.server.model.HexFrame;
import uk.org.funcube.fcdw.server.model.MinMax;
import uk.org.funcube.fcdw.server.model.RealTimeEntity;
import uk.org.funcube.fcdw.server.model.SatelliteStatus;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.RealTime;
import uk.org.funcube.fcdw.server.shared.RealTimeFC2;
import uk.org.funcube.fcdw.server.shared.RealTimeFC2Info;
import uk.org.funcube.fcdw.server.shared.RealTimeInfo;
import uk.org.funcube.fcdw.server.shared.SharedInfo;
import uk.org.funcube.fcdw.server.shared.StringPair;
import uk.org.funcube.fcdw.server.shared.ValMinMax;
import uk.org.funcube.fcdw.web.controller.RealtimeController;


@Controller
@RequestMapping(value = "/ui/realtimefc2")
public class RealTimeFC2ServiceRestImpl extends AbstractService {

	private static final String PA_MILLI_WATT_FORMAT = "%4.1f mW";
	private static final String MILLI_VOLT_FORMAT = "%s mV";
	private static final String VOLTS_FORMAT = "%s V";
	private static final String TEMPERATURE_FORMAT = "%s C";
	private static final String SOL_TEMPERATURE_FORMAT = "%5.1f C";
	private static final String ANTS_TEMPERATURE_FORMAT = "%5.1f C";
	private static final String PA_TEMPERATURE_FORMAT = "%4.1f C";
	private static final String PA_MILLI_AMPS_FORMAT = "%4.1f mA";
	private static final String MILLI_AMPS_FORMAT = "%s mA";
	private static final String N_A = "N/A";
	private static final String UNDEPLOYED = "Undeployed";
	private static final String DEPLOYED = "Deployed";

	private static final Logger LOGGER = Logger.getLogger(RealtimeController.class.getName());

	@Autowired
	private HexFrameDao hexFrameDao;
	
	@Autowired
	private RealTimeDao realTimeDao;
	
	@Autowired
	private SatelliteStatusDao satelliteStatusDao;
	
	@Autowired
	private MinMaxDao minMaxDao;

	private Long satelliteId;
	
	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SharedInfo getLatest(
			@PathVariable(value = "satelliteId") Long theSatelliteId) {
		
		this.satelliteId = theSatelliteId;
		
		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		final HexFrame latestHexFrame = hexFrameDao.getLatest(satelliteId);
		
		final RealTimeEntity realTimeEntity = realTimeDao.getLastEntry(satelliteId);

		if (latestHexFrame == null) {
			return new RealTimeFC2Info();
		}
		
		RealTimeFC2 realTimeFC2 = new RealTimeFC2(realTimeEntity);
		
		final Long hfCount = hexFrameDao.countAll(satelliteId);
		final String packetCount = String.format("%d (%5.1fMB)", hfCount, (double)hfCount * 2048 / 8 / 1000000);
		
		List<SatelliteStatus> satelliteStatuses = satelliteStatusDao.findBySatelliteId(satelliteId);
		
		final Date createdDate = latestHexFrame.getCreatedDate();
		final Date minmaxResetDate = minMaxDao.findMaxRefDate(satelliteId);
		final String satelliteMode = satelliteStatuses.get(0).getMode();
		final String transponderState = satelliteStatuses.get(0).getTransponderState();
		
		String latitude = latestHexFrame.getLatitude();
		
		final double latitudeValue = Double.parseDouble((latitude != null) ? latitude : "0");
		if (latitudeValue > 0.0) {
			latitude = String.format("%5.1f N", latitudeValue);
		} else {
			latitude = String.format("%5.1f S", Math.abs(latitudeValue));
		}
		
		String longitude = latestHexFrame.getLongitude();
		
		final double longitudeValue = Double.parseDouble((longitude != null) ? longitude : "0");
		if (longitudeValue > 180.0) {
			longitude = String.format("%5.1f W", 360.0 - longitudeValue);
		} else {
			longitude = String.format("%5.1f E", longitudeValue);
		}

		Set<UserEntity> users = latestHexFrame.getUsers();

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
		
		epsValues.add(new ValMinMax("Solar Array +Y Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent1Plus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array +Y Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent1Minus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Y Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent2Plus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Y Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent2Minus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Z Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent3Plus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Z Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent3Minus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array -Y Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent4Plus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array -Y Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent4Minus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array X Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent5Plus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array X Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent5Minus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array +X Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent6Plus()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array +X Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent6Minus()), N_A, N_A));
		
		epsValues.add(new ValMinMax("Battery 0 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery0CurrentString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 0 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery0VoltsString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 0 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery0TemperatureString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 1 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery1CurrentString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 1 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery1VoltsString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 1 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery1TemperatureString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 2 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery2CurrentString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 2 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery2VoltsString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 2 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery2TemperatureString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery Heater", N_A, N_A, N_A));
		
		SharedInfo realtimeInfo 
			= new RealTimeFC2Info(realTimeEntity.getSequenceNumber(), 
					SDTF.format(createdDate),
					epsValues, asibValues, rfValues, paValues, swValues,
					siteList, SDTF.format(minmaxResetDate),
					latitude, longitude, packetCount, satelliteMode, transponderState);
		
		return realtimeInfo;
		
	}

	private Long unWrap(Long value) {
		
		if (value >= 128) {
			return ~value ^ 255;
		}
		else {
			return value;
		}
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
