alter table VISIT add column id_patient int;
alter table VISIT add foreign key (id_patient) references PATIENT(id_patient);
alter table MEDICAL_CARD add column id_patient int;
alter table MEDICAL_CARD add foreign key (id_patient) references PATIENT(id_patient);