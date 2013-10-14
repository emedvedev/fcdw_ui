package uk.org.funcube.fcdw.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import uk.org.funcube.fcdw.server.dao.UserDao;

public class EmailNotDuplicateConstraintValidator implements ConstraintValidator<EmailNotDuplicate, String> {

	@Autowired
	UserDao userDao;

	@Override
	public void initialize(EmailNotDuplicate emailNotDuplicate) {
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isValid(String emailNotDuplicateField, ConstraintValidatorContext cxt) {
		if (emailNotDuplicateField == null) {
			return false;
		}
		return (userDao.findByUserName(emailNotDuplicateField) == null);
	}
}
