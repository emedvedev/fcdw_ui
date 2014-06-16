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
@RequestMapping("realtimeFC2")
public class RealtimeFC2Controller extends AbstractService {

	private static final Logger LOGGER = Logger.getLogger(RealtimeFC2Controller.class.getName());

	private static final String N_A = "N/A";

	@Autowired
	private HexFrameDao hexFrameDao;
	
	@Autowired
	private MinMaxDao minMaxDao;
	
	
	private Long satelliteId;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView realtime() {
		
		satelliteId = new Long(1L);

		ModelAndView mv = new ModelAndView("realtimeFC2");

		this.satelliteId = 1L;
		final HexFrame latestFrame = hexFrameDao.getLatest(satelliteId);

		if (latestFrame == null) {
			mv.addObject("satelliteId", satelliteId);
			return mv;
		}
		
		final Long hfCount = hexFrameDao.countAll(satelliteId);
		final String packetCount = String.format("%d (%5.1fMB)", hfCount, (double)hfCount * 2048 / 8 / 1000000);
		
		String latitude = latestFrame.getLatitude();
		
		final double latitudeValue = Double.parseDouble((latitude != null) ? latitude : "0");
		if (latitudeValue > 0.0) {
			latitude = String.format("%5.1f N", latitudeValue);
		} else {
			latitude = String.format("%5.1f S", Math.abs(latitudeValue));
		}
		
		String longitude = latestFrame.getLongitude();
		
		final double longitudeValue = Double.parseDouble((longitude != null) ? longitude : "0");
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
