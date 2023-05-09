-- Создание базы данных
CREATE DATABASE restaurant_db;

-- Использование созданной базы данных
USE restaurant_db;

-- Создание таблицы "Рестораны"
CREATE TABLE Restaurants (
  ID INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Address VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  ManagerID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ManagerID) REFERENCES Managers(ID)
);

-- Создание таблицы "Менеджеры"
CREATE TABLE Managers (
  ID INT NOT NULL AUTO_INCREMENT,
  FirstName VARCHAR(255) NOT NULL,
  LastName VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

-- Создание таблицы "Меню"
CREATE TABLE Menu (
  ID INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Description TEXT NOT NULL,
  Price DECIMAL(10,2) NOT NULL,
  RestaurantID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (RestaurantID) REFERENCES Restaurants(ID)
);

-- Создание таблицы "Категории блюд"
CREATE TABLE FoodCategories (
  ID INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  MenuID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (MenuID) REFERENCES Menu(ID)
);

-- Создание таблицы "Блюда"
CREATE TABLE Foods (
  ID INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Description TEXT NOT NULL,
  Price DECIMAL(10,2) NOT NULL,
  CategoryID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (CategoryID) REFERENCES FoodCategories(ID)
);