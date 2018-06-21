package com.index.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.index.authority.bean.User;
import com.index.authority.mapper.IAuthorityMapper;
import com.index.authority.service.ILoginService;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	public IAuthorityMapper authorityMapper;

	@Override
	public List<User> getUserInfo() {
		try {
			authorityMapper.selectAllUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
