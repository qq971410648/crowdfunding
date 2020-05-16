package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Orders;
import com.crowdfunding.mapper.OrdersMapper;
import com.crowdfunding.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public Orders getOrdersAndProduct(int id) {
        return ordersMapper.getOrdersAndProduct(id);
    }
}
