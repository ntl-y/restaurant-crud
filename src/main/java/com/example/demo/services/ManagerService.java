package com.example.demo.services;

import com.example.demo.entities.Manager;
import com.example.demo.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository repository;
    public Iterable<Manager> getAll(){
        return repository.findAll();
    }
    public Optional<Manager> getById(int id){
        return repository.findById(id);
    }
    public Manager save(Manager manager){
        return repository.save(manager);
    }
    public void deleteById(int id){
        repository.deleteById(id);
    }
}
