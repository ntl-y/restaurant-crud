sudo mysql --password

create database restaurant_db_2;

create user 'restaurant_user_2'@'%' identified by '12345Aa?';

grant all on restaurant_db_2.* to 'restaurant_user_2'@'%';

sudo ./mvnw spring-boot:run

http://localhost:8081/demo/add?firstname=boris&lastName=borisov&email=2&phone=2

USE restaurant_db_2;
SHOW TABLES;