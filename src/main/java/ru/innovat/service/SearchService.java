package ru.innovat.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.search.EventSearch;
import ru.innovat.search.OrganizationSearch;
import ru.innovat.search.PersonSearch;
import ru.innovat.search.ProjectSearch;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {
  ProjectService projectService;
  ProjectSearch projectSearch;
  PersonService personService;
  PersonSearch personSearch;
  EventService eventService;
  EventSearch eventSearch;
  OrganizationService organizationService;
  OrganizationSearch organizationSearch;

    @Transactional
    public ArrayList<Project> searchProjectList(String search){
        if (search != null) {
            if (search.length() > 0) {
                return projectSearch.fuzzySearch(search);
            } else return projectService.projectList();
        } else return projectService.projectList();
    }

    @Transactional
    public List<Person> searchPersonList(String search){
        if (search != null) {
            if (search.length() > 0) {
                return personSearch.fuzzySearch(search);
            } else return personService.personList();
        } else return personService.personList();
    }

    @Transactional
    public List<Event> searchEventList(String search){
        if (search != null) {
            if (search.length() > 0) {
                return eventSearch.fuzzySearch(search);
            } else return eventService.eventList();
        } else return eventService.eventList();
    }

    @Transactional
    public List<Organization> searchListOrganization(String search) {
        if (search != null) {
            if (search.length() > 0) {
                return organizationSearch.fuzzySearch(search);
            } else return organizationService.organizationList();
        } else return organizationService.organizationList();
    }
}
