sudo mysql --password

create database restaurant_db;

create user 'restaurant_user'@'%' identified by '12345Aa?';

grant all on restaurant_db.* to 'restaurant_user'@'%';

sudo ./mvnw spring-boot:run

http://localhost:8081/demo/add?firstname=boris&lastName=borisov&email=2&phone=2

USE restaurant_db;
SHOW TABLES;