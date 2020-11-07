package ru.innovat.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import ru.innovat.models.Project;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProjectSearch {

    @PersistenceContext
    private final EntityManager entityManager;


    public ProjectSearch(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    @SuppressWarnings("unchecked")
    public List<Project> fuzzySearch(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Project.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name_project")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Project.class);


        try {

            return (List<Project>) jpaQuery.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }

        return null;


    }
}