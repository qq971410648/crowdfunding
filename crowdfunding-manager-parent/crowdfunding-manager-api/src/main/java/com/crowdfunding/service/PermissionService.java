package com.crowdfunding.service;

import com.crowdfunding.domain.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 获取所有根节点
     *
     * @return
     */
    Permission getPNode() throws Exception;

    /**
     * 根据id查询子节点
     *
     * @param id
     * @return
     */
    List<Permission> getCNode(int id) throws Exception;
}
