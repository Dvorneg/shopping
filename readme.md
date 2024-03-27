# Getting Started

### Reference Documentation

For further reference, please consider the following sections:
http://localhost:8081/shopping

* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)

### to do
When loading, the user selects a family and their shopping is shown.
If the family is alone, the shopping list is shown immediately. You need a list with families.

- Add authorization+
- Fix icon +
- Fix css +
- Add registration +
- Fix password +
- When creating a user, create a default family+
- user_family +
- Give access to your family to other users+
- view only you are shopping?
- http://localhost:8081/family/2 add header and footer+
- remove duplicates when adding user access!+
- getAll family controller+
- different access levels full, read, add
- when deleting a family or shopping an error




/*SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/
SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/
SELECT nextVal('"shopping_id_seq"');  /* next value sequence or SELECT setval('the_primary_key_sequence', (SELECT MAX(the_primary_key) FROM the_table)+1);*/

SELECT nextVal('"family_id_seq"');
SELECT nextVal('"users_id_seq"');*/