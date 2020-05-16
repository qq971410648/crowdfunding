package com.crowdfunding.mapper;

import com.crowdfunding.domain.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper {
    Orders getOrdersAndProduct(int id);
}
