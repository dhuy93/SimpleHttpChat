/**
 * 
 */
package com.example.chat.service;

import com.example.chat.dto.ChatterDTO;
import com.example.chat.model.Chatter;

/**
 * @author ldhuy
 *
 */
public interface ChatterService {

	Chatter addChatter(Chatter chatter);
	
	Chatter getChatterByEmail(String email);
	ChatterDTO getChatterDTOByEmail(String email);
}
