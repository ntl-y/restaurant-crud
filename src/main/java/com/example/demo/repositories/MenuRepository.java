package com.example.demo.repositories;

import com.example.demo.entities.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Integer> {
}
