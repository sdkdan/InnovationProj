delete from messages;
delete from verificationtoken;
delete from users;
delete from blocked;

INSERT INTO `users` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `id_role`, `enabled`)
VALUES ('1', 'test0', '$3a$10$zK5vEVucBbKdA895SoorLum6rKIATOwCeTYt7uUALCWOo8HUsPHme', 'test@test.test', 'test', 'test',
'1', '1');

INSERT INTO `blocked` (`id_blocked`, `id_user`, `startdate`, `enddate`, `comment`)
VALUES ('1', '1', '2020-11-18', '2020-11-18', 'test');

UPDATE `users` SET `id_blocked` = '1' WHERE `users`.`id_user` = 1;

INSERT INTO `users` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `id_role`, `enabled`, `id_blocked`
) VALUES ('2', 'test1', '$2a$10$zK5vEVucBbKdA895SoorLum6rKIATOwCeTYt7uUALCWOo8HUsPHme', 'test@test.test', 'test', 'test',
'1', '1', NULL);

INSERT INTO `users` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `id_role`, `enabled`, `id_blocked`
) VALUES ('3', 'test2', '$22a$10$zK5vEVucBbKdA895SoorLum6rKIATOwCeTYt7uUALCWOo8HUsPHme', 'test1@test.test', 'test', 'test',
'2', '1', NULL);

