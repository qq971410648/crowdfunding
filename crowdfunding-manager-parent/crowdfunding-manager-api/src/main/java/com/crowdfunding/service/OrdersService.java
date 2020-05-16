package com.crowdfunding.service;

import com.crowdfunding.domain.Orders;

public interface OrdersService {
    Orders getOrdersAndProduct(int id);
}
