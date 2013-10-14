// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao.impl;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.AuthorityDao;
import uk.org.funcube.fcdw.server.model.Authority;
import uk.org.funcube.fcdw.server.model.AuthorityEntity;

public class AuthorityDaoImpl
	extends AbstractDataAccessObject<Authority, AuthorityEntity, String> 
	implements AuthorityDao {

	public AuthorityDaoImpl() {
	    // Nothing to be done.
	}

	@Override
	public Authority saveOrUpdate(final Authority authority) {
		return super.save(authority);
	}
}
