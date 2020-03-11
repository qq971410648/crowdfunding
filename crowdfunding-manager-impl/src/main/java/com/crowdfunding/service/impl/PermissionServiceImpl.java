package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Permission;
import com.crowdfunding.mapper.PermissionMapper;
import com.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 获取根节点
     *
     * @return
     * @throws Exception
     */
    public Permission getPNode() throws Exception {
        return permissionMapper.getPNode();
    }

    /**
     * 获取父节点的所有子节点
     *
     * @param id
     * @return
     * @throws Exception
     */
    public List<Permission> getCNode(int id) throws Exception {
        return permissionMapper.getCNode(id);
    }
}
