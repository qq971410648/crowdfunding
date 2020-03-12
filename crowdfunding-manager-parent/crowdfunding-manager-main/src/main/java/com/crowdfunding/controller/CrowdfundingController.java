package com.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crowdfunding")
public class CrowdfundingController {

    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping("/to_Login")
    public String to_Login() {
        return "login";
    }

    /**
     * 跳转主页面
     *
     * @return
     */
    @RequestMapping("/to_main")
    public String to_main() {
        return "main";
    }

    /**
     * 跳转用户页
     *
     * @return
     */
    @RequestMapping("/to_User")
    public String tp_User() {
        return "user";
    }

    /**
     * 跳转用户修改页
     *
     * @return
     */
    @RequestMapping("/to_update")
    public String to_update() {
        return "edit";
    }

    /**
     * 跳转用户添加页
     *
     * @return
     */
    @RequestMapping("/to_Add")
    public String to_Add() {
        return "add";
    }

    /**
     * 跳转角色页面
     *
     * @return
     */
    @RequestMapping("/to_Role")
    public String to_Role() {
        return "role";
    }

    /**
     * 跳转角色添加页面
     *
     * @return
     */
    @RequestMapping("/to_RAdd")
    public String to_RAdd() {
        return "radd";
    }

    /**
     * 跳转角色修改页面
     *
     * @return
     */
    @RequestMapping("/to_RUpdate")
    public String to_RUpdate() {
        return "redit";

    }

    /**
     * 跳转用户分配角色页面
     *
     * @return
     */
    @RequestMapping("/to_AssignRole")
    public String to_AssignRole() {
        return "assignRole";
    }

    /**
     * 跳转许可维护页
     *
     * @return
     */
    @RequestMapping("/to_Permission")
    public String to_Permission() {
        return "permission";
    }

    /**
     * 跳转广告业
     *
     * @return
     */
    @RequestMapping("/to_Advertisement")
    public String to_Advertisement() {
        return "advertisement";
    }

    /**
     * 跳转广告添加页
     *
     * @return
     */
    @RequestMapping("/to_AAdd")
    public String to_AAdd() {
        return "aadd";
    }

    /**
     * 跳转资质维护页面
     *
     * @return
     */
    @RequestMapping("/to_cert")
    public String to_cert() {
        return "cert";
    }
}
