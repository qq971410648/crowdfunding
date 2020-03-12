package com.crowdfunding.service;

import com.crowdfunding.ajax.RoleVo;
import com.crowdfunding.domain.Role;
import com.crowdfunding.domain.User;

import java.util.List;

public interface UserService {

    User getUserByLogincct(User user) throws Exception;

    void doDel(int id) throws Exception;

    void doAdd(User user) throws Exception;

    User getUserById(int id) throws Exception;

    void doUpdate(User user) throws Exception;

    List<User> getAllUserByCondition() throws Exception;

    List<Role> checkAssignedRole(int id) throws Exception;

    List<Role> checkNoAssignedRole(int id) throws Exception;

    void savaUserRole(int userid, RoleVo roleVo) throws Exception;

    void delUserRole(int userid, RoleVo roleVo) throws Exception;

    List<Integer> getUser_Role(int id);

    void doDelForIds(int[] id);
}
