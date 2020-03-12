package com.crowdfunding.mapper;

import com.crowdfunding.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    /**
     * 根据id删除角色信息
     *
     * @param
     * @return
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 添加角色信息
     *
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 根据id查询角色信息
     *
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 模糊查询角色信息
     *
     * @param name
     * @return
     */
    List<Role> selectAll(@Param("name") String name);

    /**
     * 更新角色信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

}