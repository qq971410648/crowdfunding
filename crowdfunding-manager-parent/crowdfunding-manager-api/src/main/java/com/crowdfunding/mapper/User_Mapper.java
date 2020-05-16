package com.crowdfunding.mapper;

import com.crowdfunding.domain.User_;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Mapper {
    User_ getUserAndOrder(int id);
}
