package uk.org.funcube.fcdw.server.dao;
import uk.org.funcube.fcdw.server.model.Authority;

/**
 * Data Access Object for retrieving and persisting user data.
 */
public interface AuthorityDao extends DataAccessObject<Authority, String> {

	Authority saveOrUpdate(Authority authority);

}
