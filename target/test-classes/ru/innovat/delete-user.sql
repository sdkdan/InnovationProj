delete from blocked;
delete from messages;
delete from verificationtoken;
delete from users;

INSERT INTO users (id_user, username, password, email, name, last_name, id_role, enabled, id_blocked
) VALUES ('2', 'test1', '$2a$10$zK5vEVucBbKdA895SoorLum6rKIATOwCeTYt7uUALCWOo8HUsPHme', 'test@test.test', 'test', 'test',
'2', '0', NULL);

INSERT INTO verificationtoken (id_token ,expirydate, token, id_user) VALUES ('31','2020-11-18', 'testtoken', '2');