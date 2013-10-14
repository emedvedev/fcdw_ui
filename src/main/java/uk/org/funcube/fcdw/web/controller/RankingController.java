package uk.org.funcube.fcdw.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.dao.UserRankingDao;
import uk.org.funcube.fcdw.server.model.UserRanking;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.StringPair;

@Controller
@RequestMapping("ranking")
public class RankingController extends AbstractService {

	@Autowired
	private UserRankingDao userRankingDao;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView ranking(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		List<StringPair> ranking = new ArrayList<StringPair>();

		List<UserRanking> userRankings = userRankingDao.findBySatelliteId(satelliteId);

		for (UserRanking userRanking : userRankings) {
			ranking.add(new StringPair(userRanking.getSite(), userRanking.getNumber().toString()));
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
