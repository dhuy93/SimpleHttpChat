/**
 * 
 */
package com.example.chat.service;

import java.util.List;

import com.example.chat.dto.MessageDTO;
import com.example.chat.model.Message;

/**
 * @author ldhuy
 *
 */
public interface MessageService {
	Message addMessage(Message msg);
	
	List<Message> getMessagesByUserEmail(String email1, String email2);
	List<MessageDTO> getMessagesDTOByUserEmail(String email1, String email2);

}
