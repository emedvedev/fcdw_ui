package uk.org.funcube.fcdw.server.dao;

import javax.persistence.EntityNotFoundException;

/**
 * The base interface for a data access object.
 * 
 * @param <T> the type of the entity to be stored
 * @param <K> the type of the unique identifier for an entity
 */
public interface DataAccessObject<T, K> {
    /**
     * Retrieve an entity by its unique identifier.
     * 
     * @param id the unique identifier of the required entity
     * 
     * @return the entity found
     * 
     * @throws EntityNotFoundException if the entity cannot be found
     */
    T findById(final K id) throws EntityNotFoundException;

    /**
     * Save a given entity.
     * 
     * @param entity the entity to be saved
     * 
     * @return the saved entity
     */
    T save(T entity);
}
