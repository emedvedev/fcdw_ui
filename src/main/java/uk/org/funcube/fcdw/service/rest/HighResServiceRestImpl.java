// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.funcube.fcdw.server.dao.HighPrecisionDao;
import uk.org.funcube.fcdw.server.model.HPEntity;
import uk.org.funcube.fcdw.server.shared.DataElement;
import uk.org.funcube.fcdw.server.shared.HighResJson;


@Controller
@RequestMapping(value = "/ui/highres")
public class HighResServiceRestImpl {
	
	@Autowired
	HighPrecisionDao highResolutionDao;
	
	// get all data for one orbit for a given satellite
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HighResJson getAllHighResForSatellite(
			@PathVariable(value = "satelliteId") Long satelliteId) {
		

		HighResJson highResJson = new HighResJson();
		
		DataElement channel1 = new DataElement("Channel1");
		highResJson.addElement(channel1);
		DataElement channel2 = new DataElement("Channel2");
		highResJson.addElement(channel2);
		DataElement channel3 = new DataElement("Channel3");
		highResJson.addElement(channel3);
		DataElement channel4 = new DataElement("Channel4");
		highResJson.addElement(channel4);
		DataElement channel5 = new DataElement("Channel5");
		highResJson.addElement(channel5);
		DataElement channel6 = new DataElement("Channel6");
		highResJson.addElement(channel6);
		DataElement channel7 = new DataElement("Channel7");
		highResJson.addElement(channel7);
		DataElement channel8 = new DataElement("Channel8");
		highResJson.addElement(channel8);
		DataElement channel9 = new DataElement("Channel9");
		highResJson.addElement(channel9);
		DataElement channel10 = new DataElement("Channel10");
		highResJson.addElement(channel10);
		
		List<HPEntity> latestOrbit = highResolutionDao.getLatestSixtySeconds(satelliteId);
		
		for (HPEntity highResEntity : latestOrbit) {
			for (int i = 0; i < 10; i++) {
				switch (i) {
				case 0: 
					channel1.addDatum(new Double(highResEntity.getC1()));
					break;
				case 1: 
					channel2.addDatum(new Double(highResEntity.getC2()));
					break;
				case 2: 
					channel3.addDatum(new Double(highResEntity.getC3()));
					break;
				case 3: 
					channel4.addDatum(new Double(highResEntity.getC4()));
					break;
				case 4: 
					channel5.addDatum(new Double(highResEntity.getC5()));
					break;
				case 5: 
					channel6.addDatum(new Double(highResEntity.getC6()));
					break;
				case 6: 
					channel7.addDatum(new Double(highResEntity.getC7()));
					break;
				case 7: 
					channel8.addDatum(new Double(highResEntity.getC8()));
					break;
				case 8: 
					channel9.addDatum(new Double(highResEntity.getC9()));
					break;
				case 9: 
					channel10.addDatum(new Double(highResEntity.getC10()));
				}
			}
		}
		
		
		return highResJson;
		
	}
	
}
