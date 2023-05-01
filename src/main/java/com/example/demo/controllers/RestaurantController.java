package com.example.demo.controllers;

import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.ManagerService;
import com.example.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Controller
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ManagerService managerService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, ManagerService managerService) {
        this.restaurantService = restaurantService;
        this.managerService = managerService;
    }

    @GetMapping("/restaurants")
    public String restaurant(Model model) {
        Iterable<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/restaurants/restaurants_new")
    public String newRestaurant(Model model) {
        Restaurant restaurant = new Restaurant();
        Iterable<Manager> managers = managerService.getAll();
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("managers", managers);
        return "restaurants_new";
    }

    @PostMapping("/restaurants/restaurants_new")
    public String create(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/{id}/restaurants_edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            Iterable<Manager> managers = managerService.getAll();
            model.addAttribute("restaurant", restaurant);
            model.addAttribute("managers", managers);
            return "restaurants_edit";
        } else {
            return "redirect:/restaurants";
        }
    }

    @PostMapping("restaurants/{id}/restaurants_edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("restaurant") Restaurant restaurantData) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setName(restaurantData.getName());
            restaurant.setAddress(restaurantData.getAddress());
            restaurant.setPhone(restaurantData.getPhone());
            restaurant.setManager(restaurantData.getManager());
            restaurantService.saveRestaurant(restaurant);
        }
        return "redirect:/restaurants";
    }

    @GetMapping("restaurants/{id}/restaurants_delete")
    public String delete(@PathVariable("id") int id) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        if (optionalRestaurant.isPresent()) {
            // Restaurant restaurant = optionalRestaurant.get();
            restaurantService.deleteRestaurantById(id);
        }
        return "redirect:/restaurants";
    }
}
