package uk.org.funcube.fcdw.server.model;

import org.apache.commons.lang3.StringUtils;

abstract class AbstractEntity {

	protected static final String convertLongToBinary(final Long value, final int length) {
		return StringUtils.leftPad(Long.toString(value, 2), length, "0");
	}

}