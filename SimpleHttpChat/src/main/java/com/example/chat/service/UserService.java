/**
 * 
 */
package com.example.chat.service;

import com.example.chat.dto.UserDTO;
import com.example.chat.model.User;

/**
 * @author ldhuy
 *
 */
public interface UserService {

	User addUser(User user);
	
	User getUserByEmail(String email);
	UserDTO getUserDTOByEmail(String email);
}
