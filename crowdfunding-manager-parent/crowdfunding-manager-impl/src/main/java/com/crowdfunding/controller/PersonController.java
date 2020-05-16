package com.crowdfunding.controller;


import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Person;
import com.crowdfunding.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    /**
     * 查询指定person信息  及  对应的card 信息          一对一，多对一
     * @return
     */
    @RequestMapping("/getPersonAndCard")
    public AjaxResult getPersonAndCard(int id){
        try {
            AjaxResult ajaxResult = new AjaxResult();
            Person personAndCard = personService.getPersonAndCard(id);
            ajaxResult.setData(personAndCard);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false,"查询失败");
        }
    }
}
