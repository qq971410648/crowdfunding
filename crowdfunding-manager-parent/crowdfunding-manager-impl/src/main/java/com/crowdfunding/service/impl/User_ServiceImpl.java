package com.crowdfunding.service.impl;

import com.crowdfunding.domain.User_;
import com.crowdfunding.mapper.User_Mapper;
import com.crowdfunding.service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_ServiceImpl implements User_Service {

    @Autowired
    private User_Mapper user_mapper;

    @Override
    public User_ getUserAndOrder(int id) {
        return user_mapper.getUserAndOrder(id);
    }
}
