package com.sxt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.domain.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer perid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer perid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     *  根据用户ID查询用户拥有的权限
     * @param userid
     * @return
     */
	List<Permission> queryPermissionsByUserId(@Param("userid")Integer userid);
}