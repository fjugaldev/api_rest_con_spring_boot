# Tabla "role"
INSERT INTO role (id, code, name) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf', 'ADMIN', 'Admin');
INSERT INTO role (id, code, name) VALUES ('a56c332e-56e6-487d-ba23-7e3819fe293f', 'USER', 'Usuario');
INSERT INTO role (id, code, name) VALUES ('f75e9881-1a8c-4897-a64e-dc6cc4282003', 'MANAGER', 'Manager');

# Tabla "privilege"
INSERT INTO privilege (id, code, name) VALUES ('1c529af9-8164-4904-9e54-e5647f1ba626', 'EDIT_PRIVILEGE', 'Privilegio de Edición');
INSERT INTO privilege (id, code, name) VALUES ('36739fb0-c06c-4ba8-8892-316936db159b', 'READ_PRIVILEGE', 'Privilegio de Lectura');
INSERT INTO privilege (id, code, name) VALUES ('454dbc6d-4f77-4e00-aec5-c205ff486c32', 'DELETE_PRIVILEGE', 'Privilegio de Borrado');
INSERT INTO privilege (id, code, name) VALUES ('f9ada705-14bb-4cc9-b2e4-04c6837edb4d', 'CREATE_PRIVILEGE', 'Privilegio de Escritura');

# Tabla "role_privileges"
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf', '1c529af9-8164-4904-9e54-e5647f1ba626');
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf', '36739fb0-c06c-4ba8-8892-316936db159b');
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf', '454dbc6d-4f77-4e00-aec5-c205ff486c32');
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf', 'f9ada705-14bb-4cc9-b2e4-04c6837edb4d');
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('a56c332e-56e6-487d-ba23-7e3819fe293f', '36739fb0-c06c-4ba8-8892-316936db159b');
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('f75e9881-1a8c-4897-a64e-dc6cc4282003', '36739fb0-c06c-4ba8-8892-316936db159b');
INSERT INTO role_privileges (role_id, privilege_id) VALUES ('f75e9881-1a8c-4897-a64e-dc6cc4282003', 'f9ada705-14bb-4cc9-b2e4-04c6837edb4d');
