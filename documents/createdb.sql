SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS QME;
CREATE DATABASE IF NOT EXISTS QME;

ALTER SCHEMA QME DEFAULT CHARACTER SET utf8 ;

USE QME;

DROP TABLE IF EXISTS USER;
CREATE TABLE IF NOT EXISTS USER (
  USER_ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_NAME VARCHAR(45) NOT NULL ,
  USER_FIRST_NAME VARCHAR(128) NOT NULL ,
  USER_LAST_NAME VARCHAR(128) NOT NULL ,
  USER_EMAIL VARCHAR(128) NOT NULL ,
  USER_PASSCODE VARCHAR(512) NOT NULL COMMENT 'Hash encoded user pass code' ,
  USER_REGISTERED_DATE TIMESTAMP NOT NULL ,
  USER_UPDATED_DATE TIMESTAMP NOT NULL ,
  UPDATE_USER BIGINT NULL DEFAULT 0,
  PRIMARY KEY (USER_ID) ,
  UNIQUE INDEX USER_ID_UNIQUE (USER_ID ASC),
  UNIQUE INDEX USER_NAME_UNIQUE (USER_NAME ASC),
  UNIQUE INDEX USER_EMAIL_UNIQUE (USER_EMAIL ASC)
) COMMENT 'User Table';

DROP TABLE IF EXISTS CATEGORY;
CREATE TABLE IF NOT EXISTS CATEGORY 
(
  CAT_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key category id',
  CAT_NAME VARCHAR(150) NOT NULL COMMENT 'Category Name',
  CAT_PARENT_ID BIGINT NOT NULL DEFAULT 0 COMMENT 'Category Parent, Categories can be nested to allow for sub categories',
  CAT_CREATE_DATE TIMESTAMP NOT NULL COMMENT 'Category Created Date ',
  CAT_CREATE_USER BIGINT NOT NULL COMMENT 'Category Created User ',
  CAT_LIKES BIGINT NULL COMMENT 'Categories likes, each user can like a category once only or chose to unlike the category if they previously like the category',
  PRIMARY KEY (CAT_ID) ,
  UNIQUE INDEX CAT_ID_UNIQUE (CAT_ID ASC),
  UNIQUE INDEX CAT_NAME_UNIQUE (CAT_NAME ASC) ,
  CONSTRAINT CATEGORY_CREATE_USER_ID_FK FOREIGN KEY (CAT_CREATE_USER) REFERENCES USER (USER_ID)
) COMMENT 'Category maintains various quiz/questions category. User Can like categories';

DROP TABLE IF EXISTS MEDIA_TYPE;
CREATE TABLE IF NOT EXISTS MEDIA_TYPE 
(
  MEDIA_TYPE_ID 	INT NOT NULL AUTO_INCREMENT,
  MEDIA_TYPE    	VARCHAR(50) NOT NULL,
  MEDIA_TYPE_DESC 	VARCHAR(255) NULL,
  PRIMARY KEY (MEDIA_TYPE_ID),
  UNIQUE INDEX MEDIA_TYPE_ID_UNIQUE (MEDIA_TYPE_ID ASC),
  UNIQUE INDEX MEDIA_TYPE_UNIQUE (MEDIA_TYPE ASC)
)COMMENT 'Media types maintains supported media types';

DROP TABLE IF EXISTS QUESTION;
CREATE TABLE IF NOT EXISTS QUESTION (
  QUESTION_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary Key question id',
  CAT_ID BIGINT NOT NULL COMMENT 'Each Question can be under once category',
  QUESTION_TEXT TEXT NOT NULL COMMENT 'The question to be asked',
  QUESTION_ANSWER LONGTEXT NULL COMMENT 'The question answer explanation after user has made choice',
  QUESTION_LIKES BIGINT NOT NULL DEFAULT 0 COMMENT 'Question likes, each user can like a question once only or chose to unlike the question if they previously like the question',
  QUESTION_CREATE_DATE TIMESTAMP NOT NULL COMMENT 'Question Created Date',
  QUESTION_CREATE_USER BIGINT NOT NULL COMMENT 'Question Created User',
  QUESTION_UPDATE_DATE TIMESTAMP NOT NULL COMMENT 'Question Updated Date',
  QUESTION_UPDATE_USER BIGINT NOT NULL COMMENT 'Question Updated User',
  PRIMARY KEY (QUESTION_ID) ,
  UNIQUE INDEX QUESTION_ID_UNIQUE (QUESTION_ID ASC) ,
  INDEX CAT_ID_INDEX (CAT_ID ASC) ,
  CONSTRAINT QUESTION_CREATE_USER_ID_FK FOREIGN KEY (QUESTION_CREATE_USER) REFERENCES USER (USER_ID),
  CONSTRAINT QUESTION_UPDATE_USER_ID_FK FOREIGN KEY (QUESTION_UPDATE_USER) REFERENCES USER (USER_ID),
  CONSTRAINT CAT_ID_QUEST_FK FOREIGN KEY (CAT_ID) REFERENCES CATEGORY (CAT_ID)
  ON DELETE RESTRICT
  ON UPDATE NO ACTION
) COMMENT 'Question maintains questions that can be part of a fixed set quiz or appear by itself for chose category in game mode';

DROP TABLE IF EXISTS ANSWER_REFERENCE_MEDIA;
CREATE  TABLE IF NOT EXISTS ANSWER_REFERENCE_MEDIA (
  ANSWER_REF_MEDIA_ID BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key explanation reference id' ,
  QUESTION_ID BIGINT(11) NOT NULL COMMENT 'Explanation reference belongs to a question, a question explanation can have zero or more references' ,
  MEDIA_TYPE_ID INT NOT NULL COMMENT 'Reference type mime type - images, videos etc' ,
  REF_MEDIA_MIME VARCHAR(150) NOT NULL COMMENT 'Mime type for media reference',
  REF_MEDIA BLOB NOT NULL COMMENT 'Reference media can be stored and save from server' ,
  PRIMARY KEY (ANSWER_REF_MEDIA_ID) ,
  UNIQUE INDEX ANSWER_REF_MEDIA_ID_UNIQUE (ANSWER_REF_MEDIA_ID ASC) ,
  INDEX ANSWER_REF_MEDIA_QUESTION_ID_FK_INDEX (QUESTION_ID ASC) ,
  CONSTRAINT ANSWER_EXPLAIN_MEDIA_QUESTION_ID_FK
    FOREIGN KEY (QUESTION_ID)
    REFERENCES QUESTION (QUESTION_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT ANSWER_REF_MEDIA_TYPE_ID_FK
    FOREIGN KEY (MEDIA_TYPE_ID)
    REFERENCES MEDIA_TYPE (MEDIA_TYPE_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION	
)COMMENT = 'Answer reference maintains answer explanations for the questions, basically will provide the users with the additonal reference material for the question';

DROP TABLE IF EXISTS QUESTION_HIT;
CREATE TABLE IF NOT EXISTS QUESTION_HIT (
  QUESTION_ID  BIGINT NOT NULL COMMENT 'Primary Key question id which is also a foreign key',
  CAT_ID BIGINT NOT NULL COMMENT 'Each Question can be under once category',
  QUESTION_HIT BIGINT NOT NULL DEFAULT 0 COMMENT 'Number of times this question was displayed',
  RIGHT_COUNT BIGINT NOT NULL DEFAULT 0 COMMENT 'Number of times this question was answered correctly',
  WRONG_COUNT BIGINT NOT NULL DEFAULT 0 COMMENT 'Number of time this question was answered incorrectly',
  PRIMARY KEY (QUESTION_ID) ,
  UNIQUE INDEX QUESTION_ID_UNIQUE (QUESTION_ID ASC),
  CONSTRAINT QUESTION_HIT_QUEST_FK FOREIGN KEY (QUESTION_ID)
  REFERENCES QUESTION (QUESTION_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'Question hits maintains the number of times this question was shown and number of times it was answered right or wrong, which will help to track difficulty level of the question';

DROP TABLE IF EXISTS ANSWER_OPTION;
CREATE TABLE IF NOT EXISTS ANSWER_OPTION (
  OPTION_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary Key option id',
  QUESTION_ID BIGINT NOT NULL COMMENT 'The question for which these option should appear',
  OPTION_TEXT TEXT NOT NULL COMMENT 'The option selection text, this could appear as a single select or multiple select item',
  ISCORRECT TINYINT NULL COMMENT 'Whether this option is correct' ,
  PRIMARY KEY (OPTION_ID) ,
  UNIQUE INDEX OPTION_ID_UNIQUE (OPTION_ID ASC) ,
  INDEX OPTION_QUESTION_FK_INDEX (QUESTION_ID ASC) ,
  CONSTRAINT OPTION_QUESTION_FK FOREIGN KEY (QUESTION_ID)
  REFERENCES QUESTION (QUESTION_ID) 
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'Answer options that appear as single or multiselect items, these are shown for question. Questions may have one or more options. Option likes may not also apply for example if its right or wrong, options like will not apply and will always be 0. ';

DROP TABLE IF EXISTS ANSWER_OPTION_MEDIA;
CREATE  TABLE IF NOT EXISTS ANSWER_OPTION_MEDIA (
  OPTION_MEDIA_ID BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key option media' ,
  OPTION_ID BIGINT(11) NOT NULL ,
  MEDIA_TYPE_ID INT NOT NULL COMMENT 'Answer option type mime type - images, videos etc' ,
  OPTION_MEDIA_MIME VARCHAR(150) NOT NULL COMMENT 'Mime type for option media reference',
  OPTION_MEDIA BLOB NOT NULL COMMENT 'Media data which save and served from server' ,
  PRIMARY KEY (OPTION_MEDIA_ID) ,
  UNIQUE INDEX OPTION_MEDIA_ID_UNIQUE (OPTION_MEDIA_ID ASC) ,
  INDEX OPTION_MEDIA_OPTION_ID_FK_INDEX (OPTION_ID ASC) ,
  CONSTRAINT OPTION_MEDIA_OPTION_ID_FK
    FOREIGN KEY (OPTION_ID)
    REFERENCES ANSWER_OPTION(OPTION_ID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
   CONSTRAINT ANSWER_OPTION_MEDIA_TYPE_ID_FK
    FOREIGN KEY (MEDIA_TYPE_ID)
    REFERENCES MEDIA_TYPE (MEDIA_TYPE_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION		
)COMMENT = 'Answer options media maintain answer reference media - images, videos';

DROP TABLE IF EXISTS QUIZ;
CREATE TABLE IF NOT EXISTS QUIZ (
  QUIZ_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary Key quiz id',
  QUIZ_NAME VARCHAR(60) NOT NULL COMMENT 'quiz name',
  QUIZ_DESC VARCHAR(756) NULL COMMENT 'Description',
  CAT_ID BIGINT NOT NULL COMMENT 'Quiz belongs to a category',
  QUIZ_HITS BIGINT NULL DEFAULT 0 COMMENT 'Number of times this quiz has been attempted',
  QUIZ_LIKES BIGINT NOT NULL DEFAULT 0 COMMENT 'Quiz likes, each user can like a quiz once only or chose to unlike the quiz if they previously like the quiz',
  MAX_ATTEMPTS INT NOT NULL DEFAULT 0 COMMENT 'Number of times this quiz can be attemted by a user',
  QUIZ_CREATE_DATE TIMESTAMP NOT NULL COMMENT 'Quiz Created Date',
  QUIZ_CREATE_USER BIGINT NOT NULL COMMENT 'Quiz Created User',
  QUIZ_UPDATE_DATE TIMESTAMP NOT NULL COMMENT 'Quiz Updated Date',
  QUIZ_UPDATE_USER BIGINT NOT NULL COMMENT 'Quiz Updated User',
  PRIMARY KEY (QUIZ_ID) ,
  UNIQUE INDEX QUIZ_ID_UNIQUE (QUIZ_ID ASC) ,
  UNIQUE INDEX QUIZ_NAME_UNIQUE (QUIZ_NAME ASC) ,
  INDEX QUIZ_CAT_FK_INDEX (CAT_ID ASC),
  CONSTRAINT QUIZ_CREATE_USER_ID_FK FOREIGN KEY (QUIZ_CREATE_USER) REFERENCES USER (USER_ID),
  CONSTRAINT QUIZ_UPDATE_USER_ID_FK FOREIGN KEY (QUIZ_UPDATE_USER) REFERENCES USER (USER_ID),
  CONSTRAINT QUIZ_CAT_FK FOREIGN KEY (CAT_ID)
  REFERENCES CATEGORY (CAT_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'Quiz is basically used to display fixed set of question and check users responses for a fixed set of questions';

DROP TABLE IF EXISTS QUIZ_QUESTION;
CREATE TABLE IF NOT EXISTS QUIZ_QUESTION (
  QUIZ_QUESTION_ID BIGINT NOT NULL AUTO_INCREMENT,
  QUIZ_ID BIGINT NOT NULL ,
  QUESTION_ID BIGINT NOT NULL ,
  PRIMARY KEY (QUIZ_QUESTION_ID) ,
  UNIQUE INDEX QUIZ_QUESTION_ID_UNIQUE (QUIZ_QUESTION_ID ASC) ,
  INDEX QUIZ_QUEST_QUESTION_FK_INDEX (QUESTION_ID ASC) ,
  INDEX QUIZ_QUEST_QUIZ_FK_INDEX (QUIZ_ID ASC) ,
  CONSTRAINT QUIZ_QUEST_QUESTION_FK FOREIGN KEY (QUESTION_ID)
  REFERENCES QUESTION (QUESTION_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT QUIZ_QUEST_QUIZ_FK FOREIGN KEY (QUIZ_ID)
  REFERENCES QUIZ (QUIZ_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'Fixed set of questions that make up the quiz';

DROP TABLE IF EXISTS USER_CATEGORY;
CREATE TABLE IF NOT EXISTS USER_CATEGORY (
  USER_CAT_ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_ID BIGINT NOT NULL ,
  CAT_ID BIGINT NOT NULL ,
  PRIMARY KEY (USER_CAT_ID) ,
  UNIQUE INDEX USER_CAT_ID_UNIQUE (USER_CAT_ID ASC) ,
  INDEX USER_CAT_USER_ID_FK_INDEX (USER_ID ASC) ,
  INDEX USER_CAT_CAT_ID_FK_INDEX (CAT_ID ASC) ,
  CONSTRAINT USER_CAT_USER_ID_FK FOREIGN KEY (USER_ID)
  REFERENCES USER (USER_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT USER_CAT_CAT_ID_FK FOREIGN KEY (CAT_ID)
  REFERENCES CATEGORY (CAT_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'User subscribed categories - if user has chosen some categories, user can attemp quiz, games from the chosen category';

DROP TABLE IF EXISTS USER_CATEGORY_LIKES;
CREATE TABLE IF NOT EXISTS USER_CATEGORY_LIKES (
  USER_ID BIGINT NOT NULL ,
  CAT_ID BIGINT NOT NULL ,
  PRIMARY KEY (USER_ID, CAT_ID) ,
  INDEX USER_CAT_LIKE_USER_ID_FK_INDEX (USER_ID ASC) ,
  INDEX USER_CAT_LIKE_QUIZ_ID_FK_INDEX (CAT_ID ASC) ,
  CONSTRAINT USER_CAT_LIKE_USER_ID_FK FOREIGN KEY (USER_ID)
  REFERENCES USER (USER_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT USER_CAT_LIKE_QUIZ_ID_FK FOREIGN KEY (CAT_ID)
  REFERENCES CATEGORY (CAT_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'User Category likes, user can like a category only once and chose to unlike previously liked category.';

DROP TABLE IF EXISTS USER_QUESTION_LIKES;
CREATE TABLE IF NOT EXISTS USER_QUESTION_LIKES (
  USER_ID BIGINT NOT NULL ,
  QUESTION_ID BIGINT NOT NULL ,
  PRIMARY KEY (USER_ID, QUESTION_ID) ,
  INDEX USER_QUESTION_LIKE_USER_ID_FK_INDEX (USER_ID ASC) ,
  INDEX USER_QUESTION_LIKE_QUIZ_ID_FK_INDEX (QUESTION_ID ASC) ,
  CONSTRAINT USER_QUESTION_LIKE_USER_ID_FK FOREIGN KEY (USER_ID)
  REFERENCES USER (USER_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT USER_QUESTION_LIKE_QUIZ_ID_FK FOREIGN KEY (QUESTION_ID)
  REFERENCES QUESTION (QUESTION_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
)COMMENT 'User Question likes, user can like a question only once and chose to unlike previously liked question.';

DROP TABLE IF EXISTS USER_QUIZ_LIKES;
CREATE TABLE IF NOT EXISTS USER_QUIZ_LIKES (
  USER_ID BIGINT NOT NULL ,
  QUIZ_ID BIGINT NOT NULL ,
  PRIMARY KEY (USER_ID, QUIZ_ID) ,
  INDEX USER_QUIZ_LIKE_USER_ID_FK_INDEX (USER_ID ASC) ,
  INDEX USER_QUIZ_LIKE_QUIZ_ID_FK_INDEX (QUIZ_ID ASC) ,
  CONSTRAINT USER_QUIZ_LIKE_USER_ID_FK FOREIGN KEY (USER_ID)
  REFERENCES USER (USER_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT USER_QUIZ_LIKE_QUIZ_ID_FK FOREIGN KEY (QUIZ_ID)
  REFERENCES QUIZ (QUIZ_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
) COMMENT 'User Quiz likes, user can like a quiz only once and chose to unlike previously liked quiz.';


DROP TABLE IF EXISTS USER_QUIZ;
CREATE TABLE IF NOT EXISTS USER_QUIZ (
  USER_QUIZ_ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_ID BIGINT NOT NULL ,
  QUIZ_ID BIGINT NOT NULL ,
  CAT_ID BIGINT NOT NULL COMMENT 'Category for Quiz',
  QUIZ_START_DATE TIMESTAMP NOT NULL ,
  QUIZ_END_DATE TIMESTAMP NULL ,
  QUIZ_USER_SCORE INT NOT NULL DEFAULT 0 ,
  QUIZ_MAX_SCORE INT NOT NULL DEFAULT 0 ,
  QUIZ_TOKEN VARCHAR(256) NULL COMMENT 'Quiz Generated token to compare results',
  QUIZ_COMPLETE TINYINT NOT NULL DEFAULT 0 COMMENT 'Flag to indicate quiz was completed and submitted by the user',
  PRIMARY KEY (USER_QUIZ_ID) ,
  UNIQUE INDEX USER_QUIZ_ID_UNIQUE (USER_QUIZ_ID ASC) ,
  INDEX USER_QUIZ_USER_ID_FK_INDEX (USER_ID ASC) ,
  INDEX USER_QUIZ_QUIZ_ID_FK_INDEX (QUIZ_ID ASC) ,
  CONSTRAINT USER_QUIZ_USER_ID_FK FOREIGN KEY (USER_ID) 
  REFERENCES USER (USER_ID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT USER_QUIZ_QUIZ_ID_FK FOREIGN KEY (`QUIZ_ID` )
  REFERENCES QUIZ (QUIZ_ID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) COMMENT 'User Quiz maintain quiz results when user attempts fixed quizs.';

DROP TABLE IF EXISTS USER_QUIZ_GAME;
CREATE  TABLE IF NOT EXISTS USER_QUIZ_GAME (
  USER_ID BIGINT(11) NOT NULL ,
  CAT_ID BIGINT(11) NOT NULL COMMENT 'Category for Quiz Game Mode' ,
  START_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  END_DATE TIMESTAMP NULL DEFAULT NULL ,
  USER_SCORE INT(11) NOT NULL DEFAULT '0' ,
  QUIZ_GAME_TOKEN BIGINT NOT NULL COMMENT 'Quiz Game Generated token to compare results' ,
  PRIMARY KEY (USER_ID, CAT_ID, QUIZ_GAME_TOKEN) ,
  INDEX USER_QUIZ_GAME_USER_ID_FK_INDEX (USER_ID ASC) ,
  INDEX USER_QUIZ_GAME_CAT_ID_FK_INDEX (CAT_ID ASC) ,
  CONSTRAINT USER_QUIZ_GAME_USER_ID_FK
    FOREIGN KEY (USER_ID)
    REFERENCES USER (USER_ID),
  CONSTRAINT USER_QUIZ_GAME_CAT_ID_FK
    FOREIGN KEY (CAT_ID)
    REFERENCES CATEGORY(CAT_ID)
)
COMMENT = 'User can play quiz in game mode where random questions will  be displayed to the user, game ends when user gets x answers wrong.';


DROP TABLE IF EXISTS ROLE;
CREATE TABLE IF NOT EXISTS ROLE (
  ROLE_ID INT NOT NULL AUTO_INCREMENT,
  ROLE_NAME VARCHAR(45) NOT NULL ,
  ROLE_DESC VARCHAR(128) NOT NULL ,
  PRIMARY KEY (ROLE_ID) ,
  UNIQUE INDEX ROLE_ID_UNIQUE (ROLE_ID ASC) ,
  UNIQUE INDEX ROLE_NAME_UNIQUE (ROLE_NAME ASC)
);

DROP TABLE IF EXISTS USER_ROLES;
CREATE TABLE IF NOT EXISTS USER_ROLES (
  USER_ROLE_ID BIGINT NOT NULL AUTO_INCREMENT,
  USER_ID BIGINT NOT NULL ,
  ROLE_ID INT NOT NULL ,
  PRIMARY KEY (USER_ROLE_ID) ,
  UNIQUE INDEX USER_ROLE_ID_UNIQUE (USER_ROLE_ID ASC) ,
  INDEX USER_ROLE_USER_ID_INDEX (USER_ID ASC) ,
  INDEX USER_ROLE_ROLE_ID_INDEX (ROLE_ID ASC) ,
  CONSTRAINT USER_ROLE_USER_ID FOREIGN KEY (USER_ID)
  REFERENCES USER (USER_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT USER_ROLE_ROLE_ID FOREIGN KEY (ROLE_ID)
  REFERENCES ROLE (ROLE_ID)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
);

-- Add Media Type Data
INSERT INTO MEDIA_TYPE(MEDIA_TYPE,MEDIA_TYPE_DESC)
VALUES ('IMAGE',"Image");
INSERT INTO MEDIA_TYPE(MEDIA_TYPE,MEDIA_TYPE_DESC)
VALUES ('VIDEO',"Video");
INSERT INTO MEDIA_TYPE(MEDIA_TYPE,MEDIA_TYPE_DESC)
VALUES ('AUDIO',"Audio");
INSERT INTO MEDIA_TYPE(MEDIA_TYPE,MEDIA_TYPE_DESC)
VALUES ('FILE',"File");

-- Add Role Data
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('ADMIN', 'ADMIN ROLE');
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('READ', 'READ ROLE');
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('CREATE', 'CREATE ROLE');
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('UPDATE', 'UPDATE ROLE');
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('DELETE', 'DELETE ROLE');

-- Add User Data
INSERT INTO USER (USER_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL,USER_PASSCODE, USER_REGISTERED_DATE, USER_UPDATED_DATE) 
VALUES ('testadmin', 'Test', 'Admin', 'test.admin@gmail.com','$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO USER (USER_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL,USER_PASSCODE, USER_REGISTERED_DATE, USER_UPDATED_DATE) 
VALUES ('jdoe', 'John', 'Doe', 'john.doe@doe.com','$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO USER (USER_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL,USER_PASSCODE, USER_REGISTERED_DATE, USER_UPDATED_DATE) 
VALUES ('kperry', 'Katy', 'Perry', 'k.perry@perry.com','$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO USER (USER_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL,USER_PASSCODE, USER_REGISTERED_DATE, USER_UPDATED_DATE) 
VALUES ('tswift', 'Taylor', 'Swift', 't.swift@swift.com','$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO USER (USER_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL,USER_PASSCODE, USER_REGISTERED_DATE, USER_UPDATED_DATE) 
VALUES ('testuser', 'Test', 'User', 'test.user@gmail.com','$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Add User Role Data
INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (1,1);
INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (2,2);
INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (3,2);
INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (4,2);
INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES (5,2);

-- Add Categories
INSERT INTO CATEGORY (CAT_NAME,CAT_PARENT_ID,CAT_CREATE_DATE,CAT_CREATE_USER,CAT_LIKES)
VALUES ('Java', '0', CURRENT_TIMESTAMP, 1, 0);
INSERT INTO CATEGORY (CAT_NAME,CAT_PARENT_ID,CAT_CREATE_DATE,CAT_CREATE_USER,CAT_LIKES)
VALUES ('Movies', '0', CURRENT_TIMESTAMP, 1, 0);

-- Add User Categories
INSERT INTO USER_CATEGORY(USER_ID, CAT_ID) 
VALUES (1, 1);
INSERT INTO USER_CATEGORY(USER_ID, CAT_ID) 
VALUES (1, 2);

-- Add User Categories Likes
INSERT INTO USER_CATEGORY_LIKES (USER_ID,CAT_ID) 
VALUES (1, 1);
INSERT INTO USER_CATEGORY_LIKES (USER_ID,CAT_ID) 
VALUES (1, 2);

-- Add Questions
INSERT INTO QUESTION (CAT_ID, QUESTION_TEXT, QUESTION_ANSWER, QUESTION_LIKES, QUESTION_CREATE_DATE, QUESTION_CREATE_USER, QUESTION_UPDATE_DATE, QUESTION_UPDATE_USER)
VALUES(1,"Some Question Text 1","Some Answer",0,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);
INSERT INTO QUESTION (CAT_ID, QUESTION_TEXT, QUESTION_ANSWER, QUESTION_LIKES, QUESTION_CREATE_DATE, QUESTION_CREATE_USER, QUESTION_UPDATE_DATE, QUESTION_UPDATE_USER)
VALUES(1,"Some Question Text 2","Some Answer",0,CURRENT_TIMESTAMP,1,CURRENT_TIMESTAMP,1);

-- Add Answer Reference Media
INSERT INTO ANSWER_REFERENCE_MEDIA (QUESTION_ID, MEDIA_TYPE_ID, REF_MEDIA_MIME,REF_MEDIA) 
VALUES ('1', '1', 'image/png', '/tmp/cube.png');

-- Add Question Hits
INSERT INTO QUESTION_HIT(QUESTION_ID,CAT_ID,QUESTION_HIT,RIGHT_COUNT,WRONG_COUNT)
VALUES(1,1,0,0,0);
INSERT INTO QUESTION_HIT(QUESTION_ID,CAT_ID,QUESTION_HIT,RIGHT_COUNT,WRONG_COUNT)
VALUES(2,1,0,0,0);

-- Add User Question Likes
INSERT INTO USER_QUESTION_LIKES(USER_ID,QUESTION_ID)
VALUES(1,1);
INSERT INTO USER_QUESTION_LIKES(USER_ID,QUESTION_ID)
VALUES(1,2);
