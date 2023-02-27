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