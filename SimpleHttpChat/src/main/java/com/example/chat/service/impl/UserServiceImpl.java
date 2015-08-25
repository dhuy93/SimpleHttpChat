/**
 * 
 */
package com.example.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.chat.model.User;
import com.example.chat.repository.UserRepository;
import com.example.chat.service.UserService;

/**
 * @author ldhuy
 *
 */
@Service("userService")
@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	/* (non-Javadoc)
	 * @see com.example.chat.service.UserService#saveUser(com.example.chat.model.User)
	 */
	@Override
	public User saveUser(User user) {
		User savedUser = userRepo.save(user);
		return savedUser;
	}

}
