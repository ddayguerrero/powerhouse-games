CREATE TABLE IF NOT EXISTS Game(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
game_id INT,
game_name TINYTEXT NOT NULL,
game_description TEXT ,
console TEXT ,
num_players TEXT default '1',
coop BOOLEAN ,
genre TEXT ,
release_date DATE ,
developer TEXT ,
publisher TEXT ,
front_box_art TEXT ,
back_box_art TEXT ,
price DECIMAL(4,2) ,
discount DECIMAL(4,2)
);

INSERT INTO Game(game_id, game_name, game_description, console, num_players, coop, genre, release_date, developer, publisher, front_box_art, back_box_art, price, discount) VALUES();
