/**
 * 
 */
package com.example.chat.repository.extend;

import java.util.List;

import com.example.chat.model.Chatter;

/**
 * @author ldhuy
 *
 */
public interface ChatterRepositoryExtend {

	public Chatter findByEmail(String email);
	public List<String> addFriend(String ownerEmail, String friendEmail);
}
