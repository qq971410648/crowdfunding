package com.crowdfunding.mapper;

import com.crowdfunding.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    /**
     * 查询所有菜单信息
     * @return
     */
    List<Menu> getAllMenu();
}
