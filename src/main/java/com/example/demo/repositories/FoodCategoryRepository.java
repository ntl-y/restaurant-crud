package com.example.demo.repositories;

import com.example.demo.entities.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long>, JpaSpecificationExecutor<FoodCategory> {
    // Дополнительные методы репозитория, если нужны
}
