package com.example.demo.repositories;

import com.example.demo.entities.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
}
