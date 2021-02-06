package ru.innovat.search;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Person;
import ru.innovat.service.major.PersonService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@AllArgsConstructor
@Slf4j
public class PersonSearch {
    private final PersonService personService;
    @PersistenceContext
    private final EntityManager entityManager;
    private final static int DISTANCE_UP_TO_SEARCH = 1;
    private final static int PREFIX_LENGTH = 1;

    @SuppressWarnings("unchecked")
    public List<Person> fuzzySearch(String searchTerm) {
        int distanceUpToSearch = 1;
        int prefixLength = 1;
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            log.error("fuzzy search in person, fullTextEntityManager indexes are not working");
        }
        if (searchTerm != null && searchTerm.length() > 0) {
            QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Person.class).get();
            Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(DISTANCE_UP_TO_SEARCH).withPrefixLength(PREFIX_LENGTH)
                    .onFields("name", "surname")
                    .matching(searchTerm).createQuery();
            javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Person.class);
            return Optional.ofNullable(jpaQuery.getResultList()).orElseThrow(NoResultException::new);
        } else {
            return personService.personList();
        }
    }

}