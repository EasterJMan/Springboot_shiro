package com.sxt.realm;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxt.domain.User;
import com.sxt.service.PermissionService;
import com.sxt.service.RoleService;
import com.sxt.service.UserService;
import com.sxt.utils.ActiveUser;

public class UserRealm extends AuthorizingRealm {

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permssionService;

	/**
	 * 完成认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		Object credentials = token.getCredentials();// 用户登陆时传过来的
		System.out.println(Arrays.toString((char[]) credentials));
		// 根据用户名查询用户是否存在
		User user = this.userService.queryUserByUserName(username);
		// 返回null说明用户不存在
		if (null != user) {
			// 根据用户名去查询用户拥有哪些角色
			List<String> roles = roleService.queryRolesByUserId(user.getUserid());
			// 根据用户名查询用户拥有哪些权限
			List<String> permissions = this.permssionService.queryPermissionsByUserId(user.getUserid());

			ActiveUser activeUser = new ActiveUser(user, roles, permissions);
			/**
			 * 参数1 用户身份 参数2 用户在数据库里面存放的密码 参数3 当前类名
			 */
			// SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activeUser,
			// user.getPassword(), this.getName());
			/**
			 * 参数1:传到doGetAuthorizationInfo里面getPrimaryPrincipal()的对象或者subject.getPrincipal()
			 * 参数2:hashedCredentials 加密之后的密码 参数3:credentialsSalt 盐
			 */
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername()+user.getAddress());
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getUserpwd(), credentialsSalt,
					this.getName());
			return info;

		}
		return null;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 根据用户名去查询用户拥有哪些角色
		List<String> roles = activeUser.getRoles();
		if (null != roles && roles.size() > 0) {
			// 添加角色
			info.addRoles(roles);
		}
		// 根据用户名查询用户拥有哪些权限
		List<String> permissions = activeUser.getPermissions();
		// 添加权限
		if (null != permissions && permissions.size() > 0) {
			// 添加角色
			info.addStringPermissions(permissions);
		}
		return info;
	}

}
