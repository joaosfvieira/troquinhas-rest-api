INSERT INTO `album_tipo` VALUES (1, '2022-12-01', 'Esse e um album de teste', 'Copa do Mundo 2022');
INSERT INTO `album_tipo` VALUES (2, '2022-12-01', 'Esse e um album de teste', 'Cavaleiros do Zodiaco');

INSERT INTO `role` (name) VALUES ('Admin'), ('Developer'), ('User');

INSERT INTO `contatos` VALUES (1, 'telefone', '84991266475');
INSERT INTO `pontos_troca` (nome) VALUES ('Natal Shopping');

INSERT INTO `colecionador` (nome, contato_id, pontos_troca_id, sobrenome, email, senha) VALUES ('Joao', 1, 1, 'Souza', 'admin@teste', '$2a$10$0t101SmjvSI3xOnylkAsL.KUwr.CThEc7nOk31O1ORdAfuayBKQZi');
INSERT INTO `colecionador` (nome, sobrenome, email, senha) VALUES ('Paulo', 'Barboza', 'dev@teste', '$2a$10$0t101SmjvSI3xOnylkAsL.KUwr.CThEc7nOk31O1ORdAfuayBKQZi');
INSERT INTO `colecionador` (nome, sobrenome, email, senha) VALUES ('Rita', 'de Cássia', 'user@teste', '$2a$10$0t101SmjvSI3xOnylkAsL.KUwr.CThEc7nOk31O1ORdAfuayBKQZi');

INSERT INTO `reputacao_colecionador` VALUES (1, 9);
INSERT INTO `album` VALUES (1, 1, 1);
INSERT INTO `album` VALUES (2, 2, 1);

INSERT INTO `figurinhas` (nome, raridade, album_tipo_id) VALUES ('Cristiano Ronaldo', 'Legend', 1);
INSERT INTO `figurinhas` (nome, raridade, album_tipo_id) VALUES ('Neymar Jr.', 'Legend', 1);
INSERT INTO `figurinhas` (nome, raridade, album_tipo_id) VALUES ('Vini Júnior', 'Ouro', 1);
INSERT INTO `figurinhas` (nome, raridade, album_tipo_id) VALUES ('Saint Seya', 'Legend', 2);
INSERT INTO `figurinhas` (nome, raridade, album_tipo_id) VALUES ('Ikki de Phoenix', 'Legend', 2);

INSERT INTO `colecionador_roles` VALUES (1, 1);
INSERT INTO `colecionador_roles` VALUES (2, 2);
INSERT INTO `colecionador_roles` VALUES (3, 3);