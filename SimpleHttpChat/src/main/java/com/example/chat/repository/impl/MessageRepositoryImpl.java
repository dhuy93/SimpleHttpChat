/**
 * 
 */
package com.example.chat.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import com.example.chat.repository.extend.MessageRepositoryExtend;

/**
 * @author ldhuy
 *
 */
public class MessageRepositoryImpl implements MessageRepositoryExtend {

	@Autowired
	private MongoTemplate mongoTpl;
	/* (non-Javadoc)
	 * @see com.example.chat.repository.extend.MessageRepositoryExtend#findByChatters(com.example.chat.model.User, com.example.chat.model.User)
	 */
	@Override
	public List<Message> findByChatters(User u1, User u2) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sender").is(u1).andOperator(Criteria.where("receiver").is(u2))
				.orOperator(Criteria.where("sender").is(u2).andOperator(Criteria.where("receiver").is(u1))));
		query.with(new Sort(Sort.Direction.ASC, "createdTime"));
		List<Message> msgs = mongoTpl.find(query, Message.class);
		return msgs;
	}
	
	@Override
	public List<Message> findByChatterEmail(String email1, String email2) {
		User u1 = mongoTpl.findOne(new Query(Criteria.where("email").is(email1)), User.class);
		User u2 = mongoTpl.findOne(new Query(Criteria.where("email").is(email2)), User.class);
		List<Message> msgs = this.findByChatters(u1, u2);
		return msgs;
	}

}
