// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.funcube.fcdw.server.dao.HexFrameDao;
import uk.org.funcube.fcdw.server.dao.HighPrecisionDao;
import uk.org.funcube.fcdw.server.model.HPEntity;
import uk.org.funcube.fcdw.server.model.HexFrame;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.DataElement;
import uk.org.funcube.fcdw.server.shared.HighResJson;

@Controller
@RequestMapping(value = "/ui/highres")
public class HighResServiceRestImpl extends AbstractService {

	@Autowired
	HighPrecisionDao highResolutionDao;
	
	@Autowired
	HexFrameDao hexFrameDao;

	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HighResJson getAllHighResForSatellite(
			@PathVariable(value = "satelliteId") Long satelliteId) {

		long previousTime = 0;

		HighResJson highResJson = new HighResJson();

		DataElement channel1 = new DataElement("Sun Sensor +X");
		highResJson.addElement(channel1);
		DataElement channel2 = new DataElement("Sun Sensor +Y");
		highResJson.addElement(channel2);
		DataElement channel3 = new DataElement("Sun Sensor -Y");
		highResJson.addElement(channel3);
		DataElement channel4 = new DataElement("Sun Sensor +Z");
		highResJson.addElement(channel4);
		DataElement channel5 = new DataElement("Sun Sensor -Z");
		highResJson.addElement(channel5);
		DataElement channel6 = new DataElement("Total Photo Current");
		highResJson.addElement(channel6);
		DataElement channel7 = new DataElement("Battery Volts");
		highResJson.addElement(channel7);

		List<HPEntity> latestOrbit = highResolutionDao
				.getLatestFourMinutes(satelliteId);
		
		long startX = latestOrbit.size() * -2;
		long xValue = 0;
		
		highResJson.setMinX((int) startX);

		for (HPEntity highResEntity : latestOrbit) {
			for (int i = 0; i < 10; i++) {
				switch (i) {
				case 0:
					xValue = pad(channel1,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel1.addDatum(xValue, new Double(SOL_ILLUMINATION[highResEntity
							.getC1().intValue()]));
					break;
				case 1:
					xValue = pad(channel2,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel2.addDatum(xValue, new Double(SOL_ILLUMINATION[highResEntity
							.getC2().intValue()]));
					break;
				case 2:
					xValue = pad(channel3,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel3.addDatum(xValue, new Double(SOL_ILLUMINATION[highResEntity
							.getC3().intValue()]));
					break;
				case 3:
					xValue = pad(channel4,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel4.addDatum(xValue, new Double(SOL_ILLUMINATION[highResEntity
							.getC4().intValue()]));
					break;
				case 4:
					xValue = pad(channel5,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel5.addDatum(xValue, new Double(SOL_ILLUMINATION[highResEntity
							.getC5().intValue()]));
					break;
				case 5:
					xValue = pad(channel6,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel6.addDatum(xValue, new Double(highResEntity.getC6() * 2.0));
					break;
				case 6:
					xValue = pad(channel7,
							highResEntity.getSatelliteTime().getTime() - 1000,
							previousTime, startX);
					channel7.addDatum(xValue, new Double(highResEntity.getC7() * 2.0));
					break;
				}
			}

			previousTime = highResEntity.getSatelliteTime().getTime();
			startX = xValue + 1;
		}

		final HexFrame latestFrame = hexFrameDao.getLatest(satelliteId);
		
		Set<UserEntity> users = latestFrame.getUsers();

		List<String> siteList = new ArrayList<String>();

		for (UserEntity user : users) {
			siteList.add(user.getSiteId());
		}
		
		highResJson.setSiteList(siteList);

		return highResJson;

	}

	private long pad(DataElement channel, long lastTime, long previousTime,
			long startX) {

		long xValue = startX;

		if (previousTime != 0) {
			final long timeDifference = lastTime - previousTime;
			if (timeDifference > 0) {
				for (int i = 0; i < timeDifference; i += 1000) {
					channel.addDatum(startX, (Double) null);
					startX++;
				}
			}
		}

		return startX;
	}

}
