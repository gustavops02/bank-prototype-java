# Banking System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

> OBS.: This project has been created for academic purposes. But, the team is considering moving forward with the project.


Tools used:

- Java
- JDBC Connection
- MySQL


## UML Diagram
![Conta Bancária](https://github.com/gustavops02/bank-prototype-java/assets/87784023/0e7dbeb6-55af-4275-96ab-b2376c439cf3)

## Build

First of all. the project use MySQL for data persistences. In order to run this project, make sure that MySQL has been installed on your PC.

- Create the `banking` database.
- Create the following table:
  ```sql
  CREATE TABLE `contas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numeroConta` int(11) NOT NULL,
  `titular` varchar(100) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `tipoConta` enum('CC','CP') NOT NULL,
  `saldo` decimal(20,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numeroConta` (`numeroConta`)
  ) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  ```
- On `DBContext` class, follow the instructions below:
   ```java
    protected static final String url = "jdbc:mysql://localhost:3306/banking";
    protected static final String host = "root"; // MAKE SURE THAT YOUR USERNAME WILL BE EQUAL THIS CONSTANT
    protected static final String senha = ""; // MAKE SURE THAT YOUR PASSWORD WILL BE EQUAL THIS CONSTANT
   ``` 
