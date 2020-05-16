package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.User_;
import com.crowdfunding.service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_")
public class User_Controller {

    @Autowired
    private User_Service user_service;

    /**
     * 查询指定user的信息  及  对应的orders信息       一对多
     * @param id
     * @return
     */
    @RequestMapping("/getUserAndOrder")
    public AjaxResult getUserAndOrder(int id){
        AjaxResult ajaxResult = new AjaxResult();

        try {
            User_ user_ =user_service.getUserAndOrder(id);
            System.out.println(user_);
            ajaxResult.setData(user_);
            ajaxResult.setSuccess(true);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false,"查询失败");
        }
    }

}
