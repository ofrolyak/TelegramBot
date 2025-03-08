package com.tatko.api.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Service
public class DaoService {

    /**
     * The {@code entityManager} is a persistence context used for
     * interacting with the database within
     * the Java Persistence API (JPA) framework.
     * It provides methods for creating queries, persisting, removing,
     * and managing entities.
     *
     * This variable is injected by the JPA provider
     * using the {@code @PersistenceContext}
     * annotation, enabling dependency injection for seamless integration
     * with the persistence context.
     *
     * In the context of this service, the {@code entityManager} is utilized
     * for operations such as building criteria queries, generating predicates
     * for filtering, and retrieving paginated results of entities.
     */
    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Filters and retrieves a paginated list of entities of the specified type
     * based on the given predicate and pageable parameters.
     *
     * @param predicateCreator a functional interface for creating
     *                         a filter predicate,
     *                         which operates on a CriteriaBuilder and
     *                         a root element.
     * @param pageable         an instance of Pageable, defining pagination
     *                         parameters
     *                         such as page number and size.
     * @param clazz            the class type of the entities to be filtered
     *                         and returned.
     * @param <T>              the type parameter representing the entity class.
     * @return a list of entities that match the filtering criteria,
     * limited by the pagination parameters.
     */
    public <T> List<T> entityFilter(
            final PredicateCreator predicateCreator,
            final Pageable pageable, final Class<T> clazz) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        Predicate predicate = predicateCreator.execute(criteriaBuilder, root);

        CriteriaQuery<T> select = criteriaQuery.select(root).where(predicate);
        TypedQuery<T> typedQuery = entityManager.createQuery(select);

        typedQuery.setFirstResult(pageable.getPageNumber());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<T> jpaEntityList = typedQuery.getResultList();

        return jpaEntityList;

    }
}
