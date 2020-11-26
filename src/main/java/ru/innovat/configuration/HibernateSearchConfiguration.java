package ru.innovat.configuration;

import javax.persistence.EntityManager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.innovat.search.PersonSearch;


@EnableAutoConfiguration
@Configuration
public class HibernateSearchConfiguration {
    private final EntityManager entityManager;

    public HibernateSearchConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    PersonSearch hibernateSearchService() {
        PersonSearch hibernateSearchService = new PersonSearch(entityManager);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}