CREATE TABLE IF NOT EXISTS movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    time_movies VARCHAR(50),
    director VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS scenes (
    id SERIAL PRIMARY KEY,
    name_scene VARCHAR(255),
    budget INT,
    minutes INT,
    movie_id INT,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS characters (
    id SERIAL PRIMARY KEY,
    name_character VARCHAR(100) NOT NULL,
    cost INT,
    actor VARCHAR(100),
    scene_id INT,
    FOREIGN KEY (scene_id) REFERENCES scenes(id) ON DELETE CASCADE
    );
