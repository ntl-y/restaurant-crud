package com.example.demo.controllers;

import com.example.demo.entities.FoodCategory;
import com.example.demo.entities.Food;
import com.example.demo.entities.Manager;
import com.example.demo.entities.Food;
import com.example.demo.services.FoodCategoryService;
import com.example.demo.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class FoodController {
    private final FoodCategoryService foodCategoryService;
    private final FoodService foodService;
    @Autowired
    public FoodController(FoodService foodService, FoodCategoryService foodCategoryService){
        this.foodCategoryService = foodCategoryService;
        this.foodService = foodService;
    }
    //  ----------------------------------------------------------------------------
//    @GetMapping("/foods")
//    public String food(Model model){
//        Iterable<Food> foods = foodService.getAll();
//        model.addAttribute("foods", foods);
//        return "foods";
//    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/foods_new")
    public String newFood(Model model){
        Food food = new Food();
        model.addAttribute("food", food);
        Specification<FoodCategory> spec = Specification.where(null);
        Iterable<FoodCategory> foodCategories = foodCategoryService.getAll(spec);
        model.addAttribute("foodCategories", foodCategories);
        return "foods_new";
    }
    @PostMapping("/foods_new")
    public String createFood(@ModelAttribute("food") Food food, Model model){
        Specification<FoodCategory> spec = Specification.where(null);
        Iterable<FoodCategory> foodCategories = foodCategoryService.getAll(spec);
        model.addAttribute("foodCategories", foodCategories);
        int foodCategoryId = food.getFoodCategory().getId();
        Optional<FoodCategory> optionalFoodCategory = foodCategoryService.getById(foodCategoryId);
        if (optionalFoodCategory.isPresent()) {
            FoodCategory foodCategory = optionalFoodCategory.get();
            food.setFoodCategory(foodCategory);
            foodService.saveFood(food);
        }
        return "redirect:/foods";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/foods_delete/{id}")
    public String deleteFood(@PathVariable("id")int id) {
        Optional<Food> optionalFood = foodService.getById(id);
        if (optionalFood.isPresent()) {
            foodService.deleteFoodById(id);
        }
        return "redirect:/foods";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/foods_edit/{id}")
    public String editFood(@PathVariable("id") int id, Model model){
        Optional<Food> optionalFood = foodService.getById(id);
        Specification<FoodCategory> spec = Specification.where(null);
        Iterable<FoodCategory> foodCategories = foodCategoryService.getAll(spec);
        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            model.addAttribute("food", optionalFood);
            model.addAttribute("foodCategories", foodCategories);

            model.addAttribute("currentFoodCategory", food.getFoodCategory());
            return "foods_edit";
        }else{
            return "redirect:/foods";
        }
    }
    @PostMapping("/foods_edit")
    public String updateFood(@ModelAttribute("food") Food foodData, Model model){
        Specification<FoodCategory> spec = Specification.where(null);
        Iterable<FoodCategory> foodCategories = foodCategoryService.getAll(spec);
        model.addAttribute("foodCategories", foodCategories);
        int foodCategoryId = foodData.getFoodCategory().getId();
        int foodId = foodData.getId();
        Optional<Food> optionalFood = foodService.getById(foodId);
        Optional<FoodCategory> optionalFoodCategory = foodCategoryService.getById(foodCategoryId);
        if (optionalFood.isPresent() && optionalFoodCategory.isPresent()){
            Food food = optionalFood.get();
            FoodCategory foodCategory = optionalFoodCategory.get();
            food.setName(foodData.getName());
            food.setDescription(foodData.getDescription());
//            try {
//                String priceFloat = String.valueOf(foodData.getPrice());
//                food.setPrice(Float.parseFloat(priceFloat));
//            } catch (NumberFormatException e) {
//                food.setPrice(0.0f);
//            }
            food.setPrice(foodData.getPrice());
            food.setFoodCategory(foodData.getFoodCategory());
            foodService.saveFood(food);
        }
        return "redirect:/foods";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/foods")
    public String food(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String description,
                       @RequestParam(required = false) String id,
                       @RequestParam(required = false) Float price,
                       @RequestParam(required = false) Long foodCategoryId,
                       Model model) {
        Specification<Food> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("name"),  name ));
        }
        if (description != null && !description.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("description"),  description ));
        }
        if (price != null && price != 0) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("price"),  price ));
        }
        if (id != null && !id.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("id"), id));
        }
//        if (managerId != null ) {
//            spec = spec.and((root, query, builder) -> builder.equal(root.get("manager").get("managerId"), managerId));
//        }

        Iterable<Food> foods = foodService.getAll(spec);
        model.addAttribute("foods", foods);
        return "foods";
    }
}
