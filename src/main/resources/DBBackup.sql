/*All User's gets stored in APP_USER table*/
drop database  doctorAdministrationPanel;
create database doctorAdministrationPanel;
use doctorAdministrationPanel;

create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   phone VARCHAR (30) NOT NULL,
    joining_date DATE NOT NULL,
   description VARCHAR(1024),
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);

/* USER_PROFILE table contains all possible roles */
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

INSERT INTO USER_PROFILE(type)
VALUES ('USER');

INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE(type)
VALUES ('DBA');

INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, phone,joining_date,description)
VALUES ('testAdmin','$2a$10$5Hw52OpxBwVieaAM3WSU0umdpphZNe2osZfap1im3fXPIkjYl0SE2', 'testAdmin','testAdmin','testAdmin@gmail.com','442071234337','2015-12-12','test admin message');

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='testAdmin' and profile.type='ADMIN';

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

create table USER_DOCUMENT(
   id BIGINT NOT NULL AUTO_INCREMENT,
   user_id BIGINT NOT NULL,
   name  VARCHAR(100) NOT NULL,
   description TEXT ,
   type VARCHAR(100) NOT NULL,
   content longblob NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT document_user FOREIGN KEY (user_id) REFERENCES APP_USER (id) ON UPDATE CASCADE ON DELETE CASCADE
);

