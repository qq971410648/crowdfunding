package com.crowdfunding.service.impl;

import com.crowdfunding.domain.Role;
import com.crowdfunding.mapper.RoleMapper;
import com.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRole(String name) throws Exception {
        return roleMapper.selectAll(name);
    }

    public int doAdd(Role role) throws Exception {
        //这里特别说明一下   int doAdd(Role role)  其实阔以就写返回值为void就行   然后再方法里面调用 roleMapper.insert(role); 不过这样mapper会有提示
        //Return value of the method is never used
        return roleMapper.insert(role);
    }

    public void doDel(int id) throws Exception {
        roleMapper.deleteByPrimaryKey(id);
    }

    public Role getRoleById(int id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    public void doUpdate(Role role) throws Exception {
        roleMapper.updateByPrimaryKey(role);
    }
}
