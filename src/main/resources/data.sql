INSERT INTO shopping (id, data, how_many, name, store) VALUES
(1,'2004-10-19 10:23:54','100 гр', 'пакет для пакетов','Шестёрочка');
SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/
