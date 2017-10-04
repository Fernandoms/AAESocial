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
  photoUrl VARCHAR(255) NULL,
  notify BOOLEAN NULL
) CHARACTER SET = utf16;

CREATE TABLE IF NOT EXISTS message (
  id SERIAL PRIMARY KEY,
  sender BIGINT UNSIGNED NOT NULL,
  receiver BIGINT UNSIGNED NOT NULL,
  sendDate DATETIME NOT NULL,
  status INT NULL,
  FOREIGN KEY fk_sender(sender) REFERENCES user(id),
  FOREIGN KEY fk_receiver(receiver) REFERENCES user(id)
) CHARACTER SET = utf16;


