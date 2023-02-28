create table dbi.condition
(
    id             bigint primary key,
    condition_name text,
    description text
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