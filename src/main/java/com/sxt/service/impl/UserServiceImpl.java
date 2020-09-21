package com.sxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.domain.User;
import com.sxt.mapper.UserMapper;
import com.sxt.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	

	@Override
	public User queryUserByUserName(String username) {
		User user=userMapper.queryUserByUserName(username);
		return user;
	}

}
