package com.ntly.webrestaurant.controllers;

import com.ntly.webrestaurant.models.FoodCategory;
import com.ntly.webrestaurant.repositories.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FoodCategoryController {
    @Autowired
    private FoodCategoryRepository foodCategoryRepository;
    @GetMapping("/food_category")
    public String foodCategory(Model model) {
        Iterable<FoodCategory> foodCategories = foodCategoryRepository.findAll();
        model.addAttribute("foodCategories", foodCategories);
        model.addAttribute("title", "Food Categories");
        return "foodCategory";
    }
}