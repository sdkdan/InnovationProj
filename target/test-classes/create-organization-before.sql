delete from organization;
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

delete from blocked;
delete from messages;
delete from verificationtoken;
delete from users;

INSERT INTO `users` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `id_role`, `enabled`, `id_blocked`
) VALUES ('1', 'test', '$2a$10$zK5vEVucBbKdA895SoorLum6rKIATOwCeTYt7uUALCWOo8HUsPHme', 'test@test.test', 'test', 'test',
'2', '1', NULL);
INSERT INTO `organization` (`Id_organization`, `Name_organization`, `Site_organization`, `City_organization`, `Notes_organization`)
 VALUES ('1', 'Политех', 'spbbu@kek.ru', 'Nursultan',  'DFDFD');
INSERT INTO `organization` (`Id_organization`, `Name_organization`, `Site_organization`, `City_organization`, `Notes_organization`)
 VALUES ('2', 'Spbpu', 'spb@kek.ru', 'Almaty',  'note');