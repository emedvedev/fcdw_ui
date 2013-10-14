package uk.org.funcube.fcdw.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatLongConstraintValidator implements ConstraintValidator<LatLong, String> {

	@Override
	public void initialize(LatLong latLong) {
	}

	@Override
	public boolean isValid(String latLongField, ConstraintValidatorContext cxt) {
		if (latLongField == null) {
			return false;
		}
		return latLongField.matches("^-?[0-9]{1,3}(?:\\.[0-9]{1,4})?$");
	}
}
