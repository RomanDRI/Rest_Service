CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS requests (
    id SERIAL PRIMARY KEY NOT NULL,
    name  VARCHAR(50) NOT NULL,
    user_message  VARCHAR(1000) NOT NULL,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    state VARCHAR(50),
    created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL
);