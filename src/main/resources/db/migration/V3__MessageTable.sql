CREATE TABLE IF NOT EXISTS message
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    text TEXT,
    user_id INTEGER,
    town_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES person (id),
    FOREIGN KEY (town_id) REFERENCES town (id)
);
