CREATE DATABASE IF NOT EXISTS AAESocial;

USE AAESocial;

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstName VARCHAR(100) NOT NULL,
  lastName VARCHAR(100) NOT NULL,
  birthDate DATETIME NOT NULL,
  corporative BOOLEAN DEFAULT FALSE,
  photoUrl VARCHAR(255) NULL,
  bgColor VARCHAR(6) NULL,
  notify BOOLEAN NULL
) CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS message (
  id SERIAL PRIMARY KEY,
  sender BIGINT UNSIGNED NOT NULL,
  receiver BIGINT UNSIGNED NOT NULL,
  content VARCHAR(140) NOT NULL,
  sendDate DATETIME NOT NULL,
  status INT NULL,
  FOREIGN KEY fk_sender(sender) REFERENCES user(id),
  FOREIGN KEY fk_receiver(receiver) REFERENCES user(id)
) CHARACTER SET = utf8;


