/**
 * 
 */
package com.example.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.chat.dto.UserDTO;
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
	private UserRepository userrepo;
	
	/* (non-Javadoc)
	 * @see com.example.chat.service.UserService#addUser(com.example.chat.model.User)
	 */
	@Override
	public User addUser(User user) {
		User savedUser = userrepo.save(user);
		return savedUser;
	}

	/* (non-Javadoc)
	 * @see com.example.chat.service.UserService#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		User foundUser = userrepo.findByEmail(email);
		return foundUser;
	}

	/* (non-Javadoc)
	 * @see com.example.chat.service.UserService#getUserDTOByEmail(java.lang.String)
	 */
	@Override
	public UserDTO getUserDTOByEmail(String email) {
		User foundUser = this.getUserByEmail(email);
		UserDTO userdto = new UserDTO();
		userdto.set_id(foundUser.getId().toString());
		userdto.setEmail(foundUser.getEmail());
		userdto.setFirstname(foundUser.getFirstname());
		userdto.setLastname(foundUser.getLastname());
		userdto.setPassword(foundUser.getPassword());
		
		return userdto;
	}

}
