CREATE TABLE IF NOT EXISTS files
(
    id         BIGSERIAL PRIMARY KEY,
    message_id BIGINT,
    file_name  VARCHAR(255),
    data       OID,
    CONSTRAINT fk_message_id FOREIGN KEY (message_id) REFERENCES messages (id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id         BIGSERIAL PRIMARY KEY,
    send_date  TIMESTAMP(6)  NOT NULL,
    text       VARCHAR(1000) NOT NULL,
    from_email VARCHAR(255)  NOT NULL,
    status     VARCHAR(255)  NOT NULL CHECK (status IN ('PENDING', 'SEND', 'ERROR')),
    subject    VARCHAR(255)  NOT NULL,
    to_email   VARCHAR(255)  NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    password VARCHAR(255),
    username VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);
