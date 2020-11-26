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

INSERT INTO `person` (`Id_person`, `Surname`, `Name`, `Third_name`, `Phone_number`, `Date_of_birth`, `id_status_person`,
 `E_mail`, `Facebook`, `VK`, `Rating`, `Twitter`, `Comment`) VALUES ('1', 'Максим', 'Марцинкевич', 'Абрамович',
 '88005553535', '1997-28-02', '1', 'SlavaUkraine@geroyam.slava', 'Фейсбук', 'ВК', '10', 'Твитер', 'комментарий');
 INSERT INTO `person` (`Id_person`, `Surname`, `Name`, `Third_name`, `Phone_number`, `Date_of_birth`, `id_status_person`,
 `E_mail`, `Facebook`, `VK`, `Rating`, `Twitter`, `Comment`) VALUES ('2', 'Максим1', 'Марцинкевич', 'Абрамович',
 '88005553535', '1997-28-02', '1', 'SlavaUkraine@geroyam.slava', 'Фейсбук', 'ВК', '10', 'Твитер', 'комментарий');