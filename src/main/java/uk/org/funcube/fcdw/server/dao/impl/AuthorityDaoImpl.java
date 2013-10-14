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
