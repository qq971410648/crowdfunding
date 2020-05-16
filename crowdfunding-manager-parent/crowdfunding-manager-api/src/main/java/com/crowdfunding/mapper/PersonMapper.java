package com.crowdfunding.mapper;

import com.crowdfunding.domain.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper {

    Person getPersonAndCard(int id);
}
