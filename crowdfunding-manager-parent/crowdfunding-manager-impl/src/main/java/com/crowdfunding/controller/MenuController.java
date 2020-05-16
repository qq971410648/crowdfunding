package com.crowdfunding.controller;

import com.crowdfunding.ajax.AjaxResult;
import com.crowdfunding.domain.Menu;
import com.crowdfunding.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getAllMenu")
    public AjaxResult getAllMenu(){
        AjaxResult result = new AjaxResult();
        try {
            // 查询出所有根菜单信息   根菜单信息下包含了子菜单
            List<Menu> menuList = menuService.getAllMenu();
            result.setData(menuList);
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false,"查询菜单信息失败");
        }
    }
}
