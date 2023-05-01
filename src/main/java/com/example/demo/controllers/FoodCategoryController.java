package com.example.demo.controllers;

import com.example.demo.entities.Menu;
import com.example.demo.entities.FoodCategory;
import com.example.demo.services.MenuService;
import com.example.demo.services.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FoodCategoryController {
    private final MenuService menuService;
    private final FoodCategoryService foodCategoryService;
    @Autowired
    public FoodCategoryController(FoodCategoryService foodCategoryService, MenuService menuService){
        this.menuService = menuService;
        this.foodCategoryService = foodCategoryService;
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/food_categories")
    public String foodCategory(Model model){
        Iterable<FoodCategory> foodCategories = foodCategoryService.getAll();
        model.addAttribute("foodCategories", foodCategories);
        return "food_categories";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/food_categories_new")
    public String newFoodCategory(Model model){
        FoodCategory foodCategory = new FoodCategory();
        model.addAttribute("foodCategory", foodCategory);
        Iterable<Menu> menus = menuService.getAll();
        model.addAttribute("menus", menus);
        return "food_categories_new";
    }
    @PostMapping("/food_categories_new")
    public String createFoodCategory(@ModelAttribute("foodCategory") FoodCategory foodCategory, Model model){
        Iterable<Menu> menus = menuService.getAll();
        model.addAttribute("menus", menus);
        int menuId = foodCategory.getMenu().getId();
        Optional<Menu> optionalMenu = menuService.getById(menuId);
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            foodCategory.setMenu(menu);
            foodCategoryService.saveFoodCategory(foodCategory);
        }
        return "redirect:/food_categories";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/food_categories_delete/{id}")
    public String deleteFoodCategory(@PathVariable("id")int id) {
        Optional<FoodCategory> optionalFoodCategory = foodCategoryService.getById(id);
        if (optionalFoodCategory.isPresent()) {
            foodCategoryService.deleteFoodCategoryById(id);
        }
        return "redirect:/food_categories";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/food_categories_edit/{id}")
    public String editFoodCategory(@PathVariable("id") int id, Model model){
        Optional<FoodCategory> optionalFoodCategory = foodCategoryService.getById(id);
        Iterable<Menu> menus = menuService.getAll();
        if (optionalFoodCategory.isPresent()) {
            FoodCategory foodCategory = optionalFoodCategory.get();
            model.addAttribute("foodCategory", optionalFoodCategory);
            model.addAttribute("menus", menus);

            model.addAttribute("currentMenu", foodCategory.getMenu());
            return "food_categories_edit";
        }else{
            return "redirect:/food_categories";
        }
    }
    @PostMapping("/food_categories_edit")
    public String updateFoodCategory(@ModelAttribute("foodCategory") FoodCategory foodCategoryData, Model model){
        Iterable<Menu> menus = menuService.getAll();
        model.addAttribute("menus", menus);
        int menuId = foodCategoryData.getMenu().getId();
        int foodCategoryId = foodCategoryData.getId();
        Optional<FoodCategory> optionalFoodCategory = foodCategoryService.getById(foodCategoryId);
        Optional<Menu> optionalMenu = menuService.getById(menuId);
        if (optionalFoodCategory.isPresent() && optionalMenu.isPresent()){
            FoodCategory foodCategory = optionalFoodCategory.get();
            Menu menu = optionalMenu.get();
            foodCategory.setName(foodCategoryData.getName());
            foodCategory.setMenu(foodCategoryData.getMenu());
            foodCategoryService.saveFoodCategory(foodCategory);
        }
        return "redirect:/food_categories";
    }
}
