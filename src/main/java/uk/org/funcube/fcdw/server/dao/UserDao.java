// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

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
