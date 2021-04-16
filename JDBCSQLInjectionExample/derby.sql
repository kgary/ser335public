-- Timestamp: 2021-04-15 18:38:39.103
-- Source database is: UserDB
-- Connection URL is: jdbc:derby:UserDB
-- appendLogs: false

-- ----------------------------------------------
-- DDL Statements for tables
-- ----------------------------------------------

CREATE TABLE "APP"."USERS" ("ID" INTEGER NOT NULL, "NAME" VARCHAR(20) NOT NULL DEFAULT '', "EMAIL" VARCHAR(20) NOT NULL DEFAULT '', "COUNTRY" VARCHAR(20) NOT NULL DEFAULT 'USA', "PASSWORD" VARCHAR(20) NOT NULL DEFAULT '');

-- ----------------------------------------------
-- DDL Statements for keys
-- ----------------------------------------------

-- PRIMARY/UNIQUE
ALTER TABLE "APP"."USERS" ADD CONSTRAINT "SQL210415182619430" PRIMARY KEY ("ID");

