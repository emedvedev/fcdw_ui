// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.model;

import org.apache.commons.lang3.StringUtils;

abstract class AbstractEntity {

	protected static final String convertLongToBinary(final Long value, final int length) {
		return StringUtils.leftPad(Long.toString(value, 2), length, "0");
	}

}