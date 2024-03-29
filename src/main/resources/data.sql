INSERT INTO family (id, name) VALUES
    (1, 'Первая семья');
INSERT INTO family (id, name) VALUES
    (2, 'Вторая семья');

INSERT INTO shopping (id, data, how_many, name, store, family_id) VALUES
(1,'2004-10-19 10:23:54','100 гр', 'покупка 1, первой семьи','Шестёрочка', 1),
(2,'2004-10-19 10:23:54','100 гр', 'покупка 2, первой семьи','Шестёрочка', 1),
(3,'2004-10-19 10:23:54','100 гр', 'пакет для пакетовб 2-й семьи','Шестёрочка', 2);


INSERT INTO users ( name, password) VALUES
('123', '{noop}123');

INSERT INTO user_family(family_id, user_id) VALUES
(1,1);
INSERT INTO user_family(family_id, user_id) VALUES
 (2,1);

insert into user_role (user_id, role) VALUES
(1, 'USER');

SELECT setval('"shopping_id_seq"', (SELECT MAX(id) FROM public."shopping")+1);
SELECT setval('"family_id_seq"', (SELECT MAX(id) FROM public."family")+1);
SELECT setval('"users_id_seq"', (SELECT MAX(id) FROM public."users")+1);