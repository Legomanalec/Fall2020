-- new file --
drop database if exists alecrm4;
create database alecrm4;
use alecrm4;

create table Patient(address varchar(255), pname varchar(255), dob varchar(255), ssn int primary key);
create table Prescription(quantity int, order_date varchar(255) primary key);
create table Drug(formula varchar(255), trade_name varchar(255));
create table Doctor(specialty varchar(255));
create table Personnel(ID int primary key, personnel_name varchar(255), phone_num int, salary int);
create table Administrator(role varchar(255));
create table Nurse(edu varchar(255));
create table Pham_Co(address varchar(255), pham_name varchar(255) primary key, license_num int);