package com.ntly.webrestaurant.controllers;

import com.ntly.webrestaurant.models.Manager;
import com.ntly.webrestaurant.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;
    @GetMapping("/manager")
    public String manager(Model model) {
        Iterable<Manager> managers = managerRepository.findAll();
        model.addAttribute("managers", managers);
        model.addAttribute("title", "Managers");
        return "manager";
    }
    @GetMapping("/manager/manager_create")
    public String managerCreateView(Model model) {
        return "manager_create";
    }
//    @PostMapping("/manager")
//    public String managerCreate(@RequestParam String  Model model) {
//        return "";
//    }
//    @PostMapping("/manager_create")
//    public String createManager(Manager manager){
//        managerRepository.save(manager);
//        return "redirect:/manager";
//    }
}
