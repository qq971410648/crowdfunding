package com.crowdfunding.service.impl;

import com.crowdfunding.ajax.RoleVo;
import com.crowdfunding.domain.Role;
import com.crowdfunding.domain.User;
import com.crowdfunding.mapper.UserMapper;
import com.crowdfunding.service.UserService;
import com.crowdfunding.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByLogincct(User user) throws Exception {
        return userMapper.getUserByLogincct(user);
    }

    public void doDel(int id) throws Exception {
        userMapper.deleteByPrimaryKey(id);
    }

    public void doAdd(User user) throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreatetime(dateFormat.format(date));
        //对密码进行加密再保存
        user.setUserpswd(MD5Util.digest(user.getUserpswd()));
        userMapper.insert(user);
    }

    public List<User> getAllUser() throws Exception {
        return userMapper.selectAll();
    }

    public User getUserById(int id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    public void doUpdate(User user) throws Exception {
        userMapper.updateByPrimaryKey(user);
    }

    public List<User> getAllUserByCondition() throws Exception {
        return userMapper.getAllUserByCondition();
    }

    public List<Role> checkAssignedRole(int id) throws Exception {
        return userMapper.checkAssignedRole(id);
    }

    public List<Role> checkAllRole() throws Exception {
        return userMapper.checkAllRole();
    }

    public List<Role> checkNoAssignedRole(int id) throws Exception {
        return userMapper.checkNoAssignedRole(id);
    }

    public void savaUserRole(int userid, RoleVo roleVo) throws Exception {
        userMapper.savaUserRole(userid, roleVo);
    }

    public void delUserRole(int userid, RoleVo roleVo) throws Exception {
        userMapper.delUserRole(userid, roleVo);
    }

    public List<Integer> getUser_Role(int id) {
        return userMapper.getUser_Role(id);
    }

    public void doDelForIds(int[] id) {
        for (int i : id) {
            userMapper.deleteByPrimaryKey(i);
        }
    }
}
