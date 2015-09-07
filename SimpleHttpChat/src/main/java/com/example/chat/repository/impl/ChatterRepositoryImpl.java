/**
 * 
 */
package com.example.chat.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.chat.model.Chatter;
import com.example.chat.repository.extend.ChatterRepositoryExtend;
import com.google.gson.Gson;

/**
 * @author ldhuy
 *
 */
public class ChatterRepositoryImpl implements ChatterRepositoryExtend {

	@Autowired
	private MongoTemplate mongoTpl;
	/* (non-Javadoc)
	 * @see com.example.chat.repository.extend.ChatterRepositoryExtend#findByEmail(java.lang.String)
	 */
	@Override
	public Chatter findByEmail(String email) {
		Chatter foundChatter = mongoTpl.findOne(new Query(Criteria.where("email").is(email)), Chatter.class);
		return foundChatter;
	}
	
	@Override
	public List<String> addFriend(String ownerEmail, String friendEmail) {
		Chatter chatter = this.findByEmail(ownerEmail);
		List<String> emailList = chatter.getEmailList();
		if (emailList == null) {
			emailList = new ArrayList<String>();
		}
		if (!emailList.contains(friendEmail)) {
			emailList.add(friendEmail);
			Query query = new Query(Criteria.where("email").is(ownerEmail));
			Update update = new Update();
			Gson gson = new Gson();
			String emailListStr = gson.toJson(emailList);
			update.set("emailList", emailListStr);
			mongoTpl.updateFirst(query, update, Chatter.class);
			chatter = this.findByEmail(ownerEmail);
			return chatter.getEmailList();
		}
		
		return chatter.getEmailList();
	}

}
