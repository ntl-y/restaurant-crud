package com.example.demo.services;

import com.example.demo.entities.FoodCategory;
import com.example.demo.repositories.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodCategoryService {
    private final FoodCategoryRepository repository;
    @Autowired
    public FoodCategoryService(FoodCategoryRepository repository){
        this.repository = repository;
    }
    public Optional<FoodCategory> getById(int id){return repository.findById(id);}
    public Iterable<FoodCategory> getAll() {
        return repository.findAll();
    }
    public FoodCategory saveFoodCategory(FoodCategory foodCategory){
        return repository.save(foodCategory);
    }
    public void deleteFoodCategoryById(int id){repository.deleteById(id);}
}
