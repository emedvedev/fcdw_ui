package uk.org.funcube.fcdw.server.dao;

import java.util.List;

import uk.org.funcube.fcdw.server.model.User;
import uk.org.funcube.fcdw.server.model.UserEntity;

/**
 * Data Access Object for retrieving and persisting user data.
 */
public interface UserDao extends DataAccessObject<User, String> {

	List<User> findByEither(final String userName, final String siteId);

	List<User> findBySiteId(final String siteId);

	User getByUserName(final String userName);

	User saveOrUpdate(UserEntity user);

	List<User> getRanking(int i);

	User findByUserName(String emailNotDuplicateField);

}
