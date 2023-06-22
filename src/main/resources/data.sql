
INSERT INTO rental_user (email, name, password,role, status)
VALUES ('salah@miu.edu', 'salah',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','OWNER', 'ACTIVE');

INSERT INTO rental_user (email, name, password,role)
VALUES ('salah@miu.edu1', 'salah', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','ADMIN'); --123

INSERT INTO rental_user (email, name, password,role , status)
VALUES ('waleed@miu.edu', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER' , 'ACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu1', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu2', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'INACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu3', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu4', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu5', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'INACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu6', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('waleed@miu.edu7', 'waleed',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'INACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('wissam@miu.edu8', 'wissam',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');


INSERT INTO rental_user (email, name, password,role, status)
VALUES ('wissam@miu.edu9', 'wissam',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');

INSERT INTO rental_user (email, name, password,role, status)
VALUES ('wissam@miu.edu10', 'wissam',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');


INSERT INTO rental_user (email, name, password,role, status)
VALUES ('wissam@miu.edu11', 'wissam',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','CUSTOMER', 'ACTIVE');


 INSERT INTO rental_user (email, name, password,role, status)
 VALUES ('wissam@miu.edu', 'wissam',  '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2','OWNER', 'ACTIVE');--123

INSERT INTO PROPERTY(location, user_id, price, area, rooms, click_count, status, property_type, contract_type)
VALUES('Fairfield', 1, 250000, 2000, 7, 33, 'ACTIVE', 'APARTMENT', 'RENT');

INSERT INTO PROPERTY(location, user_id, price, area, rooms, click_count, status, property_type, contract_type)
VALUES('Burlington', 1, 120000, 900, 3, 33, 'ACTIVE', 'APARTMENT', 'RENT');

INSERT INTO PROPERTY(location, user_id, price, area, rooms, click_count, status, property_type, contract_type)
VALUES('Iowa City', 1, 80000, 720, 1, 33, 'CONTINGENT', 'APARTMENT', 'RENT');

INSERT INTO PROPERTY(location, user_id, price, area, rooms, click_count, status, property_type, contract_type)
VALUES('Fairfield', 3, 90000, 1200, 1, 33, 'ACTIVE', 'APARTMENT', 'RENT');

INSERT INTO PROPERTY(location, user_id, price, area, rooms, click_count, status, property_type, contract_type)
VALUES('Ottumwa', 3, 70000, 98, 4, 33, 'PENDING', 'TOWN_HOMES', 'RENT');

INSERT INTO PICTURE(path, property_id)
VALUES('https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg', 1);

INSERT INTO PICTURE(path, property_id)
VALUES('https://media.istockphoto.com/id/1026205392/photo/beautiful-luxury-home-exterior-at-twilight.jpg?s=612x612&w=0&k=20&c=HOCqYY0noIVxnp5uQf1MJJEVpsH_d4WtVQ6-OwVoeDo=', 2);


INSERT INTO PICTURE(path, property_id)
VALUES('https://images.unsplash.com/photo-1625602812206-5ec545ca1231?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YW1lcmljYW4lMjBob3VzZXN8ZW58MHx8MHx8&w=1000&q=80', 3);


INSERT INTO PICTURE(path, property_id)
VALUES('https://t3.ftcdn.net/jpg/01/18/46/52/360_F_118465200_0q7Of6UnbA8kDlYEe3a4PuIyue27fbuV.jpg', 4);


INSERT INTO PICTURE(path, property_id)
VALUES('https://images.unsplash.com/photo-1613490493576-7fde63acd811?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bW9kZXJuJTIwaG91c2V8ZW58MHx8MHx8&w=1000&q=80', 5);


INSERT INTO public.offer
(last_modified_date, message, offer_date, price, status, property_id, user_id)
VALUES(current_timestamp, 'I will pay 230000', current_timestamp, 100, 'ACCEPTED', 1, 1);


INSERT INTO public.offer
(last_modified_date, message, offer_date, price, status, property_id, user_id)
VALUES(current_timestamp, 'could you please consider 100000', current_timestamp, 100, 'ACCEPTED', 2, 1);


INSERT INTO public.offer
(last_modified_date, message, offer_date, price, status, property_id, user_id)
VALUES(current_timestamp, 'I will pay what you ask', current_timestamp, 100, 'ACCEPTED', 3, 1);


INSERT INTO public.offer
(last_modified_date, message, offer_date, price, status, property_id, user_id)
VALUES(current_timestamp, '85000 is that work with you.', current_timestamp, 100, 'ACCEPTED', 4, 3);


INSERT INTO public.offer
(last_modified_date, message, offer_date, price, status, property_id, user_id)
VALUES(current_timestamp, 'I can pay an additional 5k', current_timestamp, 100, 'ACCEPTED', 5, 3);




INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 1', 2);


INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 2', 2);


INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 3', 2);


INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 4', 3);

INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 2', 3);


INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 3', 3);


INSERT INTO public.favorite_list
("name", user_id)
VALUES('Fav List 4', 3);