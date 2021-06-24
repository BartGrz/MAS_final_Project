-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-06-20 23:15:45.457

-- tables

-- Table: office_hours
CREATE TABLE office_hours
(
    id_office_hours integer     NOT NULL
        CONSTRAINT office_hours_pk PRIMARY KEY,
    hours           varchar(50) NOT NULL
);



--table client
CREATE TABLE Client
(
    id_client    integer     NOT NULL
        CONSTRAINT Client_pk PRIMARY KEY,
    note         varchar(100),
    name         varchar(50) NOT NULL,
    last_name    varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    email_adress varchar(50) NOT NULL,
    PESEL        varchar(50) NOT NULL
);

-- Table: Doctor
create table Doctor
(
    id_doctor   integer NOT NULL
        CONSTRAINT Doctor_pk PRIMARY KEY,
    bonus       double,
    name         varchar(50) NOT NULL,
    last_name    varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    email_adress varchar(50) NOT NULL,
    PESEL        varchar(50) NOT NULL

);
-- Table: Specialization
CREATE TABLE Specialization
(
    id_specialization integer NOT NULL
        CONSTRAINT Specialization_pk PRIMARY KEY,
    name              integer NOT NULL
);

-- Table: Doctor_Spec
CREATE TABLE Doctor_Spec
(
    id_doctor         integer NOT NULL,
    id_specialization integer NOT NULL,
    CONSTRAINT Doctor_Spec_Doctor FOREIGN KEY (id_doctor)
        REFERENCES Doctor (id_doctor),
    CONSTRAINT Doctor_Spec_Specialization FOREIGN KEY (id_specialization)
        REFERENCES Specialization (id_specialization)
);



-- Table: Office
CREATE TABLE Office
(
    id_office integer NOT NULL
        CONSTRAINT Office_pk PRIMARY KEY,
    number    integer NOT NULL,
    floor     integer,
    id_doctor integer NOT NULL,
    CONSTRAINT Office_Doctor FOREIGN KEY (id_doctor)
        REFERENCES Doctor (id_doctor)
);

-- Table: medical_card
CREATE TABLE Medical_Card
(
    id_medical_card integer     NOT NULL
        CONSTRAINT medical_card_pk PRIMARY KEY,
    age             integer,
    species         varchar(50) NOT NULL
);

-- Table: Patient
CREATE TABLE Patient
(
    id_patient      integer     NOT NULL
        CONSTRAINT Patient_pk PRIMARY KEY,
    name            varchar(50) NOT NULL,
    id_client       integer     NOT NULL,
    id_medical_card integer     NOT NULL,
    CONSTRAINT Patient_Client FOREIGN KEY (id_client)
        REFERENCES Client (id_client),
    CONSTRAINT Patient_Medical_Card FOREIGN KEY (id_medical_card)
        REFERENCES medical_card (id_medical_card)
);



-- Table: RTG
CREATE TABLE RTG
(
    id_RTG            integer     NOT NULL
        CONSTRAINT RTG_pk PRIMARY KEY,
    available_plates  integer     NOT NULL,
    protective_aprons integer     NOT NULL,
    serial_number     varchar(50) NOT NULL,
    date_of_purchase  date        NOT NULL,
    last_service_date date        NOT NULL,
    reservation       boolean     NOT NULL,
    id_office         integer     NOT NULL,
    CONSTRAINT RTG_Office FOREIGN KEY (id_office)
        REFERENCES Office (id_office)
);

-- Table: Reception
create table Reception
(
    id_reception integer not null constraint reception_pk primary key,
    name         varchar(50) NOT NULL,
    last_name    varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    email_adress varchar(50) NOT NULL,
    PESEL        varchar(50) NOT NULL

);

-- Table: technician
create table Technician
(
    id_technitian integer NOT NULL CONSTRAINT technician_pk PRIMARY KEY,
    responsibilities varchar(50) NOT NULL,
    is_an_apprentice boolean NOT NULL,
    name         varchar(50) NOT NULL,
    last_name    varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    email_adress varchar(50) NOT NULL,
    PESEL        varchar(50) NOT NULL

);



-- Table: Tomograph
CREATE TABLE Tomograph
(
    id_tomograph          integer     NOT NULL
        CONSTRAINT Tomograph_pk PRIMARY KEY,
    contrast              double      NOT NULL,
    serial_number         varchar(50) NOT NULL,
    date_of_purchase      date        NOT NULL,
    previous_service_date date        NOT NULL,
    reservation           boolean     NOT NULL,
    id_office             integer     NOT NULL,
    CONSTRAINT Tomograph_Office FOREIGN KEY (id_office)
        REFERENCES Office (id_office)
);

-- Table: Visit
CREATE TABLE Visit
(
    id_visit   integer     NOT NULL
        CONSTRAINT Visit_pk PRIMARY KEY,
    visit_type varchar(50) NOT NULL,
    id_client  integer     NOT NULL,
    id_doctor  integer     NOT NULL,
    CONSTRAINT Visit_Client FOREIGN KEY (id_client)
        REFERENCES Client (id_client),
    CONSTRAINT Visit_Doctor FOREIGN KEY (id_doctor)
        REFERENCES Doctor (id_doctor)
);

-- Table: Tech_Visit
CREATE TABLE Tech_Visit
(
    id_technitian integer NOT NULL,
    id_visit      integer NOT NULL,
    CONSTRAINT Tech_Visit_technician FOREIGN KEY (id_technitian)
        REFERENCES technician (id_technitian),
    CONSTRAINT Tech_Visit_Visit FOREIGN KEY (id_visit)
        REFERENCES Visit (id_visit)
);








-- Table: vaccines
CREATE TABLE vaccines
(
    id_vaccine          integer     NOT NULL
        CONSTRAINT vaccines_pk PRIMARY KEY,
    name                varchar(50) NOT NULL,
    date_of_vaccination date        NOT NULL,
    date_of_second_dose date
);


-- Table: vaccination_received
CREATE TABLE vaccination_received
(
    id_medical_card integer NOT NULL,
    id_vaccine      integer NOT NULL,
    CONSTRAINT vaccination_received_Medical_Card FOREIGN KEY (id_medical_card)
        REFERENCES Medical_Card (id_medical_card),
    CONSTRAINT vaccination_received_vaccines FOREIGN KEY (id_vaccine)
        REFERENCES vaccines (id_vaccine)
);


-- End of file.

