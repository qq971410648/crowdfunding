package com.crowdfunding.service;

import com.crowdfunding.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色信息
     *
     * @return
     */
    List<Role> getAllRole(String name) throws Exception;

    /**
     * 添加角色信息
     *
     * @param role
     * @return
     */
    int doAdd(Role role) throws Exception;

    /**
     * 根据用户id删除角色信息
     *
     * @param id
     */
    void doDel(int id) throws Exception;

    /**
     * 根据id查询角色信息
     *
     * @param id
     * @return
     */
    Role getRoleById(int id) throws Exception;

    /**
     * 更新角色信息
     *
     * @param role
     */
    void doUpdate(Role role) throws Exception;
}
