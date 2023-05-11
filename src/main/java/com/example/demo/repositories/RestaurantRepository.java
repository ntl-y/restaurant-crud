package com.example.demo.repositories;

import com.example.demo.entities.Restaurant;
import org.hibernate.NotImplementedYetException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    // Дополнительные методы репозитория, если нужны
}