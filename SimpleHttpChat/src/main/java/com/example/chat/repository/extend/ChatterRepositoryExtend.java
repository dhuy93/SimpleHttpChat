/**
 * 
 */
package com.example.chat.repository.extend;

import com.example.chat.model.Chatter;

/**
 * @author ldhuy
 *
 */
public interface ChatterRepositoryExtend {

	public Chatter findByEmail(String email);
}
