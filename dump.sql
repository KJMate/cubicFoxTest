SET DB_CLOSE_DELAY -1;        
;             
CREATE USER IF NOT EXISTS "SA" SALT '2cebfa7ee5ac344c' HASH '43788ce3833fdd29bf36802163e53cc05700629afd46c9fe6934463c4f149092' ADMIN;         
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_10FC3173_A07A_48AC_902F_AE92635B8FC4" START WITH 11 BELONGS_TO_TABLE;               
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9906C8C7_F6E4_43FD_9C22_6B4A2094B601" START WITH 23 BELONGS_TO_TABLE;               
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FB1A6ED4_1AA7_4A51_87F6_318606DEBC8F" START WITH 3 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."PRODUCTS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_10FC3173_A07A_48AC_902F_AE92635B8FC4" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_10FC3173_A07A_48AC_902F_AE92635B8FC4",
    "CODE" VARCHAR(6),
    "DESCRIPTION" VARCHAR(255),
    "NAME" VARCHAR(255) NOT NULL,
    "PRICE" DOUBLE NOT NULL,
    "USER_ID" BIGINT
);       
ALTER TABLE "PUBLIC"."PRODUCTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");     
-- 10 +/- SELECT COUNT(*) FROM PUBLIC.PRODUCTS;               
INSERT INTO "PUBLIC"."PRODUCTS" VALUES
(1, '123456', 'Description1', 'Product1', 0.1, 1),
(2, '654321', 'Description2', 'Product2', 1.0, 1),
(3, '123789', 'Description3', 'Product3', 2.0, 2),
(4, '123456', '', 'dfg', 10.0, 2),
(5, '147852', 'Description5', 'Product5', 23.5, 1),
(6, '123456', 'Description6', 'Product6', 5.0, 1),
(7, '654321', 'Description7', 'Product7', 52.0, 1),
(8, '123789', 'Description8', 'Product8', 42.0, 2),
(9, '987321', 'Description9', 'Product9', 23.0, 2),
(10, '147852', 'Description10', 'Product10', 125.0, 1);       
CREATE MEMORY TABLE "PUBLIC"."RATE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_9906C8C7_F6E4_43FD_9C22_6B4A2094B601" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_9906C8C7_F6E4_43FD_9C22_6B4A2094B601",
    "PRODUCTID" BIGINT,
    "VALUE" INTEGER NOT NULL
);    
ALTER TABLE "PUBLIC"."RATE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 22 +/- SELECT COUNT(*) FROM PUBLIC.RATE;   
INSERT INTO "PUBLIC"."RATE" VALUES
(1, 1, 5),
(2, 2, 2),
(3, 2, 4),
(4, 1, 6),
(5, 2, 8),
(6, 1, 1),
(7, 3, 2),
(8, 3, 4),
(9, 4, 7),
(10, 4, 6),
(11, 4, 3),
(12, 3, 5),
(13, 2, 4),
(14, 2, 8),
(15, 5, 5),
(16, 5, 1),
(17, 6, 9),
(18, 6, 5),
(19, 7, 6),
(20, 7, 4),
(21, 5, 1),
(22, 4, 10);      
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_FB1A6ED4_1AA7_4A51_87F6_318606DEBC8F" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_FB1A6ED4_1AA7_4A51_87F6_318606DEBC8F",
    "EMAIL" VARCHAR(255),
    "NAME" VARCHAR(255),
    "PASSWORD" VARCHAR(255)
);        
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");        
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(1, 'kunecz1@gmail.com', STRINGDECODE('J\u00e1nos'), '123456'),
(2, 'kunecz2@gmail.com', STRINGDECODE('M\u00e1t\u00e9'), '654321');     
ALTER TABLE "PUBLIC"."PRODUCTS" ADD CONSTRAINT "PUBLIC"."PRICE_GRATHER_THAN_0" CHECK("PRICE" > 0.0) NOCHECK;  
ALTER TABLE "PUBLIC"."PRODUCTS" ADD CONSTRAINT "PUBLIC"."PRODUCT_CODE_EQUAL_6" CHECK(LENGTH("CODE") > 0) NOCHECK;             
ALTER TABLE "PUBLIC"."PRODUCTS" ADD CONSTRAINT "PUBLIC"."FKDB050TK37QRYV15HD932626TH" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;       
