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
@RequestMapping("realtimefc2")
public class RealtimeFC2Controller extends AbstractService {

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

	private static final Logger LOGGER = Logger.getLogger(RealtimeFC2Controller.class.getName());

	@Autowired
	private HexFrameDao hexFrameDao;
	
	@Autowired
	private MinMaxDao minMaxDao;
	
	
	private Long satelliteId;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView realtime(@QueryParam(value = "satelliteId") Long satelliteId) {
		
		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("realtimefc2");

		this.satelliteId = satelliteId;
		final HexFrame latestFrame = hexFrameDao.getLatest(satelliteId);

		if (latestFrame == null) {
			mv.addObject("satelliteId", satelliteId);
			return mv;
		}
		
		final Long hfCount = hexFrameDao.countAll(satelliteId);
		final String packetCount = String.format("%d (%5.1fMB)", hfCount, (double)hfCount * 2048 / 8 / 1000000);

		Set<UserEntity> users = latestFrame.getUsers();

		List<String> siteList = new ArrayList<String>();

		for (UserEntity user : users) {
			siteList.add(user.getSiteId());
		}

		//mv.addObject("createdDate", SDTF.format(createdDate));
		mv.addObject("siteList", siteList);
		mv.addObject("satelliteId", satelliteId);
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

	public void setHexFrameDao(HexFrameDao hexFrameDao) {
		this.hexFrameDao = hexFrameDao;
	}

	public void setMinMaxDao(MinMaxDao minMaxDao) {
		this.minMaxDao = minMaxDao;
	}

}
