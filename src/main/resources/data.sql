INSERT INTO family (id, name) VALUES
    (2, 'Первая семья');
INSERT INTO family (id, name) VALUES
    (3, 'Вторая семья');

INSERT INTO shopping (id, data, how_many, name, store, family_id) VALUES
(1,'2004-10-19 10:23:54','100 гр', 'покупка 1, первой семьи','Шестёрочка', 2),
(2,'2004-10-19 10:23:54','100 гр', 'покупка 2, первой семьи','Шестёрочка', 2),
(3,'2004-10-19 10:23:54','100 гр', 'пакет для пакетовб 2-й семьи','Шестёрочка', 3);

SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/
SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/
SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/

INSERT INTO users (id, name, password) VALUES
(5, '123', '{noop}123');

INSERT INTO user_family(family_id, user_id) VALUES
(2,5);
INSERT INTO user_family(family_id, user_id) VALUES
 (3,5);

insert into user_role (user_id, role) VALUES
(5, 'USER');