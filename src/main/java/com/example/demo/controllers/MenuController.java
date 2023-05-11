package com.example.demo.controllers;

import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Menu;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MenuController {
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService, RestaurantService restaurantService){
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }
    //  ----------------------------------------------------------------------------
//    @GetMapping("/menus")
//    public String menu(Model model){
//        Iterable<Menu> menus = menuService.getAll();
//        model.addAttribute("menus", menus);
//        return "menus";
//    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus_new")
    public String newMenu(Model model){
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        Specification<Restaurant> spec = Specification.where(null);
        Iterable<Restaurant> restaurants = restaurantService.getAll(spec);
        model.addAttribute("restaurants", restaurants);
        return "menus_new";
    }
    @PostMapping("/menus_new")
    public String createMenu(@ModelAttribute("menu") Menu menu, Model model){
        Specification<Restaurant> spec = Specification.where(null);
        Iterable<Restaurant> restaurants = restaurantService.getAll(spec);
        model.addAttribute("restaurants", restaurants);
        int restaurantId = menu.getRestaurant().getId();
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            menu.setRestaurant(restaurant);
            menuService.saveMenu(menu);
        }
        return "redirect:/menus";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus_delete/{id}")
    public String deleteMenu(@PathVariable("id")int id) {
        Optional<Menu> optionalMenu = menuService.getById(id);
        if (optionalMenu.isPresent()) {
            menuService.deleteMenuById(id);
        }
        return "redirect:/menus";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus_edit/{id}")
    public String editMenu(@PathVariable("id") int id, Model model){
        Optional<Menu> optionalMenu = menuService.getById(id);
        Specification<Restaurant> spec = Specification.where(null);
        Iterable<Restaurant> restaurants = restaurantService.getAll(spec);
        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            model.addAttribute("menu", menu);
            model.addAttribute("restaurants", restaurants);
            model.addAttribute("currentRestaurant", menu.getRestaurant());
            return "menus_edit";
        }else{
            return "redirect:/menus";
        }
    }
    @PostMapping("/menus_edit")
    public String updateMenu(@ModelAttribute("menu") Menu menuData, Model model){
        Specification<Restaurant> spec = Specification.where(null);
        Iterable<Restaurant> restaurants = restaurantService.getAll(spec);
        model.addAttribute("restaurants", restaurants);
        int restaurantId = menuData.getRestaurant().getId();
        int menuId = menuData.getId();
        Optional<Menu> optionalMenu = menuService.getById(menuId);
        Optional<Restaurant> optionalRestaurant = restaurantService.getById(restaurantId);
        if (optionalMenu.isPresent() && optionalRestaurant.isPresent()){
            Menu menu = optionalMenu.get();
            Restaurant restaurant = optionalRestaurant.get();
            menu.setName(menuData.getName());
            menu.setDescription(menuData.getDescription());
            menu.setRestaurant(menuData.getRestaurant());
            menuService.saveMenu(menu);
        }
        return "redirect:/menus";
    }
    //  ----------------------------------------------------------------------------
    @GetMapping("/menus")
    public String menu(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String id,
                             @RequestParam(required = false) Long restaurantId,
                             Model model) {
        Specification<Menu> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("name"),  name ));
        }
        if (description != null && !description.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("description"),  description));
        }
        if (id != null && !id.isEmpty()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("id"), id));
        }
//        if (managerId != null ) {
//            spec = spec.and((root, query, builder) -> builder.equal(root.get("manager").get("managerId"), managerId));
//        }

        Iterable<Menu> menus = menuService.getAll(spec);
        model.addAttribute("menus", menus);
        return "menus";
    }
}



