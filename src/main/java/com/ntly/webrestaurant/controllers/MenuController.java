package com.ntly.webrestaurant.controllers;

import com.ntly.webrestaurant.models.Menu;
import com.ntly.webrestaurant.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @GetMapping("/menu")
    public String menu(Model model) {
        Iterable<Menu> menus = menuRepository.findAll();
        model.addAttribute("menus", menus);
        model.addAttribute("title", "Menu");
        return "menu";
    }
}
