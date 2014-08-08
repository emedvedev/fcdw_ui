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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.funcube.fcdw.server.clock.UTCClock;
import uk.org.funcube.fcdw.server.dao.HexFrameDao;
import uk.org.funcube.fcdw.server.dao.WholeOrbitDataDao;
import uk.org.funcube.fcdw.server.model.HexFrame;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.model.WODEntity;
import uk.org.funcube.fcdw.server.shared.DataElement;
import uk.org.funcube.fcdw.server.shared.WodJson;


@Controller
@RequestMapping(value = "/ui/wodfc2")
public class WodFC2ServiceRestImpl {
	
	@Autowired
	WholeOrbitDataDao wholeOrbitDataDao;
	
	@Autowired
	HexFrameDao hexFrameDao;
	
	@Autowired
	UTCClock utcClock;
	
	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public WodJson getAllWodForSatellite(
			@PathVariable(value = "satelliteId") Long satelliteId) {
		

		WodJson wodJson = new WodJson();
		
		DataElement channel1 = new DataElement("Black Chassis");
		wodJson.addElement(channel1);
		DataElement channel2 = new DataElement("Silver Chassis");
		wodJson.addElement(channel2);
		DataElement channel3 = new DataElement("Black Panel");
		wodJson.addElement(channel3);
		DataElement channel4 = new DataElement("Silver Panel");
		wodJson.addElement(channel4);
		DataElement channel5 = new DataElement("Solar Panel +X");
		wodJson.addElement(channel5);
		DataElement channel6 = new DataElement("Solar Panel -X");
		wodJson.addElement(channel6);
		DataElement channel7 = new DataElement("Solar Panel +Y");
		wodJson.addElement(channel7);
		DataElement channel8 = new DataElement("Solar Panel -Y");
		wodJson.addElement(channel8);
		DataElement channel9 = new DataElement("Solar Panel Volt. X");
		wodJson.addElement(channel9);
		DataElement channel10 = new DataElement("Solar Panel Volt. Y");
		wodJson.addElement(channel10);
		DataElement channel11 = new DataElement("Solar Panel Volt. Z");
		wodJson.addElement(channel11);
		DataElement channel12 = new DataElement("Tot. Photo Curr.");
		wodJson.addElement(channel12);
		DataElement channel13 = new DataElement("Battery Volts");
		wodJson.addElement(channel13);
		DataElement channel14 = new DataElement("Total Sys. Curr");
		wodJson.addElement(channel14);
		
		long currentTime = utcClock.currentTime();
		long oneHourAgo = currentTime - (60 * 60 * 1000);
		
//		List<WODEntity> latestItem = wholeOrbitDataDao.getLastItem(satelliteId);
//		
//		Date satelliteTime = latestItem.get(0).getSatelliteTime();
//		
//		List<WODEntity> latestOrbit = wholeOrbitDataDao.getSince(satelliteId, new Date(satelliteTime.getTime() - (104 * 60 * 1000)));
		
		List<WODEntity> latestOrbit = wholeOrbitDataDao.getLatestOrbit(satelliteId);
		
		long hour = -104;
		
		for (WODEntity wodEntity : latestOrbit) {
			
			for (int i = 0; i < 14; i++) {
				
				switch (i) {
				case 0: 
					channel1.addDatum(hour, scale(wodEntity.getC1(), 1.0, 0.0));
					break;
				case 1: 
					channel2.addDatum(hour,scale(wodEntity.getC2(), 1.0, 0.0));
					break;
				case 2: 
					channel3.addDatum(hour,scale(wodEntity.getC3(), 1.0, 0.0));
					break;
				case 3: 
					channel4.addDatum(hour,scale(wodEntity.getC4(), 1.0, 0.0));
					break;
				case 4: 
					channel5.addDatum(hour,scale(wodEntity.getC5(), 1.0, 0.0));
					break;
				case 5: 
					channel6.addDatum(hour,scale(wodEntity.getC6(), 1.0, 0.0));
					break;
				case 6: 
					channel7.addDatum(hour,scale(wodEntity.getC7(), 1.0, 0.0));
					break;
				case 7: 
					channel8.addDatum(hour,scale(wodEntity.getC8(), 1.0, 0.0));
					break;
				case 8: 
					channel9.addDatum(hour,scale(wodEntity.getC9(), 1.0, 0.0));
					break;
				case 9: 
					channel10.addDatum(hour,scale(wodEntity.getC10(), 1.0, 0.0));
					break;
				case 10: 
					channel11.addDatum(hour,scale(wodEntity.getC11(), 1.0, 0.0));
					break;
				case 11: 
					channel12.addDatum(hour,scale(wodEntity.getC12(), 1.0, 0.0));
					break;
				case 12: 
					channel13.addDatum(hour,scale(wodEntity.getC13(), 1.0, 0.0));
					break;
				case 13: 
					channel14.addDatum(hour,scale(wodEntity.getC14(), 1.0, 0.0));
					break;
				}
				
			}
			
			hour++;
		}

		final HexFrame latestFrame = hexFrameDao.getLatest(satelliteId);
		
		Set<UserEntity> users = latestFrame.getUsers();

		List<String> siteList = new ArrayList<String>();

		for (UserEntity user : users) {
			siteList.add(user.getSiteId());
		}
		
		wodJson.setSiteList(siteList);
		
		return wodJson;
		
	}
	

	private void getLastItem(Long satelliteId) {
		// TODO Auto-generated method stub
		
	}


	private Double scale(Long adc, Double multiplier, Double offset) {
		double value = (adc * multiplier) + offset;
		String twoDP = String.format("%6.2f", value);
		return new Double(twoDP);
	}


	private HttpHeaders addAccessControllAllowOrigin() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return headers;
	}
	
}
