package com.ntly.webrestaurant.repositories;


import com.ntly.webrestaurant.models.Manager;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
