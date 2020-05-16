package com.crowdfunding.domain;

import java.util.List;

public class User_ {
    private int id;
    private String username;
    private String address;
    private List<Orders> ordersList;

    @Override
    public String toString() {
        return "User_{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", ordersList=" + ordersList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public User_() {
    }

    public User_(int id, String username, String address, List<Orders> ordersList) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.ordersList = ordersList;
    }
}
