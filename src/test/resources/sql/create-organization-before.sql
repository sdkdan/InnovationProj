-- delete from organization;
delete from organization_event;
delete from organization_person;
delete from organization_project;
delete from person_event;
delete from project_person;
delete from event_project;
delete from project;
delete from organization;
delete from person;
delete from event;

INSERT INTO `organization` (`Id_organization`, `Name_organization`, `Site_organization`, `City_organization`, `Notes_organization`)
 VALUES ('1', 'test1', 'spbbu@kek.ru', 'Nursultan',  'DFDFD');
 INSERT INTO `organization` (`Id_organization`, `Name_organization`, `Site_organization`, `City_organization`, `Notes_organization`)
 VALUES ('2', 'test2', 'spbbu1@kek.ru', 'Almaty',  'DFDFD');
