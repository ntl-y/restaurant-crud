package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.StreamSupport;

@Controller
public class MainController {
    private final ManagerService managerService;
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final FoodCategoryService foodCategoryService;
    private final FoodService foodService;
    @Autowired
    public MainController(ManagerService managerService,
                          RestaurantService restaurantService,
                          MenuService menuService,
                          FoodCategoryService foodCategoryService,
                          FoodService foodService){
        this.managerService = managerService;
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.foodCategoryService = foodCategoryService;
        this.foodService = foodService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Приложение \"Ресторан\" ");

        Specification<Manager> managerSpec = Specification.where(null);
        Iterable<Manager> managers = managerService.getAll(managerSpec);
        long managerCount = StreamSupport.stream(managers.spliterator(), false).count();

        Specification<Restaurant> restaurantSpec = Specification.where(null);
        Iterable<Restaurant> restaurants = restaurantService.getAll(restaurantSpec);
        long restaurantCount = StreamSupport.stream(restaurants.spliterator(), false).count();

        Specification<Menu> menuSpec = Specification.where(null);
        Iterable<Menu> menus = menuService.getAll(menuSpec);
        long menuCount = StreamSupport.stream(menus.spliterator(), false).count();

        Specification<FoodCategory> foodCategorySpec = Specification.where(null);
        Iterable<FoodCategory> foodCategories = foodCategoryService.getAll(foodCategorySpec);
        long foodCategoryCount = StreamSupport.stream(foodCategories.spliterator(), false).count();

        Specification<Food> foodSpec = Specification.where(null);
        Iterable<Food> foods = foodService.getAll(foodSpec);
        long foodCount = StreamSupport.stream(foods.spliterator(), false).count();

        model.addAttribute("managerCount", managerCount);
        model.addAttribute("restaurantCount", restaurantCount);
        model.addAttribute("menuCount", menuCount);
        model.addAttribute("foodCategoryCount", foodCategoryCount);
        model.addAttribute("foodCount", foodCount);
        return "home";
    }
}
