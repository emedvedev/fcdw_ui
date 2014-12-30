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

import uk.org.funcube.fcdw.server.dao.HexFrameDao;
import uk.org.funcube.fcdw.server.dao.MinMaxDao;
import uk.org.funcube.fcdw.server.dao.RealTimeDao;
import uk.org.funcube.fcdw.server.dao.SatelliteStatusDao;
import uk.org.funcube.fcdw.server.model.HexFrame;
import uk.org.funcube.fcdw.server.model.RealTimeEntity;
import uk.org.funcube.fcdw.server.model.SatelliteStatus;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.RealTimeFC2;
import uk.org.funcube.fcdw.server.shared.RealTimeFC2Info;
import uk.org.funcube.fcdw.server.shared.SharedInfo;
import uk.org.funcube.fcdw.server.shared.StringPair;
import uk.org.funcube.fcdw.server.shared.ValMinMax;
import uk.org.funcube.fcdw.web.controller.RealtimeController;


@Controller
@RequestMapping(value = "/ui/realtimefc2")
public class RealTimeFC2ServiceRestImpl extends AbstractService {

	private static final String PA_MILLI_WATT_FORMAT = "%s mW";
	private static final String VOLTS_FORMAT = "%s V";
	private static final String TEMPERATURE_FORMAT = "%s C";
	private static final String PA_TEMPERATURE_FORMAT = "%s C";
	private static final String PA_MILLI_AMPS_FORMAT = "%s mA";
	private static final String MILLI_AMPS_FORMAT = "%s mA";
	private static final String N_A = "N/A";
	private static final String MAG_FLUX = "%s nT";

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

		final HexFrame latestHexFrame = hexFrameDao.getLatestFC2();
		
		RealTimeEntity realTimeEntity;
		
		if (satelliteId != 1) {
			realTimeEntity= realTimeDao.getLastEntry(satelliteId);
		} else {
			realTimeEntity= realTimeDao.getLastFC2Entry();
		}

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
		List<ValMinMax> amacValues = new ArrayList<ValMinMax>();
		List<ValMinMax> rfValues = new ArrayList<ValMinMax>();
		List<ValMinMax> paValues = new ArrayList<ValMinMax>();
		List<ValMinMax> antsValues = new ArrayList<ValMinMax>();
		List<StringPair> swValues = new ArrayList<StringPair>();
		
		epsValues.add(new ValMinMax("Solar Array +Y Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent1PlusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array +Y Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent1MinusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Y Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent2PlusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Y Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent2MinusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Z Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent3PlusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array Z Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent3MinusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array -Y Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent4PlusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array -Y Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent4MinusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array X Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent5PlusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array X Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent5MinusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array +X Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent6PlusString()), N_A, N_A));
		epsValues.add(new ValMinMax("Solar Array +X Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent6MinusString()), N_A, N_A));
		
		epsValues.add(new ValMinMax("Battery 0 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery0CurrentString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 0 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery0VoltsString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 0 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery0TemperatureString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 1 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery1CurrentString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 1 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery1VoltsString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 1 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery1TemperatureString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 2 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery2CurrentString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 2 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery2VoltsString()), N_A, N_A));
		epsValues.add(new ValMinMax("Battery 2 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery2TemperatureString()), N_A, N_A));
		epsValues.add(new ValMinMax("Auto Thermal Control", realTimeFC2.getBatteryHeaterForcedOnString(), N_A, N_A));
		
		amacValues.add(new ValMinMax("Controller Mode", realTimeFC2.getAmacModeString(), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Density X", String.format(MAG_FLUX, realTimeFC2.getMagnetometer0String()), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Density Y", String.format(MAG_FLUX, realTimeFC2.getMagnetometer1String()), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Density Z", String.format(MAG_FLUX, realTimeFC2.getMagnetometer2String()), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Magnitude", String.format(MAG_FLUX, realTimeFC2.getMagnetometerMagnitudeString()), N_A, N_A));
		
		rfValues.add(new ValMinMax("Receiver Doppler", realTimeFC2.getReceiverDopplerString(), N_A, N_A));
		rfValues.add(new ValMinMax("Receiver RSSI", realTimeFC2.getReceiverRSSIString(), N_A, N_A));
		rfValues.add(new ValMinMax("Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getReceiverTemperatureString()), N_A, N_A));
		rfValues.add(new ValMinMax("Receive Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getReceiverCurrentString()), N_A, N_A));
		rfValues.add(new ValMinMax("Transmit Current 3.3V bus", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getTransmitCurrent3v3String()), N_A, N_A));
		rfValues.add(new ValMinMax("Transmit Current 5.0V bus", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getTransmitCurrent5v0String()), N_A, N_A));
		
		paValues.add(new ValMinMax("Forward Power", String.format(PA_MILLI_WATT_FORMAT, realTimeFC2.getForwardPowerString()), N_A, N_A));
		paValues.add(new ValMinMax("Reverse Power", String.format(PA_MILLI_WATT_FORMAT, realTimeFC2.getReversePowerString()), N_A, N_A));
		paValues.add(new ValMinMax("Device Temperature", String.format(PA_TEMPERATURE_FORMAT, realTimeFC2.getPaDeviceTemperatureString()), N_A, N_A));
		paValues.add(new ValMinMax("Bus Current", String.format(PA_MILLI_AMPS_FORMAT, realTimeFC2.getPaBusCurrentString()), N_A, N_A));
		
		swValues.add(new StringPair("In Eclipse", (realTimeFC2.isEclipsed()) ? "No" : "Yes"));
		swValues.add(new StringPair("Mode Manager Mode", realTimeFC2.getModeManagerModeString()));
		swValues.add(new StringPair("Mode Manager Comms. Nominal", (realTimeFC2.getModeManagerCommsNominal()) ? "Yes" : "No"));
		swValues.add(new StringPair("Mode Manager Comms. State", realTimeFC2.getModeManagerCommsStateString()));
		swValues.add(new StringPair("TMTC Mode Idle", (realTimeFC2.getTmtcManagerIdleEnable()) ? "Yes" : "No"));
		swValues.add(new StringPair("TMTC Event Forwarding", (realTimeFC2.getTmtcEventForwarding()) ? "Yes" : "No"));
		swValues.add(new StringPair("TC Buffer RX Enable", (realTimeFC2.getTcBufferReceiveEnable() == 1) ? "Yes" : "No"));
		swValues.add(new StringPair("TC Buffer TX Enable", (realTimeFC2.getTcBufferSendEnable() == 1) ? "Yes" : "No"));
		swValues.add(new StringPair("OBC Soft Reset Count", realTimeFC2.getObcSoftResetCountString()));
		swValues.add(new StringPair("EPS Hard Reset Count", realTimeFC2.getEpsHardResetCountString()));
		swValues.add(new StringPair("Sequence Number", realTimeFC2.getSequenceNumberString()));
		swValues.add(new StringPair("Command Count", realTimeFC2.getDtmfCommandCountString()));
		swValues.add(new StringPair("Last Command", realTimeFC2.getDtmfLastCommandString()));
		swValues.add(new StringPair("Command Success", (realTimeFC2.getDtmfCommandSuccess()) ? "Yes" : "No"));
		
		SharedInfo realtimeInfo 
			= new RealTimeFC2Info(realTimeEntity.getSequenceNumber(), 
					SDTF.format(createdDate),
					epsValues, amacValues, rfValues, paValues, swValues,
					siteList, SDTF.format(minmaxResetDate),
					latitude, longitude, packetCount, satelliteMode, transponderState,
					realTimeEntity.isValid() ? 1 : 0);
		
		return realtimeInfo;
		
	}
}
