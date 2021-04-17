package ru.innovat.integration;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innovat.config.ConfigControllerTest;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.major.Project;
import ru.innovat.models.support.Messages;
import ru.innovat.search.EventSearch;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IntegrationTest extends ConfigControllerTest {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    EventSearch eventSearch;

    @Autowired
    EventService eventService;

    @Autowired
    PersonService personService;

    @Autowired
    ProjectService projectService;


    @Before
    public void dataPreparation() {
        Event event1 = Event.builder()
                            .nameEvent("Хакатон")
                            .importanceEvent("важно")
                            .description("Только для новых разработчиков")
                            .dateEvent("2021:02:28")
                            .prizes("бонус на собеседовании")
                            .siteEvent("hackaton.ru")
                            .idTypeEvent(1)
                            .build();
        Event event2 = Event.builder()
                            .nameEvent("Доклады по новым технологиям")
                            .importanceEvent("важно")
                            .description("Вход своодный")
                            .dateEvent("2021:02:28")
                            .prizes("мелкие призы от компаний")
                            .siteEvent("event.ru")
                            .idTypeEvent(1)
                            .build();

        Person newPerson = Person.builder()
                                 .surname("Иванов")
                                 .name("Олег")
                                 .thirdName("Игоревич")
                                 .build();

        Project newProject = Project.builder()
                                    .nameProject("Телефон с зарядкой по FI-WI")
                                    .essenceInnovations("Зарядка телефонов по FI-WI")
                                    .solutionProblems("Зарядка телефона по кабелю")
                                    .levelSolution("Глобальный")
                                    .competitiveAdvantages("Отсутсвие конкуренции")
                                    .build();

        projectService.addProject(newProject);
        personService.addPerson(newPerson);
        eventService.addEvent(event1);
        eventService.addEvent(event2);
    }

    @After
    public void dataDelete() {
        for (Event event: eventService.eventList()
        ) {
            eventService.deleteEvent(event.getId_event());
        }
        for(Person person: personService.personList()){
            personService.deletePerson(person.getId_person());
        }
        for(Project project: projectService.projectList()){
            projectService.deleteProject(project.getId_project());
        }
    }

    @WithMockUser(username = "Igor", password = "pwd", roles = "USER")
    public void integration() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        //checking access without login
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Ivan")
                .param("username", "vanya6222")
                .param("lastName", "Ivanov")
                .param("password", "1111")
                .param("passwordConfirm", "1111")
                .param("eMail", "stoletov9229225.dk@gmail.com")
                .sessionAttr("userForm", new AppUser())
        )
                .andExpect(status().isOk());

//        //confirm email
//        mockMvc.perform(get("/confirm-account?token=" + (registrationService.tokenList().get(registrationService
//                .tokenList().size() - 1).toString())));
//
//        //login
//        mockMvc.perform(post("/login")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("username", "vanya6222")
//                .param("password", "1111"));

        //send message to support
        mockMvc.perform(post("/help/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "Hello! how i can add a person to my project?")
                .sessionAttr("newMessage", new Messages())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/help"));
        mockMvc.perform(get("/help"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("messages", hasItem(
                        allOf(
                                hasProperty("userMessage", is(true)),
                                hasProperty("message", is("Hello! how i can add a person to" +
                                        " my project?"))
                        )
                )));

        //add New Organization
        int organizationListSize = organizationService.organizationList().size();
        int newAddedOrganization = 1;
        mockMvc.perform(post("/organization/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nameOrganization", "Политех")
                .param("siteOrganization", "https://www.spbstu.ru/")
                .param("notesOrganization", "university in spb")
                .param("cityOrganization", "Saint-Petersburg")
                .sessionAttr("organization", new Organization())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/organization"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("organization/organization"))
               .andExpect(model().attribute("organizationList", hasSize(organizationListSize + newAddedOrganization)))
               .andExpect(model().attribute("organizationList", Matchers.hasItem(
                        allOf(
                                hasProperty("nameOrganization", is("Политех")),
                                hasProperty("siteOrganization", is("https://www.spbstu.ru/")),
                                hasProperty("notesOrganization", is("university in spb"))
                        ))));

        //Search event
        Event lastEvent = eventService.eventList().get(eventService.eventList().size() - 1);
        String lastEventName = lastEvent.getNameEvent();
        int foundedEventsListSize = eventSearch.fuzzySearch(lastEventName).size();
        Event lastFoundedEvent = eventSearch.fuzzySearch(lastEventName).get(eventSearch.fuzzySearch(lastEventName)
                                            .size() - 1);
        mockMvc.perform(get("/event?search=" + lastEventName))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("event/event"))
               .andExpect(model().attribute("eventList", hasSize(foundedEventsListSize)))
               .andExpect(model().attribute("eventList", Matchers.hasItem(
                        allOf(
                                hasProperty("nameEvent", is(lastFoundedEvent.getNameEvent())),
                                hasProperty("scopeEvent", is(lastFoundedEvent.getScopeEvent())),
                                hasProperty("description", is(lastFoundedEvent.getDescription()))
                        ))));

        //Person edit
        List<Person> personList = personService.personList();
        int lastIdPerson = personList.get(personList.size() - 1).getId_person();
        mockMvc.perform(post("/person/{id}/update", lastIdPerson)
               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
               .param("surname", "Бугров")
               .param("name", "Алекс")
               .param("thirdName", "Абрамович")
               .param("phoneNumber", "88005553535")
               .param("dateOfBirth", "1997-28-02")
               .param("eMail", "maksim99@gmail.com")
               .param("facebook", "Фейсбук")
               .param("vk", "ВК")
               .param("rating", "10")
               .param("twitter", "Твитер")
               .param("comment", "комментарий")
               .sessionAttr("person", new Person())
        )
               .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/person/{id}", lastIdPerson))
               .andExpect(status().isOk())
               .andExpect(view().name("person/onePerson"))
               .andExpect(model().attribute("person",
                        allOf(
                                hasProperty("name", is("Алекс")),
                                hasProperty("surname", is("Бугров"))
                        )));

        //Delete Project
        List<Project> projectList = projectService.projectList();
        int projectLastId = projectList.get(projectList.size() - 1).getId_project();
        mockMvc.perform(get("/project/{id}/delete", projectLastId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
