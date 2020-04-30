ALTER TABLE PRODUCTS
ADD CONSTRAINT PRICE_GRATHER_THAN_0
CHECK  (price > 0);

ALTER TABLE PRODUCTS
ADD CONSTRAINT PRODUCT_CODE_EQUAL_6
CHECK  (length(code) > 0);

insert into USERS (name,email,password) VALUES ('János', 'kunecz1@gmail.com','123456');
insert into USERS (name,email,password) VALUES ('Máté', 'kunecz2@gmail.com','654321');

insert into PRODUCTS (code,name,description,price,user_id) values ('123456','Product1', 'Description1',0.1,(select id FROM USERS where name = 'János') );
insert into PRODUCTS (code,name,description,price,user_id) values ('654321','Product2','Description2' ,1,(select id FROM USERS where name = 'János'));
insert into PRODUCTS (code,name,description,price,user_id) values ('123789','Product3', 'Description3' ,2,(select id FROM USERS where name = 'Máté'));
insert into PRODUCTS (code,name,description,price,user_id) values ('987321','Product4', 'Description4',23,(select id FROM USERS where name = 'Máté'));
insert into PRODUCTS (code,name,description,price,user_id) values ('147852','Product5', 'Description5',23.5,(select id FROM USERS where name = 'János'));
insert into PRODUCTS (code,name,description,price,user_id) values ('123456','Product6', 'Description6',5,(select id FROM USERS where name = 'János') );
insert into PRODUCTS (code,name,description,price,user_id) values ('654321','Product7','Description7' ,52,(select id FROM USERS where name = 'János'));
insert into PRODUCTS (code,name,description,price,user_id) values ('123789','Product8', 'Description8' ,42,(select id FROM USERS where name = 'Máté'));
insert into PRODUCTS (code,name,description,price,user_id) values ('987321','Product9', 'Description9',23,(select id FROM USERS where name = 'Máté'));
insert into PRODUCTS (code,name,description,price,user_id) values ('147852','Product10', 'Description10',125,(select id FROM USERS where name = 'János'));

insert into RATE (value,productid) values (5, (select id FROM PRODUCTS where name = 'Product1') );
insert into RATE (value,productid) values (2, (select id FROM PRODUCTS where name = 'Product2') );
insert into RATE (value,productid) values (4, (select id FROM PRODUCTS where name = 'Product2') );
insert into RATE (value,productid) values (6, (select id FROM PRODUCTS where name = 'Product1') );
insert into RATE (value,productid) values (8, (select id FROM PRODUCTS where name = 'Product2') );
insert into RATE (value,productid) values (1, (select id FROM PRODUCTS where name = 'Product1') );
insert into RATE (value,productid) values (2, (select id FROM PRODUCTS where name = 'Product3') );
insert into RATE (value,productid) values (4, (select id FROM PRODUCTS where name = 'Product3') );
insert into RATE (value,productid) values (7, (select id FROM PRODUCTS where name = 'Product4') );
insert into RATE (value,productid) values (6, (select id FROM PRODUCTS where name = 'Product4') );
insert into RATE (value,productid) values (3, (select id FROM PRODUCTS where name = 'Product4') );
insert into RATE (value,productid) values (5, (select id FROM PRODUCTS where name = 'Product3') );
insert into RATE (value,productid) values (4, (select id FROM PRODUCTS where name = 'Product2') );
insert into RATE (value,productid) values (8, (select id FROM PRODUCTS where name = 'Product2') );
insert into RATE (value,productid) values (5, (select id FROM PRODUCTS where name = 'Product5') );
insert into RATE (value,productid) values (1, (select id FROM PRODUCTS where name = 'Product5') );
insert into RATE (value,productid) values (9, (select id FROM PRODUCTS where name = 'Product6') );
insert into RATE (value,productid) values (5, (select id FROM PRODUCTS where name = 'Product6') );
insert into RATE (value,productid) values (6, (select id FROM PRODUCTS where name = 'Product7') );
insert into RATE (value,productid) values (4, (select id FROM PRODUCTS where name = 'Product7') );
insert into RATE (value,productid) values (1, (select id FROM PRODUCTS where name = 'Product5') );