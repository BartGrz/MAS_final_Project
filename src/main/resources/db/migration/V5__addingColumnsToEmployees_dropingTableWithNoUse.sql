drop table OFFICE_HOURS;

alter table DOCTOR add (

    begin_hour time,
    end_hour time
    );

alter table TECHNICIAN add (

    begin_hour time,
    end_hour time

    );
alter table RECEPTION add (

    begin_hour time,
    end_hour time
    );