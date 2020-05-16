package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Menu;
import com.crowdfunding.mapper.MenuMapper;
import com.crowdfunding.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> getAllMenu() {
        List<Menu> parentList = new ArrayList<>();
        Map<Integer, Menu> parentMap = new HashMap<>();
        // 获取所有菜单
        List<Menu> menuList = menuMapper.getAllMenu();


        for (Menu menu : menuList) {
            // 父菜单
            if (menu.getPid()==0) {
                parentList.add(menu);
                parentMap.put(menu.getId(),menu);
            }
        }


        for (Menu childrenmenu : menuList) {
            //子菜单
            if (childrenmenu.getPid()!=0) {
                int pid = childrenmenu.getPid();
                Menu parent = parentMap.get(pid);
                parent.getChildren().add(childrenmenu);
            }
        }

        return parentList;
    }
}
