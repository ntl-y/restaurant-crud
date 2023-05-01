package com.example.demo.controllers;

import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Menu;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MenuController {
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService, RestaurantService restaurantService){
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus")
    public String menu(Model model){
        Iterable<Menu> menus = menuService.getAll();
        model.addAttribute("menus", menus);
        return "menus";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus_new")
    public String newMenu(Model model){
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        Iterable<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("restaurants", restaurants);
        return "menus_new";
    }
    @PostMapping("/menus_new")
    public String createMenu(@ModelAttribute("menu") Menu menu, Model model){
        Iterable<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("restaurants", restaurants);
        int restaurantId = menu.getRestaurant().getId();
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            menu.setRestaurant(restaurant);
            menuService.saveMenu(menu);
        }
        return "redirect:/menus";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus_delete/{id}")
    public String deleteMenu(@PathVariable("id")int id) {
        Optional<Menu> optionalMenu = menuService.getById(id);
        if (optionalMenu.isPresent()) {
            menuService.deleteMenuById(id);
        }
        return "redirect:/menus";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus_edit/{id}")
    public String editMenu(@PathVariable("id") int id, Model model){
        Optional<Menu> optionalMenu = menuService.getById(id);
        Iterable<Restaurant> restaurants = restaurantService.getAll();
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            model.addAttribute("menu", menu);
            model.addAttribute("restaurants", restaurants);
            model.addAttribute("currentRestaurant", menu.getRestaurant());
            return "menus_edit";
        }else{
            return "redirect:/menus";
        }
    }
    @PostMapping("/menus_edit")
    public String updateMenu(@ModelAttribute("menu") Menu menuData, Model model){
        Iterable<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("restaurants", restaurants);
        int restaurantId = menuData.getRestaurant().getId();
        int menuId = menuData.getId();
        Optional<Menu> optionalMenu = menuService.getById(menuId);
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(restaurantId);
        if (optionalMenu.isPresent() && optionalRestaurant.isPresent()){
            Menu menu = optionalMenu.get();
            Restaurant restaurant = optionalRestaurant.get();
            menu.setName(menuData.getName());
            menu.setDescription(menuData.getDescription());
            menu.setRestaurant(menuData.getRestaurant());
            menuService.saveMenu(menu);
        }
        return "redirect:/menus";
    }
}



