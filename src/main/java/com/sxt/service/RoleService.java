package com.sxt.service;

import java.util.List;

public interface RoleService {

	/**
	 * 根据用户ID查询用户拥有的角色
	 * @param userid
	 * @return
	 */
	List<String> queryRolesByUserId(Integer userid);

}
