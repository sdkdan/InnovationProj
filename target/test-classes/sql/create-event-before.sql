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

INSERT INTO `event` (`Id_event`, `Site_event`, `Name_event`, `Importance_event`, `Scope_event`, `Description`,
 `Id_type_event`, `Phone_number`, `Date_event`, `Date_for_month`, `Date_for_the_week`, `Comment`, `Prizes`,
  `Location_event`) VALUES ('1', 'testsite', 'test', 'test', 'test', 'test', NULL, '1111', '28.12.1997', NULL, NULL,
   'test', 'test', 'test');
INSERT INTO `event` (`Id_event`, `Site_event`, `Name_event`, `Importance_event`, `Scope_event`, `Description`,
 `Id_type_event`, `Phone_number`, `Date_event`, `Date_for_month`, `Date_for_the_week`, `Comment`, `Prizes`,
  `Location_event`) VALUES ('2', 'testsite', 'test2', 'test', 'test', 'test', NULL, '1111', '28.12.1997', NULL, NULL,
   'test', 'test', 'test');