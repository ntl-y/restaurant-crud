package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description", length = 9999)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RestaurantID", referencedColumnName = "ID")
    private Restaurant restaurant;

    public Menu() {}

    public Menu(String name, String description, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
