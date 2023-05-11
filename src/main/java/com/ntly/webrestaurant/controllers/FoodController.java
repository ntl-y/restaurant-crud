package com.ntly.webrestaurant.controllers;


import com.ntly.webrestaurant.models.Food;
import com.ntly.webrestaurant.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;
    @GetMapping("/food")
    public String food(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        model.addAttribute("title", "Foods");
        return "food";
    }
}