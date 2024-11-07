CREATE TABLE IF NOT EXISTS person
(
    id       SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    phone_nr VARCHAR(255),
    email    VARCHAR(255),
    town_id  INTEGER,
    FOREIGN KEY (town_id) REFERENCES town (id)
);
