package com.example.demo.services;

import com.example.demo.entities.Menu;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository repository;
    @Autowired
    public MenuService(MenuRepository repository){this.repository = repository;}
    public Optional<Menu> getById(int id){return repository.findById(Long.valueOf(id));}
    public Iterable<Menu> getAll(Specification<Menu> spec) {
        return repository.findAll(spec);
    }
    public Menu saveMenu(Menu menu){return repository.save(menu);}
    public void deleteMenuById(int id){repository.deleteById(Long.valueOf(id));}

}
