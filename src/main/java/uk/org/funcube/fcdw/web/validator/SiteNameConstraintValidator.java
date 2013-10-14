package uk.org.funcube.fcdw.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SiteNameConstraintValidator implements ConstraintValidator<SiteName, String> {

	@Override
	public void initialize(SiteName siteName) {
	}

	@Override
	public boolean isValid(String siteNameField, ConstraintValidatorContext cxt) {
		if (siteNameField == null) {
			return false;
		}
		return siteNameField.matches("[0-9a-zA-Z_]*");
	}
}
