package com.example.demo.controllers;

import com.example.demo.entities.Manager;
import com.example.demo.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
public class ManagerController {
  private final ManagerService managerService;

  @Autowired
  public ManagerController(ManagerService managerService) {
    this.managerService = managerService;
  }

  @GetMapping("/managers")
  public String manager(Model model) {
    Iterable<Manager> managers = managerService.getAll();
    model.addAttribute("managers", managers);
    return "managers";
  }

  // @GetMapping("/{id}")
  // public String show(@PathVariable("id") int id, Model model) {
  //   Optional<Manager> optionalManager = managerService.getById(id);
  //   if (optionalManager.isPresent()) {
  //     Manager manager = optionalManager.get();
  //     model.addAttribute("manager", manager);
  //     return "managers/show";
  //   } else {
  //     return "redirect:/managers";
  //   }
  // }

  @GetMapping("/managers/managers_new")
  public String newManager(Model model) {
    Manager manager = new Manager();
    model.addAttribute("manager", manager);
    return "managers_new";
  }

  @PostMapping("/managers/managers_new")
  public String create(@ModelAttribute("manager") Manager manager) {
    managerService.saveManager(manager);
    return "redirect:/managers";
  }

  @GetMapping("/managers/{id}/managers_edit")
  public String edit(@PathVariable("id") int id, Model model) {
    Optional<Manager> optionalManager = managerService.getById(id);
    if (optionalManager.isPresent()) {
      Manager manager = optionalManager.get();
      model.addAttribute("manager", manager);
      return "managers_edit";
    } else {
      return "redirect:/managers";
    }
  }

  @PostMapping("managers/{id}/managers_edit")
  public String update(@PathVariable("id") int id, @ModelAttribute("manager") Manager managerData) {
    Optional<Manager> optionalManager = managerService.getById(id);
    if (optionalManager.isPresent()) {
      Manager manager = optionalManager.get();
      manager.setFirstName(managerData.getFirstName());
      manager.setLastName(managerData.getLastName());
      manager.setEmail(managerData.getEmail());
      manager.setPhone(manager.getPhone());
      managerService.saveManager(manager);
    }
    return "redirect:/managers";
  }

  @GetMapping("managers/{id}/managers_delete")
  public String delete(@PathVariable("id") int id) {
    Optional<Manager> optionalManager = managerService.getById(id);
    if (optionalManager.isPresent()) {
      // Manager manager = optionalManager.get();
      managerService.deleteManagerById(id);
    }
    return "redirect:/managers";
  }
}