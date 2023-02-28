insert into dbi.customer (customer_number, description, state)
values ('1', 'test', 'Austria');

insert into dbi.condition (id, condition_name, description)
values (1,'Throat Inflamation', 'The Patient''s throat is very dry and has a red color. Swallowing may cause pain.');
insert into dbi.condition (id, condition_name, description)
values (2,'Chickenpox', 'A common illness mostly experienced by children.');
insert into dbi.condition (id, condition_name, description)
values (3, 'Common Cold', 'The patient has trouble breathing without having to cought') ;
insert into dbi.condition (id, condition_name, description)
values (4, 'Flu', 'The patient has a higher body temperature, shortness of breath and a runny nose') ;
insert into dbi.condition (id, condition_name, description)
values (5, 'Kidney Infection', 'Treat Immediately') ;

insert into dbi.symptom (id, condition_id, symptom_name)
values (1,1,'Dry throat');

insert into dbi.symptom (id, condition_id, symptom_name)
values (2,1,'Short of breath');

insert into dbi.symptom (id, condition_id, symptom_name)
values (3,2,'Fever');

insert into dbi.symptom (id, condition_id, symptom_name)
values (4,2,'Headache');

insert into dbi.symptom (id, condition_id, symptom_name)
values (5,3,'Chills');

insert into dbi.symptom (id, condition_id, symptom_name)
values (6,3,'Fever');

insert into dbi.symptom (id, condition_id, symptom_name)
values (7,1,'Red color');

insert into dbi.symptom (id, condition_id, symptom_name)
values (8,1,'Pain when swallowing');

insert into dbi.symptom (id, condition_id, symptom_name)
values (6,3,'Fever');




insert into dbi.station (id, name, total_number_of_beds)
values (1,'Intensive Care', 20);

insert into dbi.station (id, name, total_number_of_beds)
values (2,'Nursery', 15);

insert into dbi.station (id, name, total_number_of_beds)
values (3,'Immediate Care', 30);

insert into dbi.station (id, name, total_number_of_beds)
values (4,'Emergency Care', 50);

insert into dbi.station (id, name, total_number_of_beds)
values (5,'Radiology', 12);

insert into dbi.patient (id, first_name, height, currently_in_hospital, diagnosed, last_name, ssn, station_id,
                     weight)
values (1 , 'John', 170, false,false, 'Smith','1111111111', 2, 70);

insert into dbi.patient (id, first_name, height, currently_in_hospital, diagnosed, last_name, ssn, station_id,
                     weight)
values (2 , 'Tamara', 170, false,false, 'Melcher','234111111', 1, 65);

insert into dbi.patient (id, first_name, height, currently_in_hospital, diagnosed, last_name, ssn, station_id,
                         weight)
values (3 , 'Anna', 160, true,false, 'Wiesinger','234115111', 4, 75);