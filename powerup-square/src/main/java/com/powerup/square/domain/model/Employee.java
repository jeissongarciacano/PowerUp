package com.powerup.square.domain.model;

public class Employee {
    private Long id;
    private Restaurant restaurant;
    private Long idUser;
    private String field;

    public Employee(Long id, Restaurant restaurant, Long idUser, String field) {
        this.id = id;
        this.restaurant = restaurant;
        this.idUser = idUser;
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
