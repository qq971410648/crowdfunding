package com.crowdfunding.mapper;

import com.crowdfunding.ajax.RoleVo;
import com.crowdfunding.domain.Role;
import com.crowdfunding.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据用户id删除用户信息
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 添加用户信息
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 根据用户id修改用户信息
     *
     * @param record
     */
    void updateByPrimaryKey(User record);

    /**
     * 根据用户名查找用户信息
     *
     * @param user
     * @return
     */
    User getUserByLogincct(User user);

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    List<User> getAllUserByCondition();

    /**
     * 根据id查询已分配的角色信息
     *
     * @param id
     * @return
     */
    List<Role> checkAssignedRole(int id);

    /**
     * 查询所有角色信息
     *
     * @return
     */
    List<Role> checkAllRole();

    /**
     * 根据id查询未分配的角色信息
     *
     * @param id
     * @return
     */
    List<Role> checkNoAssignedRole(int id);

    /**
     * 分配角色   左到右
     *
     * @param userid
     * @param roleVo
     */
    void savaUserRole(@Param("userid") int userid, @Param("roleVo") RoleVo roleVo);

    /**
     * 分配角色   右到左
     *
     * @param userid
     * @param roleVo
     */
    void delUserRole(@Param("userid") int userid, @Param("roleVo") RoleVo roleVo);

    /**
     * 根据用户id 查询该用户所具有的角色信息
     *
     * @param id
     * @return
     */
    List<Integer> getUser_Role(int id);
}