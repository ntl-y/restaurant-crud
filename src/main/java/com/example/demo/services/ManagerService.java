package com.example.demo.services;

import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepository repository;
    @Autowired
    public ManagerService(ManagerRepository repository){
        this.repository = repository;
    }
    public Optional<Manager> getById(int id){

        return repository.findById(Long.valueOf(id));
    }
    public Iterable<Manager> getAll(Specification<Manager> spec) {
        return repository.findAll(spec);
    }
    public Manager saveManager(Manager manager){
        return repository.save(manager);
    }
    public void deleteManagerById(int id){
        repository.deleteById(Long.valueOf(id));
    }
}
