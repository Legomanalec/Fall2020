drop database if exists alecrm4;
create database alecrm4;
use alecrm4;

create table Patient(
	address varchar(255), 
    pname varchar(255), 
    dob date, 
    ssn int, 
    primary key(ssn)
);
create table Prescription(
	quantity int, 
	order_date date, 
	Patient_ssn int,
    primary key(order_date),
    foreign key(Patient_ssn) references Patient(ssn)
);
create table Drug(
	formula varchar(255), 
	trade_name varchar(255), 
	Patient_ssn int,
    foreign key(Patient_ssn) references Patient(ssn)
);
create table Personnel(
	id int, 
	pname varchar(255), 
	phone varchar(10), 
	salary int,
    primary key(ID)
);
create table Doctor(
	specialty varchar(255),
    Doctor_id int, 
	foreign key(Doctor_id) references Personnel(id)
);
create table Administrator(
	Arole varchar(255),
    Admin_id int,
    foreign key(Admin_id) references Personnel(id)
);
create table Nurse(
	edu varchar(255),
    Nurse_id int, 
    foreign key(Nurse_id) references Personnel(id)
);
create table Pham_Com(
	address varchar(255),
	pham_name varchar(255), 
	license_num int,
    primary key(pham_name)
 );


