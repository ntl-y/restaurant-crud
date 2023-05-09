package com.example.demo.repositories;

import com.example.demo.entities.Restaurant;
import org.hibernate.NotImplementedYetException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
//    Iterable<Restaurant> findRestaurantByValues(String name, String address, String phone, String managerFirstName);
//}
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    // Дополнительные методы репозитория, если нужны
}