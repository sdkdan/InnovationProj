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

INSERT INTO `project` (`Id_project`, `Name_project`, `Essence_innovation`, `Solution_problem`, `Level_solution`,
`Competitive_advantages`, `Start_date`, `Current_stage`, `Expertise_project`, `Project_description`, `Site_project`,
`Number_phone_project`) VALUES ('1', 'База данных инновационных проектов', 'Суть инновации:...', 'Решение проблемы:...',
'Уровень решения:...', 'Конкурентные преимущества:...', '2020-10-20', 'Текущая стадия:...', 'Экспертиза проекта:...',
'Описание проекта:...', 'dl.spbstu.ru', '+79213004090');

INSERT INTO `organization` (`Id_organization`, `Name_organization`, `Site_organization`, `City_organization`,
`Notes_organization`) VALUES ('1', 'Поликек', 'edu.spbstu.ru', 'Санкт-Петербург', 'Место для анимечников');

 INSERT INTO `project` (`Id_project`, `Name_project`, `Essence_innovation`, `Solution_problem`, `Level_solution`,
 `Competitive_advantages`, `Start_date`, `Current_stage`, `Expertise_project`, `Project_description`, `Site_project`,
 `Number_phone_project`) VALUES ('2', 'Просмотр Gachimuchi', 'Суть инновации', 'Решение проблемы', 'Уровень решения',
 'Конкурентные преимущества', '2020-11-22', 'Текущая стадия', 'Экспертиза проекта', 'Описание проекта', 'Gachi.com',
 '88005553535');

INSERT INTO `person` (`Id_person`, `Surname`, `Name`, `Third_name`, `Phone_number`, `Date_of_birth`, `id_status_person`,
 `E_mail`, `Facebook`, `VK`, `Rating`, `Twitter`, `Comment`) VALUES ('1', 'Максим', 'Марцинкевич', 'Абрамович',
 '88005553535', '1997-28-02', '1', 'SlavaUkraine@geroyam.slava', 'Фейсбук', 'ВК', '10', 'Твитер', 'комментарий');