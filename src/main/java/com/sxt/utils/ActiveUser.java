package com.sxt.utils;

import java.util.List;

import com.sxt.domain.User;

public class ActiveUser {
	
	private User user;
	
	private List<String> roles;
	
	private List<String> permissions;
	
	public ActiveUser() {
		// TODO Auto-generated constructor stub
	}

	public ActiveUser(User user, List<String> roles, List<String> permissions) {
		super();
		this.user = user;
		this.roles = roles;
		this.permissions = permissions;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
