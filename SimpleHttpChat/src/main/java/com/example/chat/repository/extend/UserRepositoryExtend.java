/**
 * 
 */
package com.example.chat.repository.extend;

import com.example.chat.model.User;

/**
 * @author ldhuy
 *
 */
public interface UserRepositoryExtend {
	
	public User findByUsername(String username);

}
