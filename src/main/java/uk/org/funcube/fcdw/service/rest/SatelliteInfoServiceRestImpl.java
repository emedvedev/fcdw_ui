// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.funcube.fcdw.server.dao.HexFrameDao;
import uk.org.funcube.fcdw.server.shared.SatelliteInfo;


@Controller
@RequestMapping(value = "/data/info")
public class SatelliteInfoServiceRestImpl {
	
	@Autowired
	HexFrameDao hexFrameDao;
	
	// get all data for one orbit for a given satellite
	@RequestMapping(value = "/{satelliteId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SatelliteInfo getAllWodForSatellite(
			@PathVariable(value = "satelliteId") Long satelliteId) {
		
		Long sequenceNumber = hexFrameDao.getMaxSequenceNumber(satelliteId);
		
		SatelliteInfo satelliteInfo = new SatelliteInfo();
		satelliteInfo.setSequenceNumber(sequenceNumber);
		
		return satelliteInfo;
		
	}
	

	private HttpHeaders addAccessControllAllowOrigin() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return headers;
	}
}
