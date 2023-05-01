package com.example.demo.services;


import com.example.demo.entities.Food;
import com.example.demo.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository repository;
    @Autowired
    public FoodService(FoodRepository repository){
        this.repository = repository;
    }
    public Optional<Food> getById(int id){return repository.findById(id);}
    public Iterable<Food> getAll() {
        return repository.findAll();
    }
    public Food saveFood(Food food){
        return repository.save(food);
    }
    public void deleteFoodById(int id){repository.deleteById(id);}
}
