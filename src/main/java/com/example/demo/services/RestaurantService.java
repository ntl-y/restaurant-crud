package com.example.demo.services;

import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;
    @Autowired
    public RestaurantService(RestaurantRepository repository){
        this.repository = repository;
    }
    public Optional<Restaurant> getById(int id){return repository.findById(Long.valueOf(id));}
    public Iterable<Restaurant> getAll(Specification<Restaurant> spec) {
        return repository.findAll(spec);
    }
    public Restaurant saveRestaurant(Restaurant restaurant){
        return repository.save(restaurant);
    }
    public void deleteRestaurantById(int id){repository.deleteById(Long.valueOf(id));}

}
//    public Iterable<Restaurant> getRestaurants(String name,
//                                               String address,
//                                               String phone,
//                                               String managerFirstName){
//        if (name == null && address == null && phone == null && managerFirstName == null) {
//            return repository.findAll();
//        } else {
//            return repository.findRestaurantByValues(
//                    name != null ? name : "",
//                    address != null ? address : "",
//                    phone != null ? phone : "",
//                    managerFirstName != null ? managerFirstName : ""
//            );
//        }
//    }
//}
