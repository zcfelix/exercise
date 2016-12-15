package com.thoughtworks.ioc.bean;

import com.thoughtworks.ioc.annotation.Bean;

@Bean
public class Product {
    private String name;
    private String description;
    private int price;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
