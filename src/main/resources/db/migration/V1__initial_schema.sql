CREATE TABLE IF NOT EXISTS town (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS user_info (
                                         id SERIAL PRIMARY KEY,
                                         username VARCHAR(255) NOT NULL UNIQUE,
    town_id INTEGER,
    CONSTRAINT fk_userinfo_town FOREIGN KEY (town_id) REFERENCES town(id)
    );

CREATE TABLE IF NOT EXISTS message (
                                       id SERIAL PRIMARY KEY,
                                       title VARCHAR(255),
    text VARCHAR(255),
    user_info_id INTEGER,
    town_id INTEGER,
    CONSTRAINT fk_msg_userinfo FOREIGN KEY (user_info_id) REFERENCES user_info(id),
    CONSTRAINT fk_msg_town FOREIGN KEY (town_id) REFERENCES town(id)
    );