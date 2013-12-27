// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.org.funcube.fcdw.server.dao.HighPrecisionDao;
import uk.org.funcube.fcdw.server.dao.WholeOrbitDataDao;
import uk.org.funcube.fcdw.server.model.HPEntity;
import uk.org.funcube.fcdw.server.model.WODEntity;

@Controller
@RequestMapping("graph")
public class GraphController {
	
	@Autowired
	HighPrecisionDao highPrecisionDao;
	
	@Autowired
	WholeOrbitDataDao wholeOrbitDataDao;

	public final void setHighPrecisionDao(HighPrecisionDao highPrecisionDao) {
		this.highPrecisionDao = highPrecisionDao;
	}
	
	public final void setWholeOrbitDataDao(WholeOrbitDataDao wholeOrbitDataDao) {
		this.wholeOrbitDataDao = wholeOrbitDataDao;
	}

	@RequestMapping(value = "/highres", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<HPEntity> listHighResolution() {
		return (List<HPEntity>) highPrecisionDao.getLatestFourMinutes(0);
	}
	
	@RequestMapping(value = "/wod", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<WODEntity> listWOD() {
		return (List<WODEntity>) wholeOrbitDataDao.getLatestOrbit(2L);
	}

}
