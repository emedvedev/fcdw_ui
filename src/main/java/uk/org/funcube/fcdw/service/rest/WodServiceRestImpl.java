// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.funcube.fcdw.server.dao.WholeOrbitDataDao;
import uk.org.funcube.fcdw.server.model.WODEntity;
import uk.org.funcube.fcdw.server.shared.DataElement;
import uk.org.funcube.fcdw.server.shared.WodJson;


@Controller
@RequestMapping(value = "/ui/wod")
public class WodServiceRestImpl {
	
	@Autowired
	WholeOrbitDataDao wholeOrbitDataDao;
	
	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public WodJson getAllWodForSatellite(
			@PathVariable(value = "satelliteId") Long satelliteId) {
		

		WodJson wodJson = new WodJson();
		
		DataElement channel1 = new DataElement("Channel1");
		wodJson.addElement(channel1);
		DataElement channel2 = new DataElement("Channel2");
		wodJson.addElement(channel2);
		DataElement channel3 = new DataElement("Channel3");
		wodJson.addElement(channel3);
		DataElement channel4 = new DataElement("Channel4");
		wodJson.addElement(channel4);
		DataElement channel5 = new DataElement("Channel5");
		wodJson.addElement(channel5);
		DataElement channel6 = new DataElement("Channel6");
		wodJson.addElement(channel6);
		DataElement channel7 = new DataElement("Channel7");
		wodJson.addElement(channel7);
		DataElement channel8 = new DataElement("Channel8");
		wodJson.addElement(channel8);
		DataElement channel9 = new DataElement("Channel9");
		wodJson.addElement(channel9);
		DataElement channel10 = new DataElement("Channel10");
		wodJson.addElement(channel10);
		DataElement channel11 = new DataElement("Channel11");
		wodJson.addElement(channel11);
		DataElement channel12 = new DataElement("Channel12");
		wodJson.addElement(channel12);
		DataElement channel13 = new DataElement("Channel13");
		wodJson.addElement(channel13);
		DataElement channel14 = new DataElement("Channel14");
		wodJson.addElement(channel8);
		

		
		List<WODEntity> latestOrbit = wholeOrbitDataDao.getLatestOrbit(satelliteId);
		
		for (WODEntity wodEntity : latestOrbit) {
			
			for (int i = 0; i < 14; i++) {
				switch (i) {
				case 0: 
					channel1.addDatum(new Double(wodEntity.getC1()));
					break;
				case 1: 
					channel2.addDatum(new Double(wodEntity.getC2()));
					break;
				case 2: 
					channel3.addDatum(new Double(wodEntity.getC3()));
					break;
				case 3: 
					channel4.addDatum(new Double(wodEntity.getC4()));
					break;
				case 4: 
					channel5.addDatum(new Double(wodEntity.getC5()));
					break;
				case 5: 
					channel6.addDatum(new Double(wodEntity.getC6()));
					break;
				case 6: 
					channel7.addDatum(new Double(wodEntity.getC7()));
					break;
				case 7: 
					channel8.addDatum(new Double(wodEntity.getC8()));
					break;
				case 8: 
					channel9.addDatum(new Double(wodEntity.getC9()));
					break;
				case 9: 
					channel10.addDatum(new Double(wodEntity.getC10()));
					break;
				case 10: 
					channel11.addDatum(new Double(wodEntity.getC11()));
					break;
				case 11: 
					channel12.addDatum(new Double(wodEntity.getC12()));
					break;
				case 12: 
					channel13.addDatum(new Double(wodEntity.getC13()));
					break;
				case 13: 
					channel14.addDatum(new Double(wodEntity.getC14()));
					break;
				}
				
			}
		}
		
		
		return wodJson;
		
	}
	

	private HttpHeaders addAccessControllAllowOrigin() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return headers;
	}
	
}
