-- noinspection SqlNoDataSourceInspectionForFile

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS mastermds_db DEFAULT CHARACTER SET utf8;

USE mastermds_db;

CREATE TABLE IF NOT EXISTS user (

    user_id bigint auto_increment primary key,
    name varchar(50) not null,
    email_address varchar(50) not null,
    date_registered varchar(50) not null,
    social_network varchar(50) not null


);


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

