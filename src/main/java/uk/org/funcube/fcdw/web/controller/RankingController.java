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

import uk.org.funcube.fcdw.server.clock.UTCClock;
import uk.org.funcube.fcdw.server.dao.UserRankingDao;
import uk.org.funcube.fcdw.server.model.UserRanking;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.ValMinMax;

@Controller
@RequestMapping("ranking")
public class RankingController extends AbstractService {

	private static final int DAY_MILLIS = 24 * 60 * 60 * 1000;

	@Autowired
	private UserRankingDao userRankingDao;
	
	@Autowired
	private UTCClock clock;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView ranking(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		List<ValMinMax> ranking = new ArrayList<ValMinMax>();

		List<UserRanking> userRankings = userRankingDao.findBySatelliteId(satelliteId);
		
		int position = 1;
		int postedPosition = 0;
		Long oldNumber = 0L;
		Long newNumber = 0L;
		for (UserRanking userRanking : userRankings) {
			
			Date latestUploadDate = userRanking.getLatestUploadDate();
			
			String colour = "light-grey";
			
			if (clock.currentTime() - latestUploadDate.getTime() < 7 * DAY_MILLIS) {
				colour = "LawnGreen";
			} else if (clock.currentTime() - latestUploadDate.getTime() < 14 * DAY_MILLIS) {
				colour = "DarkOrange";
			}
			
			newNumber = userRanking.getNumber();
			
			if (oldNumber.longValue() != newNumber.longValue()) {
				postedPosition = position;
				ranking.add(new ValMinMax(userRanking.getSite(), newNumber.toString(),
						Integer.toString(postedPosition), colour));
				position++;
			}
			else {
				ranking.add(new ValMinMax(userRanking.getSite(), newNumber.toString(),
						Integer.toString(postedPosition), colour));
				position++;
			}

			oldNumber = newNumber;
		}

		ModelAndView mv = new ModelAndView("ranking");
		mv.addObject("ranking", ranking);
		mv.addObject("satelliteId", satelliteId);
		return mv;
	}

	public void setUserRankingDao(UserRankingDao userRankingDao) {
		this.userRankingDao = userRankingDao;
	}

}
