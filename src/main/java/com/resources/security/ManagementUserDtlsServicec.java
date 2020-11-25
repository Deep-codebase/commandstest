package com.resources.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.resources.dao.UserDAO;
import com.resources.entity.UserDTO;

@Component("userdetailservice")
public class ManagementUserDtlsServicec implements UserDetailsService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userdto = userDAO.getUserByEmail(username);
		System.out.println("userdto in userdtls: "+userdto);
		if(userdto != null) {
			return new User(userdto.getEmail(), userdto.getPassword(),
					AuthorityUtils.createAuthorityList(userdto.getRole()));
		}
		else {
			return null;
		}
		
		
	}

}
