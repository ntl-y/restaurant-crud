package com.example.demo.repositories;

import com.example.demo.entities.FoodCategory;
import org.springframework.data.repository.CrudRepository;

public interface FoodCategoryRepository extends CrudRepository<FoodCategory, Integer> {
}
