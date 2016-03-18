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
import uk.org.funcube.fcdw.server.model.MinMax;
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
	
	public RealTimeFC2ServiceRestImpl() {
		super();
	}

	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SharedInfo getLatest(
			@PathVariable(value = "satelliteId") Long theSatelliteId) {
		
		this.satelliteId = theSatelliteId;
		
		satelliteId = (satelliteId != null) ? satelliteId : new Long(1L);

		final HexFrame latestHexFrame = hexFrameDao.getLatestFC2();
        
        List<MinMax> minmaxValues = minMaxDao.findBySatelliteId(satelliteId);
		
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
		List<StringPair> swValues = new ArrayList<StringPair>();
		
		String minValue = "";
        String maxValue = "";
        
        minValue = generateSolarArrayCurrentString(minmaxValues.get(0).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(0).getMaximum(), 0);
		epsValues.add(new ValMinMax("Solar Array +Y Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent1PlusString()), maxValue, minValue));

        minValue = generateSolarArrayCurrentString(minmaxValues.get(1).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(1).getMaximum(), 0);
		epsValues.add(new ValMinMax("Solar Array +Y Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent1MinusString()), maxValue, minValue));
        
		minValue = generateSolarArrayCurrentString(minmaxValues.get(2).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(2).getMaximum(), 0);
		epsValues.add(new ValMinMax("Solar Array Y Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent2PlusString()), maxValue, minValue));

        minValue = generateSolarArrayCurrentString(minmaxValues.get(3).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(3).getMaximum(), 0);
		epsValues.add(new ValMinMax("Solar Array Y Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent2MinusString()), maxValue, minValue));
		
		minValue = generateSolarArrayCurrentString(minmaxValues.get(4).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(4).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array Z Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent3PlusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(5).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(5).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array Z Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent3MinusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(6).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(6).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array -Y Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent4PlusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(7).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(7).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array -Y Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent4MinusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(8).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(8).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array X Fixed +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent5PlusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(9).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(9).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array X Fixed -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent5MinusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(10).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(10).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array +X Deployable +", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent6PlusString()), maxValue, minValue));
		
        minValue = generateSolarArrayCurrentString(minmaxValues.get(11).getMinimum(), 0);
        maxValue = generateSolarArrayCurrentString(minmaxValues.get(11).getMaximum(), 0);
        epsValues.add(new ValMinMax("Solar Array +X Deployable -", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getSolarArrayCurrent6MinusString()), maxValue, minValue));
		
        // NOTE SKIP 13 Solar Panel Temp
        
        minValue = generateBatteryCurrentString(minmaxValues.get(13).getMinimum());
        maxValue = generateBatteryCurrentString(minmaxValues.get(13).getMaximum());
		epsValues.add(new ValMinMax("Battery 0 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery0CurrentString()), maxValue, minValue));
		
		minValue = generateVoltageString(minmaxValues.get(14).getMinimum());
		maxValue = generateVoltageString(minmaxValues.get(14).getMaximum());
		epsValues.add(new ValMinMax("Battery 0 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery0VoltsString()), maxValue, minValue));
		
		minValue = generateBatteryTemperatureString(minmaxValues.get(15).getMinimum());
		maxValue = generateBatteryTemperatureString(minmaxValues.get(15).getMaximum());
        epsValues.add(new ValMinMax("Battery 0 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery0TemperatureString()), maxValue, minValue));
        
        minValue = generateBatteryCurrentString(minmaxValues.get(16).getMinimum());
        maxValue = generateBatteryCurrentString(minmaxValues.get(16).getMaximum());
        epsValues.add(new ValMinMax("Battery 1 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery1CurrentString()), maxValue, minValue));
        
        minValue = generateVoltageString(minmaxValues.get(17).getMinimum());
        maxValue = generateVoltageString(minmaxValues.get(17).getMaximum());
        epsValues.add(new ValMinMax("Battery 1 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery1VoltsString()), maxValue, minValue));
		
		minValue = generateBatteryTemperatureString(minmaxValues.get(18).getMinimum());
		maxValue = generateBatteryTemperatureString(minmaxValues.get(18).getMaximum());
        epsValues.add(new ValMinMax("Battery 1 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery1TemperatureString()), maxValue, minValue));
		
		minValue = generateBatteryCurrentString(minmaxValues.get(19).getMinimum());
        maxValue = generateBatteryCurrentString(minmaxValues.get(19).getMaximum());
        epsValues.add(new ValMinMax("Battery 2 Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getBattery2CurrentString()), maxValue, minValue));
        
        minValue = generateVoltageString(minmaxValues.get(20).getMinimum());
        maxValue = generateVoltageString(minmaxValues.get(20).getMaximum());
        epsValues.add(new ValMinMax("Battery 2 Voltage", String.format(VOLTS_FORMAT, realTimeFC2.getBattery2VoltsString()), maxValue, minValue));
		
		minValue = generateBatteryTemperatureString(minmaxValues.get(21).getMinimum());
		maxValue = generateBatteryTemperatureString(minmaxValues.get(21).getMaximum());
        epsValues.add(new ValMinMax("Battery 2 Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getBattery2TemperatureString()), maxValue, minValue));
        
		epsValues.add(new ValMinMax("Auto Thermal Control", realTimeFC2.getBatteryHeaterForcedOnString(), N_A, N_A));
		
		amacValues.add(new ValMinMax("Controller Mode", realTimeFC2.getAmacModeString(), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Density X", String.format(MAG_FLUX, realTimeFC2.getMagnetometer0String()), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Density Y", String.format(MAG_FLUX, realTimeFC2.getMagnetometer1String()), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Density Z", String.format(MAG_FLUX, realTimeFC2.getMagnetometer2String()), N_A, N_A));
		amacValues.add(new ValMinMax("Mag. Flux Magnitude", String.format(MAG_FLUX, realTimeFC2.getMagnetometerMagnitudeString()), N_A, N_A));
		
		rfValues.add(new ValMinMax("Receiver Doppler", realTimeFC2.getReceiverDopplerString(), N_A, N_A));
		rfValues.add(new ValMinMax("Receiver RSSI", realTimeFC2.getReceiverRSSIString(), N_A, N_A));
        
        minValue = generateReceiverTemperatureString(minmaxValues.get(30).getMinimum());
        maxValue = generateReceiverTemperatureString(minmaxValues.get(30).getMaximum());
        rfValues.add(new ValMinMax("Temperature", String.format(TEMPERATURE_FORMAT, realTimeFC2.getReceiverTemperatureString()), maxValue, minValue));
		
		minValue = generateReceiverCurrentString(minmaxValues.get(31).getMinimum());
        maxValue = generateReceiverCurrentString(minmaxValues.get(31).getMaximum());
		rfValues.add(new ValMinMax("Receive Current", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getReceiverCurrentString()), minValue, maxValue));
		
		minValue = generateTransmitCurrent3v3String(minmaxValues.get(32).getMinimum());
        maxValue = generateTransmitCurrent3v3String(minmaxValues.get(32).getMaximum());
        rfValues.add(new ValMinMax("Transmit Current 3.3V bus", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getTransmitCurrent3v3String()), minValue, maxValue));
        
        minValue = generateTransmitCurrent5v0String(minmaxValues.get(33).getMinimum());
        maxValue = generateTransmitCurrent5v0String(minmaxValues.get(33).getMaximum());
        rfValues.add(new ValMinMax("Transmit Current 5.0V bus", String.format(MILLI_AMPS_FORMAT, realTimeFC2.getTransmitCurrent5v0String()), minValue, maxValue));
		
		paValues.add(new ValMinMax("Forward Power", String.format(PA_MILLI_WATT_FORMAT, realTimeFC2.getForwardPowerString()), N_A, N_A));
		paValues.add(new ValMinMax("Reverse Power", String.format(PA_MILLI_WATT_FORMAT, realTimeFC2.getReversePowerString()), N_A, N_A));
        
        minValue = generatePaDeviceTemperatureString(minmaxValues.get(36).getMinimum());
        maxValue = generatePaDeviceTemperatureString(minmaxValues.get(36).getMaximum());
        paValues.add(new ValMinMax("Device Temperature", String.format(PA_TEMPERATURE_FORMAT, realTimeFC2.getPaDeviceTemperatureString()), maxValue, minValue));
        
        minValue = generatePaBusCurrentString(minmaxValues.get(37).getMinimum());
        maxValue = generatePaBusCurrentString(minmaxValues.get(37).getMaximum());
        paValues.add(new ValMinMax("Bus Current", String.format(PA_MILLI_AMPS_FORMAT, realTimeFC2.getPaBusCurrentString()), minValue, maxValue));
		
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
		
		final int valid = realTimeEntity.isValid() ? 1 : 0;
		
        SharedInfo realtimeInfo 
			= new RealTimeFC2Info(realTimeEntity.getSequenceNumber(), 
					SDTF.format(createdDate),
					epsValues, amacValues, rfValues, paValues, swValues,
					siteList, SDTF.format(minmaxResetDate),
					latitude, longitude, packetCount, satelliteMode, transponderState,
					valid);
		
		return realtimeInfo;
		
	}
    
    private String generatePaBusCurrentString(Long paBoardCurrent) {
        return String.format("%5.1f", (paBoardCurrent * 5.496E-01) + 2.544E+00);
    }

    private String generatePaDeviceTemperatureString(Long paBoardTemperature) {
        double value = 6.29764694373E+001 +
                (3.80132039683E-001 * paBoardTemperature) +
                (-9.59767044686E-003 * paBoardTemperature * paBoardTemperature) +
                (5.15917746352E-005 * paBoardTemperature * paBoardTemperature * paBoardTemperature) +
                (-9.77975220499E-008 * paBoardTemperature * paBoardTemperature * paBoardTemperature * paBoardTemperature);
        
        return String.format("%5.1f", value);
    }

    private String generateTransmitCurrent5v0String(Long transmitCurrent5v0) {
        return String.format("%5.1f", transmitCurrent5v0 * 0.636);
    }

    private String generateTransmitCurrent3v3String(Long transmitCurrent3v3) {
        return String.format("%5.1f", transmitCurrent3v3 * 0.636);
    }

    private String generateReceiverCurrentString(Long receiverCurrent) {
        return String.format("%5.1f", receiverCurrent * 0.636);
    }

    private String generateReceiverTemperatureString(Long receiverTemperature) {
        return String.format("%5.1f", (receiverTemperature * -0.857) + 193.672);
    }

    private String generateSolarArrayCurrentString(Long current, int type) {
        if (current == 99999 || current == -99999) {
            return "N/A";
        }
        if (current == 1023) {
            return "0";
        } else {
            if (type == 0) {
                return String.format("%3.1f", (current * -0.54312) +  + 555.61);
            } else {
                return String.format("%3.1f", (current * -0.575) +  + 588.22);
            }
        }
    }

    private String generateBatteryCurrentString(Long current) {
        if (current == 99999 || current == -99999) {
            return "N/A";
        }
        if (current == 0) {
            return "0";
        } else {
            return String.format("%4.0f", ((current * 4 * -3.200E+00) + 2.926E+03));
        }
    }

    private String generateVoltageString(Long volts) {
        if (volts == 99999 || volts == -99999) {
            return "N/A";
        }
        if (volts == 0) {
            return "0";
        } else {
            return String.format("%4.1f", (volts * 4 * -9.470E-03) + 9.798896E+00);
        }
    }
    
    private String generateBatteryTemperatureString(Long temperature) {
        if (temperature == 99999 || temperature == -99999) {
            return "N/A";
        }
        return String.format("%3.1f", (temperature * -0.163 * 4.75) + 75);
    }
}
