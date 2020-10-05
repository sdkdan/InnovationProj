package ru.innovat.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import org.hibernate.search.jpa.Search;
import ru.innovat.models.Organization;

@Repository
@Transactional
public class OrganizationSearch {
    // Spring will inject here the entity manager object
    @PersistenceContext
    private final EntityManager entityManager;


    public OrganizationSearch(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Organization> fuzzySearch(String searchTerm) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Organization.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name_organization","city_organization")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Organization.class);

        //execute search

        List<Organization> personList = null;
        try {
            return  (List<Organization>)jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;//do nothing

        }

        return null;


    }
}