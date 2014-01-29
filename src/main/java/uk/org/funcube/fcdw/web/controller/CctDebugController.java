// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.dao.FitterMessageDao;
import uk.org.funcube.fcdw.server.model.FitterMessage;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.FitterDebug;
import uk.org.funcube.fcdw.server.shared.StringPair;

@Controller
@RequestMapping("admin/cctDebug")
public class CctDebugController extends AbstractService {

	@Autowired
	private FitterMessageDao fitterMessageDao;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fitter(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		List<StringPair> miscValues = new ArrayList<StringPair>();
		List<StringPair> antsValues = new ArrayList<StringPair>();
		List<StringPair> stateValues = new ArrayList<StringPair>();
		List<StringPair> eclipseValues = new ArrayList<StringPair>();
		List<StringPair> safeValues = new ArrayList<StringPair>();
		List<StringPair> burnValues = new ArrayList<StringPair>();
		List<StringPair> autoValues = new ArrayList<StringPair>();

		FitterMessage fitter = fitterMessageDao.getCctDebug(satelliteId);

		ModelAndView mv = new ModelAndView("admin/cctDebug");

		if (fitter != null) {
			final Date createdDate = fitter.getLastReceived();
			FitterDebug fitterDebug = new FitterDebug(convertHexBytePairToBinary(fitter.getMessageText()));

			miscValues.add(new StringPair("DTMF Tones Rejected", fitterDebug.getDtmfRejectedToneCount()));
			miscValues.add(new StringPair("Deploy Count (0)", fitterDebug.getDeployCount0()));
			miscValues.add(new StringPair("Deploy Count (1)", fitterDebug.getDeployCount1()));
			miscValues.add(new StringPair("Deploy Count (2)", fitterDebug.getDeployCount2()));

			antsValues.add(new StringPair("A1B", fitterDebug.getAntsA1B()));
			antsValues.add(new StringPair("A1S", fitterDebug.getAntsA1S()));
			antsValues.add(new StringPair("A1T", fitterDebug.getAntsA1T()));
			antsValues.add(new StringPair("A2B", fitterDebug.getAntsA2B()));
			antsValues.add(new StringPair("A2S", fitterDebug.getAntsA2S()));
			antsValues.add(new StringPair("A2T", fitterDebug.getAntsA2T()));
			antsValues.add(new StringPair("A3B", fitterDebug.getAntsA3B()));
			antsValues.add(new StringPair("A3S", fitterDebug.getAntsA3S()));
			antsValues.add(new StringPair("A3T", fitterDebug.getAntsA3T()));
			antsValues.add(new StringPair("A4B", fitterDebug.getAntsA4B()));
			antsValues.add(new StringPair("A4S", fitterDebug.getAntsA4S()));
			antsValues.add(new StringPair("A4T", fitterDebug.getAntsA4T()));
			antsValues.add(new StringPair("Armed", fitterDebug.getAntsARM()));
			antsValues.add(new StringPair("Ingnition", fitterDebug.getAntsIGN()));

			stateValues.add(new StringPair("Cycle", fitterDebug.getStateCycle()));
			stateValues.add(new StringPair("Device Cycle", fitterDebug.getStateDeviceCycle()));
			stateValues.add(new StringPair("Device Enable", fitterDebug.getStateDeviceEnable()));
			stateValues.add(new StringPair("Device Error", fitterDebug.getStateDeviceError()));
			stateValues.add(new StringPair("Sequence", fitterDebug.getStateSequence()));
			stateValues.add(new StringPair("Checksum", fitterDebug.getStateCksum()));

			eclipseValues.add(new StringPair("Count Amateur", fitterDebug.getEclipseActiveCountAmateur()));
			eclipseValues.add(new StringPair("Count Amateur Remaining", fitterDebug.getEclipseActiveCountAmateurRemaining()));
			eclipseValues.add(new StringPair("Count RX Only", fitterDebug.getEclipseActiveCountRxonly()));
			eclipseValues.add(new StringPair("Count RX Only Remaining", fitterDebug.getEclipseActiveCountRxonlyRemaining()));
			eclipseValues.add(new StringPair("Hysteresis Current", fitterDebug.getEclipseHysteresisCurrent()));
			eclipseValues.add(new StringPair("Hysteresis Volts", fitterDebug.getEclipseHysteresisVolts()));
			eclipseValues.add(new StringPair("Min New Mode Duration", fitterDebug.getEclipseMinNewModeDuration()));
			eclipseValues.add(new StringPair("Panel Enable Current", fitterDebug.getEclipsePanelEnableCurrent()));
			eclipseValues.add(new StringPair("Panel Enable Volts", fitterDebug.getEclipsePanelEnableVolts()));
			eclipseValues.add(new StringPair("Threshold Current", fitterDebug.getEclipseThresholdCurrent()));
			eclipseValues.add(new StringPair("Threshold Volts", fitterDebug.getEclipseThresholdVolts()));
			eclipseValues.add(new StringPair("Checksum", fitterDebug.getEclipseCksum()));

			safeValues.add(new StringPair("Current Mode Duration", fitterDebug.getSafeCurrentModeDuration()));
			safeValues.add(new StringPair("EPS Temp (0)", fitterDebug.getSafeEpsTemp0()));
			safeValues.add(new StringPair("EPS Temp (1)", fitterDebug.getSafeEpsTemp1()));
			safeValues.add(new StringPair("EPS Temp (2)", fitterDebug.getSafeEpsTemp2()));
			safeValues.add(new StringPair("EPS Temp (3)", fitterDebug.getSafeEpsTemp3()));
			safeValues.add(new StringPair("EPS Temp (4)", fitterDebug.getSafeEpsTemp4()));
			safeValues.add(new StringPair("Hysteresis", fitterDebug.getSafeHysteresis()));
			safeValues.add(new StringPair("Min Mode Duration", fitterDebug.getSafeMinModeDuration()));
			safeValues.add(new StringPair("ThresholdVolts (1)", fitterDebug.getSafeThresholdVolts1()));
			safeValues.add(new StringPair("ThresholdVolts (2)", fitterDebug.getSafeThresholdVolts2()));
			safeValues.add(new StringPair("ThresholdVolts (3)", fitterDebug.getSafeThresholdVolts3()));
			safeValues.add(new StringPair("ThresholdVolts (4)", fitterDebug.getSafeThresholdVolts4()));
			safeValues.add(new StringPair("Checksum", fitterDebug.getSafeCksum()));

			burnValues.add(new StringPair("Side A 0", fitterDebug.getBurnSideA0()));
			burnValues.add(new StringPair("Side A 1", fitterDebug.getBurnSideA1()));
			burnValues.add(new StringPair("Side A 2", fitterDebug.getBurnSideA2()));
			burnValues.add(new StringPair("Side A 3", fitterDebug.getBurnSideA3()));
			burnValues.add(new StringPair("Side B 0", fitterDebug.getBurnSideB0()));
			burnValues.add(new StringPair("Side B 1", fitterDebug.getBurnSideB1()));
			burnValues.add(new StringPair("Side B 2", fitterDebug.getBurnSideB2()));
			burnValues.add(new StringPair("Side B 3", fitterDebug.getBurnSideB3()));

			autoValues.add(new StringPair("Boot Load Fitter Msgs", fitterDebug.getBootLoadFitterMsgs()));
			autoValues.add(new StringPair("Error Device", fitterDebug.getErrorDevice()));
			autoValues.add(new StringPair("Ants Power Cycle", fitterDebug.getAntsPowerCycle()));
			autoValues.add(new StringPair("Enable Fitter Upload", fitterDebug.getEnableFitterUpload()));
			autoValues.add(new StringPair("Enable Switch", fitterDebug.getEclipseSwitch()));
			autoValues.add(new StringPair("Eclipse Force", fitterDebug.getEclipseForce()));
			autoValues.add(new StringPair("Safe Mode Detect", fitterDebug.getSafeModeDetect()));
			autoValues.add(new StringPair("Safe Mode Force", fitterDebug.getSafeModeForce()));
			autoValues.add(new StringPair("Debug Model", fitterDebug.getDebugMode1()));
			autoValues.add(new StringPair("Delay Boot", fitterDebug.getDelayBoot()));
			autoValues.add(new StringPair("UkubeEnabled", fitterDebug.getUkubeEnabled()));

			mv.addObject("createdDate", SDTF.format(createdDate));
			mv.addObject("miscValues", miscValues);
			mv.addObject("antsValues", antsValues);
			mv.addObject("stateValues", stateValues);
			mv.addObject("eclipseValues", eclipseValues);
			mv.addObject("safeValues", safeValues);
			mv.addObject("burnValues", burnValues);
			mv.addObject("autoValues", autoValues);
		}

		mv.addObject("satelliteId", satelliteId);
		return mv;
	}

	public void setFitterMessageDao(FitterMessageDao fitterMessageDao) {
		this.fitterMessageDao = fitterMessageDao;
	}

}
