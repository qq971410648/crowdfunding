package com.crowdfunding.mapper;

import com.crowdfunding.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    /**
     * 获取所有父节点          select id, pid, name, icon, url from t_permission where pid is null
     *
     * @return
     */
    Permission getPNode();

    /**
     * 获取父节点下的子节点       select id, pid, name, icon, url from t_permission where pid = #{id}  id：就是父节点的id
     *
     * @param id
     * @return
     */
    List<Permission> getCNode(int id);
}