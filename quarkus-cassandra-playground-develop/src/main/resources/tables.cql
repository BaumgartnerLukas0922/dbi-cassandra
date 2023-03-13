create table dbi.condition
(
    id             bigint primary key,
    condition_name text,
    description text
);

create table dbi.diagnosis
(
    id                  bigint primary key,
    medical_staff_id    bigint,
    days_in_hospital    bigint,
    diagnosed_on        text,
    condition_id        bigint,
    patient_id          bigint
);

create table dbi.customer
(
    customer_number text primary key,
    description     text,
    state           text
);

create table dbi.symptom
(
    id           bigint primary key,
    condition_id bigint,
    symptom_name varchar
);

create table dbi.station
(
    id                   bigint primary key,
    name                 text,
    total_number_of_beds int
);

create table dbi.patient
(
    id                       bigint primary key,
    first_name               text,
    height                   double,
    currently_in_hospital boolean,
    last_name                text,
    station_id               bigint,
    weight                   double,
    ssn                      varchar,
    diagnosed              boolean
);

create table dbi.medical_staff
(
    id                       bigint primary key,
    first_name               text,
    last_name                text,
    station_id               bigint,
    dob                      text,
    hire_date                text,
    salary                   float,
    staff_designation        text
);
