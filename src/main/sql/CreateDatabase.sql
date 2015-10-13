DROP DATABASE IF EXISTS musica;
CREATE DATABASE musica;
GRANT ALL PRIVILEGES ON musica.* TO 'user'@'localhost' IDENTIFIED BY 'user';
USE musica;
create table songs(id INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(id), name VARCHAR(512));