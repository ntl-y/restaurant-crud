package com.example.demo.services;

import com.example.demo.entities.Manager;
import com.example.demo.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerRepository repository;
    @Autowired
    public ManagerService(ManagerRepository repository){
        this.repository = repository;
    }
    public Optional<Manager> getById(int id){

        return repository.findById(id);
    }
    public Iterable<Manager> getAll() {
        return repository.findAll();
    }
    public Manager saveManager(Manager manager){
        return repository.save(manager);
    }
    public void deleteManagerById(int id){
        repository.deleteById(id);
    }
}
