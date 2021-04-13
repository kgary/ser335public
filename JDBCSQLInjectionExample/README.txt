This example taken directly from https://www.journaldev.com/2489/jdbc-statement-vs-preparedstatement-sql-injection-example

A derby database version has been included, you will need to install derby and create a database named UserDB and run the derby.sql seed script to set that up. I included this as Derby can be run in an embedded database mode so you just need the jarfiles, you don't have to run a separate server.

This example has been updated with the Factory+Strategy pattern. This is in GetUserDetailsKG, and takes system property "db".

MySQL:
java -cp classes:lib/* -Ddb=mysql com.journaldev.jdbc.statements.GetUserDetailsKG
// This uses the mysql.properties file in the root of the project tree

Derby:
java -cp classes:lib/* -Ddb=derby com.journaldev.jdbc.statements.GetUserDetailsKG
// This uses the derby.properties file in the root of the project tree

This needs to be run from a home directory of the root of the project (where this file is).
The MySQL and Derby code is generic and should work o matter what version, but I used versions 5.7.12 for MySQL and 10.13.1.1 for derby.
