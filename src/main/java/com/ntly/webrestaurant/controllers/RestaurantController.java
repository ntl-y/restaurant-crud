package com.ntly.webrestaurant.controllers;

import com.ntly.webrestaurant.models.Restaurant;
import com.ntly.webrestaurant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @GetMapping("/restaurant")
    public String reservations(Model model) {
        Iterable<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("title", "Restaurants");
        return "restaurant";
    }
}
