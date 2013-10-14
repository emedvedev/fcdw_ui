package uk.org.funcube.fcdw.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import uk.org.funcube.fcdw.server.dao.UserDao;

public class SiteNameNotDuplicateConstraintValidator implements ConstraintValidator<SiteNameNotDuplicate, String> {

	@Autowired
	UserDao userDao;

	@Override
	public void initialize(SiteNameNotDuplicate siteNameNotDuplicate) {
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isValid(String siteNameNotDuplicateField, ConstraintValidatorContext cxt) {
		if (siteNameNotDuplicateField == null) {
			return false;
		} else if (siteNameNotDuplicateField.isEmpty()) {
			return true;
		} else {
			return (userDao.findBySiteId(siteNameNotDuplicateField).isEmpty());
		}
	}
}
