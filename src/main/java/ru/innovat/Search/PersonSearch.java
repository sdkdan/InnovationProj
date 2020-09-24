

        package ru.innovat.Search;

        import org.apache.lucene.search.Query;
        import org.hibernate.SessionFactory;
        import org.hibernate.search.FullTextSession;
        import org.hibernate.search.jpa.FullTextEntityManager;
        import org.hibernate.search.query.dsl.QueryBuilder;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;
        import ru.innovat.models.Person;

        import javax.persistence.EntityManager;
        import javax.persistence.NoResultException;
        import javax.persistence.PersistenceContext;
        import javax.transaction.Transactional;
        import java.util.List;
        import org.hibernate.search.jpa.Search;

@Repository
@Transactional
public class PersonSearch {
    // Spring will inject here the entity manager object
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public PersonSearch(EntityManager entityManager) {
        super();
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
    public List<Person> fuzzySearch(String searchTerm) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Person.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Person.class);

        //execute search

        List<Person> BaseballCardList = null;
        try {
            BaseballCardList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;//do nothing

        }

        return BaseballCardList;


    }
}