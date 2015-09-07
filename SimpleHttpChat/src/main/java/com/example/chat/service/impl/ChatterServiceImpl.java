/**
 * 
 */
package com.example.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.chat.dto.ChatterDTO;
import com.example.chat.model.Chatter;
import com.example.chat.repository.ChatterRepository;
import com.example.chat.service.ChatterService;

/**
 * @author ldhuy
 *
 */
@Service("chatterService")
@Repository
public class ChatterServiceImpl implements ChatterService {

	@Autowired
	private ChatterRepository chatterRepo;
	
	/* (non-Javadoc)
	 * @see com.example.chat.service.UserService#addUser(com.example.chat.model.Chatter)
	 */
	@Override
	public Chatter saveChatter(Chatter chatter) {
		Chatter savedChatter = chatterRepo.save(chatter);
		return savedChatter;
	}

	/* (non-Javadoc)
	 * @see com.example.chat.service.ChatterService#getChatterByEmail(java.lang.String)
	 */
	@Override
	public Chatter getChatterByEmail(String email) {
		Chatter foundChatter = chatterRepo.findByEmail(email);
		return foundChatter;
	}

	/* (non-Javadoc)
	 * @see com.example.chat.service.UserService#getUserDTOByEmail(java.lang.String)
	 */
	@Override
	public ChatterDTO getChatterDTOByEmail(String email) {
		Chatter foundUser = this.getChatterByEmail(email);
		ChatterDTO userdto = new ChatterDTO();
		userdto.set_id(foundUser.getId().toString());
		userdto.setEmail(foundUser.getEmail());
		userdto.setFirstname(foundUser.getFirstname());
		userdto.setLastname(foundUser.getLastname());
		userdto.setPassword(foundUser.getPassword());
		
		return userdto;
	}

	@Override
	public List<String> addFriend(String ownerEmail, String friendEmail) {
		List<String> emailList = chatterRepo.addFriend(ownerEmail, friendEmail);
		chatterRepo.addFriend(friendEmail, ownerEmail);
		
		return emailList;
	}

}
