//package ru.innovat.service.major;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.innovat.models.major.Event;
//import ru.innovat.models.major.Organization;
//import ru.innovat.models.major.Person;
//import ru.innovat.models.major.Project;
//import ru.innovat.search.EventSearch;
//import ru.innovat.search.OrganizationSearch;
//import ru.innovat.search.PersonSearch;
//import ru.innovat.search.ProjectSearch;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class SearchService {
//    private final ProjectService projectService;
//    private final ProjectSearch projectSearch;
//    private final PersonService personService;
//    private final PersonSearch personSearch;
//    private final EventService eventService;
//    private final EventSearch eventSearch;
//    private final OrganizationService organizationService;
//    private final OrganizationSearch organizationSearch;
//
//    @Transactional
//    public List<Project> searchProjectList(String search) {
//      return search != null && search.length() > 0 ? projectSearch.fuzzySearch(search) : projectService.projectList();
//    }
//
//    @Transactional
//    public List<Person> searchPersonList(String search) {
//        return search != null && search.length() > 0 ? personSearch.fuzzySearch(search) : personService.personList();
//    }
//
//    @Transactional
//    public List<Event> searchEventList(String search) {
//        return search != null && search.length() > 0 ? eventSearch.fuzzySearch(search) : eventService.eventList();
//    }
//
//    @Transactional
//    public List<Organization> searchListOrganization(String search) {
//        return search != null && search.length() > 0 ? organizationSearch.fuzzySearch(search) :
//                organizationService.organizationList();
//    }
//}
