drop table if exists travel.Person cascade;
create table travel.Person (
    person_name varchar(100),
    person_id serial constraint person_pk primary key,
    person_function varchar(50),
    person_password varchar(100),
    person_mail varchar(256) unique,
        constraint proper_email check (person_mail ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    person_phone varchar(10) unique,
        constraint person_phone check (length(person_phone) = 10)
);


drop table if exists travel.services_aux cascade;
create table travel.services_aux (
    services_aux_id integer primary key,
    services_aux_reservation integer,
    foreign key (services_aux_reservation) references travel.Reservation(reservation_id),
    services_aux_project_duration integer,
    services_aux_hotel varchar(100),
    services_aux_transport varchar(256),
    services_aux_menu varchar(245)
);

drop table if exists travel.reservation_aux cascade;
create table travel.reservation_aux (
    reservation_aux_id integer primary key unique,
      reservation_aux_project integer,
      foreign key (reservation_aux_project) references travel.projects(project_id),
      reservation_aux_customer integer,
      foreign key (reservation_aux_customer) references travel.client(client_id)
);

/*drop table if exists travel.login cascade;
create table travel.login (
    login_id integer primary key,
    foreign key (login_id) references travel.Person(person_id),
    login_mail varchar(256) unique not null,
        constraint proper_email check (login_mail ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    login_password varchar(256) not null,
         constraint login_password check (length(login_password)>=8)
);*/

drop table if exists travel.Agents cascade;
create table travel.Agents (
    agent_name varchar(100),
    agent_id integer primary key unique,
    foreign key(agent_id) references travel.person(person_id),
    agent_function varchar(100) default 'Agent',
    agent_password varchar(100) not null,
    constraint login_password check (length(agent_password)>=8),
    agent_mail varchar(50) unique
    constraint proper_email check (agent_mail ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    agent_phone varchar(10) constraint agent_phone check (length(agent_phone)=10)
);

drop table if exists travel.country cascade;
create table travel.country (
   country_id integer primary key unique ,
   country_name varchar(100)
);

drop table if exists travel.projects cascade;
create table travel.projects
(
    project_id serial constraint project_pk primary key unique ,
    project_name varchar(256) not null
    constraint project_name unique,
    project_country integer,
    foreign key (project_country) references travel.country(country_id),
    project_hotel varchar(100),
    project_distance integer,
    project_start date,
    project_stop date,
    project_agent integer,
    foreign key (project_agent) references travel.agents(agent_id)
);

drop table if exists travel.feedback cascade;
create table travel.feedback(
    feedback_id integer primary key unique,
    feedback_project integer,
    foreign key (feedback_project) references travel.projects(project_id),
    feedback_text varchar(2500),
    feedback_name varchar(256),
    /*fac join aici cu nume din travel.client pt ambele*/
    feedback_mail varchar(256)
);

drop table if exists travel.reservation cascade;
create table travel.reservation (
      reservation_id integer primary key unique,
      foreign key (reservation_id) references travel.reservation_aux(reservation_aux_id),
      reservation_project integer,
      foreign key (reservation_project) references travel.projects(project_id),
      reservation_customer integer,
      foreign key (reservation_customer) references travel.client(client_id)
);


drop table if exists travel.Services cascade;
create table travel.Services (
    services_id integer primary key unique,
    foreign key (services_id) references travel.services_aux(services_aux_id),
    services_reservation integer,
    foreign key (services_reservation) references travel.Reservation_aux(reservation_aux_id),
    services_project_duration integer,
    services_hotel varchar(100),
    services_transport varchar(256),
    services_menu varchar(245)
);

drop table if exists travel.price cascade;
create table travel.price (
    price_id integer primary key,
    foreign key (price_id) references travel.services(services_id),
    price_value float
);

drop table if exists travel.Client cascade;
create table travel.Client (
    client_name varchar(50),
    client_id integer primary key,
    foreign key (client_id) references travel.person(person_id),
    client_function varchar(100) default 'Client',
    client_password varchar(50),
    client_mail varchar(50)
    constraint proper_email check (client_mail ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    client_phone varchar(10) constraint client_phone check (length(client_phone)=10)
);

drop table if exists travel.Client_reservation cascade;
create table travel.Client_reservation (
    client_reservation_id integer primary key,
    foreign key (client_reservation_id) references travel.Reservation(Reservation_id),
    client_reservation_name integer,
    foreign key (client_reservation_name) references travel.Client(client_id)
);

drop table if exists travel.client_feedback cascade;
create table travel.client_feedback (
    client_feedback_id integer primary key,
    foreign key (client_feedback_id) references travel.Feedback(feedback_id),
    client_feedback_name integer,
    foreign key (client_feedback_name) references travel.Client(client_id)
);

drop table if exists travel.project_services cascade;
create table travel.project_services (
    project_services_id integer primary key,
    foreign key (project_services_id) references travel.services_aux(services_aux_id),
    project_services_project integer,
    foreign key (project_services_project) references travel.projects(project_id)
);

insert into travel.person values ('Bogdan', 4, 'Agent', '000000000', 'bogdan@yahoo.com', '0746632112');
insert into travel.person values ('Maria', 1, 'Agent', '00000000', 'maria@yahoo.com', '0752341123');
insert into travel.person values ('Andreea', 3, 'Client', '000000000', 'andreea@yahoo.com', '0748895562');
insert into travel.person values ('Ana', 2, 'Client', '000000000', 'ana@yahoo.com', '0798842351');
insert into travel.person values ('Ane', 6, 'Agent', '00000000', 'ane@yahoo.com', '0734425531');
insert into travel.person values ('Miruna', 5, 'Client', '000000000', 'miruna@yahoo.com', '0773345215');

/*insert into travel.login values (1, 'maria@yahoo.com', '00000000');
insert into travel.login values (2, 'ana@yahoo.com', '000000000');
insert into travel.login values (3, 'andreea@yahoo.com', '1a2a3a4a5');
insert into travel.login values (4, 'bogdan@yahoo.com', '000000000');
insert into travel.login values (5, 'miruna@yahoo.com', '123456789');
insert into travel.login values (6, 'ane@yahoo.com', '00000000');*/

insert into travel.Agents values ('Bogdan', 4, 'Agent', '000000000', 'bogdan@yahoo.com', '0746632112');
insert into travel.Agents values ('Ane', 6, 'Agent', '00000000', 'ane@yahoo.com', '0734425531');
insert into travel.Agents values ('Maria', 1, 'Agent', '00000000', 'maria@yahoo.com', '0752341123');

insert into travel.Client values ('Andreea', 3, 'Client', '000000000', 'andreea@yahoo.com', '0748895562');
insert into travel.Client values ('Ana', 2, 'Client', '000000000', 'ana@yahoo.com', '0798842351');
insert into travel.Client values ('Miruna', 5, 'Client', '000000000', 'miruna@yahoo.com', '0773345215');
insert into travel.country values (1, 'Romania');
insert into travel.country values (2, 'Japan');
insert into travel.country values (3, 'China');
insert into travel.country values (4, 'Germany');
insert into travel.country values (5, 'France');

insert into travel.projects values (1, 'Tokyo', 2, 'Majestic', 11000, '21/12/2021', '28/12/2021', 4);
insert into travel.projects values (2, 'Beijing', 3, 'Hortensia', 10000, '23/04/2022', '29/04/2022', 1);
insert into travel.projects values (3, 'Berlin', 4, 'Mozart', 1130, '06/07/2022', '16/07/2022', 6);

insert into travel.feedback values (1, 2,'I enjoyed it very much and I want to do it again!', 'Miruna', 'miruna@yahoo.com');
insert into travel.feedback values (2, 3, 'I enjoyed it!', 'Andreea', 'andreea@yahoo.com');
insert into travel.feedback values (3, 1, 'I want to do it again! It was the best experience of my life!', 'Ana', 'ana@yahoo.com');
insert into travel.feedback values (4, 2, 'I felt like in heaven in this beautiful country! It is an experience that you will remember all of your life!', 'Ana', 'ana@yahoo.com');
insert into travel.feedback values (5, 1, 'I really loved it, more than I expected!', 'Andreea', 'andreea@yahoo.com');

insert into travel.reservation_aux values (1, 2, 2);
insert into travel.reservation_aux values (2, 1, 3);
insert into travel.reservation_aux values (3, 3, 5);
insert into travel.reservation_aux values (4, 2, 2);
insert into travel.reservation_aux values (5, 1, 5);
insert into travel.reservation_aux values (6, 1, 2);

insert into travel.reservation values (1, 2, 2);
insert into travel.reservation values (2, 1, 3);
insert into travel.reservation values (3, 3, 5);
insert into travel.reservation values (4, 2, 2);
insert into travel.reservation values (5, 1, 5);
insert into travel.reservation values (6, 1, 2);

insert into travel.client_reservation values (1,2);
insert into travel.client_reservation values (2,3);
insert into travel.client_reservation values (3,2);
insert into travel.client_reservation values (4,2);
insert into travel.client_reservation values (5,5);

insert into travel.services_aux values(1, 2, 6, 'Hortensia', 'plane', 'Ultra All Inclusive');
insert into travel.services_aux  values(2, 3, 10, 'Hortensia1',  'train', 'All Inclusive');
insert into travel.services_aux  values(3, 2, 6, 'Hortensia', 'plane', 'Only Breakfast');
insert into travel.services_aux  values(4, 1, 7, 'Hortensia2',  'plane', 'All Inclusive');
insert into travel.services_aux  values(5, 3, 10, 'Hortensia', 'plane', 'All Inclusive');

insert into travel.Services values(1, 2, 6, 'Hortensia', 'plane', 'Ultra All Inclusive');
insert into travel.Services values(2, 3, 10, 'Hortensia1',  'train', 'All Inclusive');
insert into travel.Services values(3, 2, 6, 'Hortensia', 'plane', 'Only Breakfast');
insert into travel.Services values(4, 1, 7, 'Hortensia2',  'plane', 'All Inclusive');
insert into travel.Services values(5, 3, 10, 'Hortensia', 'plane', 'All Inclusive');

delete from travel.project_services row where project_services_id = 6;
delete from travel.project_services row where project_services_id = 7;

delete from travel.reservation row where reservation_id = 6;
delete from travel.reservation row where reservation_id = 7;
delete from travel.reservation row where reservation_id = 8;
delete from travel.reservation row where reservation_id = 9;
delete from travel.reservation row where reservation_id = 10;
delete from travel.reservation row where reservation_id = 11;
delete from travel.reservation row where reservation_id = 12;
delete from travel.reservation row where reservation_id = 13;
delete from travel.services row where services_id = 6;
delete from travel.services row where services_id = 7;
delete from travel.services row where services_id = 8;
delete from travel.services row where services_id = 9;
delete from travel.services row where services_id = 10;




