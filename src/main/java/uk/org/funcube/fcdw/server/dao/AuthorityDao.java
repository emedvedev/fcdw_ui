// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao;
import uk.org.funcube.fcdw.server.model.Authority;

/**
 * Data Access Object for retrieving and persisting user data.
 */
public interface AuthorityDao extends DataAccessObject<Authority, String> {

	Authority saveOrUpdate(Authority authority);

}
