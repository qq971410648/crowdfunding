package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Person;
import com.crowdfunding.mapper.PersonMapper;
import com.crowdfunding.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    public Person getPersonAndCard(int id){
        return personMapper.getPersonAndCard(id);
    }

}
