package ru.innovat.search;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Project;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProjectSearch {
    @PersistenceContext
    private final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Project> fuzzySearch(String searchTerm) {
        int distanceUpToSearch = 1;
        int prefixLength = 1;
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Project.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(distanceUpToSearch).withPrefixLength(prefixLength)
                .onFields("nameProject")
                .matching(searchTerm).createQuery();
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Project.class);
        return Optional.ofNullable(jpaQuery.getResultList()).orElseThrow(NoResultException::new);
    }

}