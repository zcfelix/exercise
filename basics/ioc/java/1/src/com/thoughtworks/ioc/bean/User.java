package com.thoughtworks.ioc.bean;


import com.thoughtworks.ioc.annotation.Bean;
import com.thoughtworks.ioc.annotation.Inject;

@Bean
public class User {
    private String id;
    private String name;
    private int age;
    private String email;

    @Inject("Product")
    Product product;

    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Product getProduct() {
        return product;
    }
}
