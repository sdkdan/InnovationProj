delete from blocked;
delete from messages;
delete from verificationtoken;
delete from users;

INSERT INTO `users` (`id_user`, `username`, `password`, `email`, `name`, `last_name`, `id_role`, `enabled`, `id_blocked`
) VALUES ('1', 'test', '$2a$10$zK5vEVucBbKdA895SoorLum6rKIATOwCeTYt7uUALCWOo8HUsPHme', 'test@test.test', 'test', 'test',
'1', '1', NULL);

-- INSERT INTO `verificationtoken` (`id_token`, `token`, `id_user`, `expirydate`) VALUES ('1', 'testToken', '1', '2020-11-19');
-- INSERT INTO `verificationtoken` (`id_token`, `token`, `id_user`, `expirydate`) VALUES ('1', 'test', '2', '2020-11-19');
INSERT INTO `verificationtoken` (`id_token` ,`expirydate`, `token`, `id_user`) VALUES ('1','2020-11-18', 'testToken', '1');