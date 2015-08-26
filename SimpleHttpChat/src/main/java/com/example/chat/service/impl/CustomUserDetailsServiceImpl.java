/**
 * 
 */
package com.example.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.chat.repository.UserRepository;
import com.example.chat.service.CustomUserDetailsService;

/**
 * @author ldhuy
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

	@Autowired
	UserRepository userRepo;
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.chat.model.User user = userRepo.findByUsername(username);
		List<GrantedAuthority> authorities = getAuthorities(user.getRole());
		org.springframework.security.core.userdetails.User userDetail = new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		return userDetail;
	}
	public List<GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (role.intValue() == 1) {
			authList.add(new SimpleGrantedAuthority("ROLE_CHATTER"));
		}
		return authList;
		
	}

}
