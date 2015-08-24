/**
 * 
 */
package com.example.chat.repository.extend;

import java.util.List;

import com.example.chat.model.Message;
import com.example.chat.model.User;

/**
 * @author ldhuy
 *
 */
public interface MessageRepositoryExtend {
	
	public List<Message> findByChatters(User u1, User u2);
	public List<Message> findByChatterEmail(String email1, String email2);

}
