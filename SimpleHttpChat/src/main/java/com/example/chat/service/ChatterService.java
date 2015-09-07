/**
 * 
 */
package com.example.chat.service;

import java.util.List;

import com.example.chat.dto.ChatterDTO;
import com.example.chat.model.Chatter;

/**
 * @author ldhuy
 *
 */
public interface ChatterService {

	Chatter saveChatter(Chatter chatter);
	
	Chatter getChatterByEmail(String email);
	ChatterDTO getChatterDTOByEmail(String email);
	
	List<String> addFriend(String ownerEmail, String friendEmail);
}
