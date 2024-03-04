DROP TABLE IF EXISTS user_entity_authorities;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user_entity;
DROP TABLE IF EXISTS secret;

CREATE TABLE authority
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user_entity
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (username)
);

CREATE TABLE secret
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    unique_link VARCHAR(255) NOT NULL,
    message     CLOB,
    active      BOOLEAN      NOT NULL,
    UNIQUE (unique_link)
);

CREATE TABLE user_entity_authorities
(
    user_entity_id BIGINT,
    authorities_id BIGINT,
    CONSTRAINT fk_user_entity FOREIGN KEY (user_entity_id) REFERENCES user_entity (id),
    CONSTRAINT fk_authority FOREIGN KEY (authorities_id) REFERENCES authority (id),
    PRIMARY KEY (user_entity_id, authorities_id)
);