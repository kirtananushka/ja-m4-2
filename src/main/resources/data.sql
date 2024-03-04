INSERT INTO authority (name)
VALUES ('STANDARD'),
       ('START');

INSERT INTO user_entity (username, password)
VALUES ('user@mail.com', '{noop}password'),
       ('user2@mail.com', '{noop}password');

INSERT INTO user_entity_authorities (user_entity_id, authorities_id)
VALUES (1, 1),
       (2, 2);
