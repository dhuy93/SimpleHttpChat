/**
 * 
 */
package com.example.chat.service;

import com.example.chat.model.User;

/**
 * @author ldhuy
 *
 */
public interface UserService {
	public User saveUser(User user);

	public User findByUsername(String username);
}
