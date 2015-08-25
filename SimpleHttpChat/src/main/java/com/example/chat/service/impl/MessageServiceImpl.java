/**
 * 
 */
package com.example.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.chat.dto.MessageDTO;
import com.example.chat.model.Message;
import com.example.chat.repository.MessageRepository;
import com.example.chat.service.MessageService;

/**
 * @author ldhuy
 *
 */
@Service("msgService")
@Repository
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository msgrepo;
	/* (non-Javadoc)
	 * @see com.example.chat.service.MessageService#addMessage(com.example.chat.model.Message)
	 */
	@Override
	public Message addMessage(Message msg) {
		Message savedMsg = msgrepo.save(msg);
		return savedMsg;
	}

	/* (non-Javadoc)
	 * @see com.example.chat.service.MessageService#getMessagesByUserEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Message> getMessagesByUserEmail(String email1, String email2) {
		List<Message> foundMsgList = msgrepo.findByChatterEmail(email1, email2);
		return foundMsgList;
	}

	/* (non-Javadoc)
	 * @see com.example.chat.service.MessageService#getMessagesDTOByUserEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public List<MessageDTO> getMessagesDTOByUserEmail(String email1, String email2) {
		List<Message> foundMsgsList = this.getMessagesByUserEmail(email1, email2);
		List<MessageDTO> msgDTOList = new ArrayList<MessageDTO>();
		
		for(Message msg : foundMsgsList) {
			MessageDTO dto = new MessageDTO();
			dto.set_id(msg.getId().toString());
			dto.setSender(msg.getSender().toString());
			dto.setReceiver(msg.getReceiver().toString());
			dto.setCreatedTime(msg.getCreatedTime().toString());
			dto.setContent(msg.getContent());
			msgDTOList.add(dto);
		}
		return msgDTOList;
	}

}
