package com.example.demo.controllers;

import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
public class ManagerController {
  private final ManagerService managerService;
  @Autowired
  public ManagerController(ManagerService managerService){
    this.managerService = managerService;
  }
//  ----------------------------------------------------------------------------
//  @GetMapping("/managers")
//  public String manager(Model model){
//    Iterable<Manager> managers = managerService.getAll();
//    model.addAttribute("managers", managers);
//    return "managers";
//  }
//  ----------------------------------------------------------------------------
  @GetMapping("/managers_new")
  public String newManager(Model model){
    Manager manager = new Manager();
    model.addAttribute("manager", manager);
    return "managers_new";
  }
  @PostMapping("/managers_new")
  public String createManager(@ModelAttribute("manager") Manager manager){
    managerService.saveManager(manager);
    return "redirect:/managers";
  }
//  ----------------------------------------------------------------------------
  @GetMapping("/managers_delete/{id}")
  public String deleteManager(@PathVariable("id")int id) {
    Optional<Manager> optionalManager = managerService.getById(id);
    if (optionalManager.isPresent()) {
      managerService.deleteManagerById(id);
    }
    return "redirect:/managers";
  }
//  ----------------------------------------------------------------------------
  @GetMapping("/managers_edit/{id}")
  public String editManager(@PathVariable("id") int id, Model model){
    Optional<Manager> optionalManager = managerService.getById(id);
    if (optionalManager.isPresent()) {
      model.addAttribute("manager", optionalManager);
      return "managers_edit";
    }else{
      return "redirect:/managers";
    }
  }
  @PostMapping("/managers_edit")
  public String updateManager(@ModelAttribute("manager") Manager managerData){
    int managerId = managerData.getId();
    Optional<Manager> optionalManager = managerService.getById(managerId);
    if (optionalManager.isPresent()){
      Manager manager = optionalManager.get();
      manager.setFirstName(managerData.getFirstName());
      manager.setLastName(managerData.getLastName());
      manager.setEmail(managerData.getEmail());
      manager.setPhone(managerData.getPhone());
      managerService.saveManager(manager);
      }
    return "redirect:/managers";
  }
  //  ----------------------------------------------------------------------------
  @GetMapping("/managers")
  public String manager(@RequestParam(required = false) String firstName,
                        @RequestParam(required = false) String lastName,
                        @RequestParam(required = false) String email,
                        @RequestParam(required = false) String phone,
                        @RequestParam(required = false) String id,
                        Model model) {
    Specification<Manager> spec = Specification.where(null);
    if (firstName != null && !firstName.isEmpty()) {
      spec = spec.and((root, query, builder) -> builder.equal(root.get("firstName"),  firstName ));
    }
    if (lastName != null && !lastName.isEmpty()) {
      spec = spec.and((root, query, builder) -> builder.equal(root.get("lastName"),  lastName ));
    }
    if (email!= null && !email.isEmpty()) {
      spec = spec.and((root, query, builder) -> builder.equal(root.get("email"),  email ));
    }
    if (phone != null && !phone.isEmpty()) {
      spec = spec.and((root, query, builder) -> builder.equal(root.get("phone"),phone));
    }
    if (id != null && !id.isEmpty()) {
      spec = spec.and((root, query, builder) -> builder.equal(root.get("id"), id));
    }
    Iterable<Manager> managers;
    try {
      managers = managerService.getAll(spec);
    } catch (Exception e){
      return "managers";
    }
    model.addAttribute("managers", managers);
    return "managers";
  }
}