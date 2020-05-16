package com.crowdfunding.domain;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private Double price;
    private List<Orders> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }

    public Product() {
    }

    public Product(int id, String name, Double price, List<Orders> orders) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orders = orders;
    }
}
