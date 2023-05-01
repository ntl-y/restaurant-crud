package com.example.demo.services;

import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.ManagerRepository;
import com.example.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;
    @Autowired
    public RestaurantService(RestaurantRepository repository){
        this.repository = repository;
    }
    public Optional<Restaurant> getById(int id){
        return repository.findById(id);
    }
    public Iterable<Restaurant> getAll() {
        return repository.findAll();
    }
    public Restaurant saveRestaurant(Restaurant restaurant){
        return repository.save(restaurant);
    }
    public void deleteRestaurantById(int id){
        repository.deleteById(id);
    }
}
