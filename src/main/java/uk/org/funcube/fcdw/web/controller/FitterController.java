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

import uk.org.funcube.fcdw.server.dao.FitterMessageDao;
import uk.org.funcube.fcdw.server.model.FitterMessage;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.shared.ValMinMax;

@Controller
@RequestMapping("fitter")
public class FitterController extends AbstractService {

	@Autowired
	private FitterMessageDao fitterMessageDao;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fitter(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		List<ValMinMax> fitter = new ArrayList<ValMinMax>();

		List<FitterMessage> fitterMessages = fitterMessageDao.findBySatelliteId(satelliteId, false);

		for (FitterMessage fitterMessage : fitterMessages) {
			final String messageText = fitterMessage.getMessageText();
			final String shortText = messageText.length() > 60 ? messageText.substring(0, 60) : null;
			fitter.add(new ValMinMax(messageText, SDF.format(fitterMessage.getLastReceived()), shortText, null));
		}

		ModelAndView mv = new ModelAndView("fitter");
		mv.addObject("fitter", fitter);
		mv.addObject("satelliteId", satelliteId);
		return mv;
	}

	public void setFitterMessageDao(FitterMessageDao fitterMessageDao) {
		this.fitterMessageDao = fitterMessageDao;
	}

}
