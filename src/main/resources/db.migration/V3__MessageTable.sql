CREATE TABLE message
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR(255),
    text    TEXT,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES user (id)
);
