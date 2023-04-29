package com.example.demo.controllers;


import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.services.ManagerService;
import com.example.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ManagerService managerService;
    @GetMapping
    public String restaurant(Model model){
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

    @PostMapping
    public String create(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantService.save(restaurant);
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
    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("restaurant") Restaurant restaurantData) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setName(restaurantData.getName());
            restaurant.setAddress(restaurantData.getAddress());
            restaurant.setPhone(restaurantData.getPhone());
            restaurant.setManager(restaurantData.getManager());
            restaurantService.save(restaurant);
        }
        return "redirect:/restaurants";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        if (optionalRestaurant.isPresent()) {
//            Restaurant restaurant = optionalRestaurant.get();
            restaurantService.deleteById(id);
        }
        return "redirect:/restaurants";
    }
}
