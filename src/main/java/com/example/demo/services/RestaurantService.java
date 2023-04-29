package com.example.demo.services;

import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;
    public Iterable<Restaurant> getAll(){
        return repository.findAll();
    }
    public Optional<Restaurant> getById(int id){
        return repository.findById(id);
    }
    public Restaurant save(Restaurant restaurant){
        return repository.save(restaurant);
    }
    public void deleteById(int id){
        repository.deleteById(id);
    }
}
