
use age_guesser_db;
DROP table guesses;
DROP table users;
DROP table scores;


INSERT INTO users(id,email,username,password,dob,photo)
VALUES(1,"dave@gmail.com","dave@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1991-01-11","https://fer-uig.glitch.me/"),
  (2,"dave2@gmail.com","dave2@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1991-01-11","https://fer-uig.glitch.me/"),
  (3,"dave3@gmail.com","dave3@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1990-01-11","https://fer-uig.glitch.me/"),
  (4,"dave4@gmail.com","dave4@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1997-01-11","https://fer-uig.glitch.me/"),
  (5,"dave5@gmail.com","dave5@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1983-01-11","https://fer-uig.glitch.me/"),
  (6,"dave6@gmail.com","dave6@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1991-01-11","https://fer-uig.glitch.me/"),
  (7,"dave7@gmail.com","dave7@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1912-01-11","https://fer-uig.glitch.me/"),
  (8,"dave8@gmail.com","dave8@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","2002-01-11","https://fer-uig.glitch.me/"),
  (9,"dave9@gmail.com","dave9@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1996-01-11","https://fer-uig.glitch.me/"),
  (10,"dave20@gmail.com","dave20@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","2000-01-11","https://fer-uig.glitch.me/"),
  (11,"dave22@gmail.com","dave22@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1991-01-11","https://fer-uig.glitch.me/"),
  (12,"dave23@gmail.com","dave23@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1981-01-11","https://fer-uig.glitch.me/"),
  (13,"dave34@gmail.com","dave34@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1970-01-11","https://fer-uig.glitch.me/"),
  (14,"dave45@gmail.com","dave45@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1990-01-11","https://fer-uig.glitch.me/"),
  (15,"dave56@gmail.com","dave56@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1963-01-11","https://fer-uig.glitch.me/"),
  (16,"dave63@gmail.com","dave63@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1951-01-11","https://fer-uig.glitch.me/"),
  (17,"dave71@gmail.com","dave71@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1972-01-11","https://fer-uig.glitch.me/"),
  (18,"dave83@gmail.com","dave83@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","2001-01-11","https://fer-uig.glitch.me/"),
  (19,"dave91@gmail.com","dave91@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1955-01-11","https://fer-uig.glitch.me/"),
  (20,"dave207@gmail.com","dave207@gmail.com","$2a$10$Vu..NnZcQ4h6aK.2TEtjXuA8uOzpK8Io6t03Ap8WuUHlwE57AAcZS","1921-01-11","https://fer-uig.glitch.me/")
;

INSERT INTO guesses(id, age, user_id)
    VALUES(1,29,3),(2,29,3),(3,34,3),(4,28,3),(5,24,4),(6,27,4),(7,36,4),
      (8,29,5),(9,29,5),(10,34,6),(11,28,6),(12,24,8),(13,27,8),(14,36,9);
