DROP IF EXISTS musica;
CREATE DATABASE musica;
USE musica;
create table songs(id INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(id), name VARCHAR(512));