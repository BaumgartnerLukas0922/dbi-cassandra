insert into dbi.customer (customer_number, description, state)
values ('1', 'test', 'Austria');

insert into condition (id, condition_name, description)
values (1,'Throat Inflamation', 'The Patient''s throat is very dry and has a red color. Swallowing may cause pain.');
insert into condition (id, condition_name, description)
values (2,'Chickenpox', 'A common illness mostly experienced by children.');
insert into condition (id, condition_name, description)
values (3, 'Common Cold', 'The patient has trouble breathing without having to cought') ;
insert into condition (id, condition_name, description)
values (4, 'Flu', 'The patient has a higher body temperature, shortness of breath and a runny nose') ;
insert into condition (id, condition_name, description)
values (5, 'Kidney Infection', 'Treat Immediately') ;

insert into symptom (id, condition_id, symptom_name)
values (1,1,'Dry throat');

insert into symptom (id, condition_id, symptom_name)
values (2,1,'Short of breath');

insert into symptom (id, condition_id, symptom_name)
values (3,2,'Fever');

insert into symptom (id, condition_id, symptom_name)
values (4,2,'Headache');

insert into symptom (id, condition_id, symptom_name)
values (5,3,'Chills');

insert into symptom (id, condition_id, symptom_name)
values (6,3,'Fever');

insert into station (id, name, total_number_of_beds)
values (1,'Intensive Care', 20);

insert into station (id, name, total_number_of_beds)
values (2,'Nursery', 15);