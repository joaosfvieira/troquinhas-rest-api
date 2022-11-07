INSERT INTO `role` (name) VALUES ('Admin'), ('Developer'), ('User');
INSERT INTO `colecionador` (nome, sobrenome, email, senha) VALUES ('Joao', 'Souza', 'admin@teste', '$2a$10$0t101SmjvSI3xOnylkAsL.KUwr.CThEc7nOk31O1ORdAfuayBKQZi');
INSERT INTO `colecionador` (nome, sobrenome, email, senha) VALUES ('Paulo', 'Barboza', 'dev@teste', '$2a$10$0t101SmjvSI3xOnylkAsL.KUwr.CThEc7nOk31O1ORdAfuayBKQZi');
INSERT INTO `colecionador` (nome, sobrenome, email, senha) VALUES ('Rita', 'de Cássia', 'user@teste', '$2a$10$0t101SmjvSI3xOnylkAsL.KUwr.CThEc7nOk31O1ORdAfuayBKQZi');
INSERT INTO `colecionador_roles` VALUES (1, 1);
INSERT INTO `colecionador_roles` VALUES (2, 2);
INSERT INTO `colecionador_roles` VALUES (3, 3);