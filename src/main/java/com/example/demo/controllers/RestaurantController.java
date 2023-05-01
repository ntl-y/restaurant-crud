package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.ManagerService;
import com.example.demo.services.RestaurantService;

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
    public String restaurant(@RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            Model model) {
        Specification<Restaurant> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("name"), "%" + name + "%"));
        }
        if (address != null && !address.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("address"), "%" + address + "%"));
        }
        if (phone != null && !phone.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("phone"), "%" + phone + "%"));
        }

        Iterable<Restaurant> restaurants = restaurantService.getAll(spec);
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
