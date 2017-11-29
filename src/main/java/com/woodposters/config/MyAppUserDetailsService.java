package com.woodposters.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.woodposters.dao.IUserInfoDAO;
import com.woodposters.entity.User;
@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserInfoDAO userInfoDAO;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User activeUserInfo = userInfoDAO.getActiveUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(activeUserInfo.getUserName(),
				activeUserInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}

