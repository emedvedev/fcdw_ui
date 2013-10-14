// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A concrete data access object that uses the JPA framework for underlying persistence.
 * 
 * @param <I> the interface type of entity to be persisted
 * @param <C> the concrete type being persisted
 * @param <K> the type of the unique identifier for an entity
 */
public abstract class AbstractDataAccessObject<I, C extends I, K> implements DataAccessObject<I, K> {
    /** The component responsible for managing entity instances. */
    private EntityManager entityManager;

    /** The class of entity being managed. */
    private final Class<C> entityBeanType;

    /**
     * Construct the data access object.
     */
    @SuppressWarnings("unchecked")
    public AbstractDataAccessObject() {
        this.entityBeanType =
                (Class<C>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * {@inheritDoc}
     */
    
    public I findById(final K id) {
        final I entity = entityManager.find(entityBeanType, id);

        if (entity == null) {
            throw new EntityNotFoundException("No instance with an ID of " + id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    
    public I save(I entity) {
        entity = entityManager.merge(entity);

        return entity;
    }

    /**
     * Specify the entity manager to be used.
     * 
     * @param em the entity manager
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager em) {
        entityManager = em;
    }

    /**
     * The entity manager that is to be used for all data access.
     * 
     * @return the entity manager
     */
    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Find a collection of results.
     * 
     * @param query the query to use
     * 
     * @return a potentially empty list of results
     */
    @SuppressWarnings("unchecked")
    protected List<I> findMany(final Query query) {
        return query.getResultList();
    }

    /**
     * Find a collection of results.
     * 
     * @param query the query to use
     * @param parameters a map of named parameters for the query
     * 
     * @return a potentially empty list of results
     */
    protected List<I> findMany(final String queryString, final Map<String, Object> parameters) {
        final Query query = entityManager.createQuery(queryString.toString());
        for (final Map.Entry<String, Object> parameter : parameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }

        return findMany(query);
    }

    /**
     * Find a single result for a query.
     * 
     * @param queryString
     * @param parameters
     * @return either a single result of the correct type or null if no result found.
     */
    @SuppressWarnings("unchecked")
    protected I getSingleResult(final String queryString, final Map<String, Object> parameters) {
        final Query query = entityManager.createQuery(queryString.toString());
        for (final Map.Entry<String, Object> parameter : parameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }

        try {
            return (I)query.getSingleResult();
        }
        catch (final NoResultException e) {
            return null;
        }
    }

    /**
     * Find a unique entity.
     * 
     * @param query the query to use
     * @param parameters a map of named parameters for the query
     * 
     * @return the entity found
     * 
     * @throws EntityNotFoundException if the entity cannot be found
     * @throws NonUniqueResultException if more than one entity is found
     */
    protected I findOneOnly(final String queryString, final Map<String, Object> parameters) {
        final Query query = entityManager.createQuery(queryString.toString());
        for (final Map.Entry<String, Object> parameter : parameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }

        final List<I> results = findMany(query);
        if (results.isEmpty()) {
            throw new EntityNotFoundException();
        }
        else if (results.size() > 1) {
            throw new NonUniqueResultException();
        }

        return results.get(0);
    }
    
    public void delete(final I entity) {
    	entityManager.remove(entity);
    }
}


