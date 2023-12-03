# aeroparker-registration
Aeroparker Coding Challenge - Registration Form

Built with Java 17 and uses JDBC to interact with MySQL database.

Features a registration page at /registration/register (if hosted locally, localhost:8080/registration/register).

Requires a database connection that can be configured in application.properties. The below table must be created in the database:

```
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registered` datetime NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `title` varchar(5) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `postcode` varchar(10) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
