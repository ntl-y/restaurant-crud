package com.example.demo.controllers;

import com.example.demo.entities.Manager;
import com.example.demo.entities.Restaurant;
import com.example.demo.services.ManagerService;
import com.example.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class RestaurantController {
    private final ManagerService managerService;
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService, ManagerService managerService){
        this.managerService = managerService;
        this.restaurantService = restaurantService;
    }
//  ----------------------------------------------------------------------------
//    @GetMapping("/restaurants")
//    public String restaurant(Model model){
//        Iterable<Restaurant> restaurants = restaurantService.getAll();
//        model.addAttribute("restaurants", restaurants);
//        return "restaurants";
//    }
//  ----------------------------------------------------------------------------
    @GetMapping("/restaurants_new")
    public String newRestaurant(Model model){
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        Iterable<Manager> managers = managerService.getAll();
        model.addAttribute("managers", managers);
        return "restaurants_new";
    }
    @PostMapping("/restaurants_new")
    public String createRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, Model model){
        Iterable<Manager> managers = managerService.getAll();
        model.addAttribute("managers", managers);
        int managerId = restaurant.getManager().getId();
        Optional<Manager> optionalManager = managerService.getById(managerId);
        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            restaurant.setManager(manager);
            restaurantService.saveRestaurant(restaurant);
        }
        return "redirect:/restaurants";
    }
//  ----------------------------------------------------------------------------
    @GetMapping("/restaurants_delete/{id}")
    public String deleteRestaurant(@PathVariable("id")int id) {
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        if (optionalRestaurant.isPresent()) {
            restaurantService.deleteRestaurantById(id);
        }
        return "redirect:/restaurants";
    }
//  ----------------------------------------------------------------------------
    @GetMapping("/restaurants_edit/{id}")
    public String editRestaurant(@PathVariable("id") int id, Model model){
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(id);
        Iterable<Manager> managers = managerService.getAll();
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            model.addAttribute("restaurant", optionalRestaurant);
            model.addAttribute("managers", managers);

            model.addAttribute("currentManager", restaurant.getManager());
            return "restaurants_edit";
        }else{
            return "redirect:/restaurants";
        }
    }
    @PostMapping("/restaurants_edit")
    public String updateRestaurant(@ModelAttribute("restaurant") Restaurant restaurantData, Model model){
        Iterable<Manager> managers = managerService.getAll();
        model.addAttribute("managers", managers);
        int managerId = restaurantData.getManager().getId();
        int restaurantId = restaurantData.getId();
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(restaurantId);
        Optional<Manager> optionalManager = managerService.getById(managerId);
        if (optionalRestaurant.isPresent() && optionalManager.isPresent()){
            Restaurant restaurant = optionalRestaurant.get();
            Manager manager = optionalManager.get();
            restaurant.setName(restaurantData.getName());
            restaurant.setAddress(restaurantData.getAddress());
            restaurant.setPhone(restaurantData.getPhone());
            restaurant.setManager(restaurantData.getManager());
            restaurantService.saveRestaurant(restaurant);
        }
        return "redirect:/restaurants";
    }
//  ----------------------------------------------------------------------------
//    @GetMapping("/restaurants")
//    public String getRestaurants(Model model,
//                                 @RequestParam(required = false) String name,
//                                 @RequestParam(required = false) String address,
//                                 @RequestParam(required = false) String phone,
//                                 @RequestParam(required = false) String managerName) {
//        Iterable<Restaurant> restaurants = restaurantService.getRestaurants(name, address, phone, managerName);
//        model.addAttribute("restaurants", restaurants);
//        return "restaurants";
//    }
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
}