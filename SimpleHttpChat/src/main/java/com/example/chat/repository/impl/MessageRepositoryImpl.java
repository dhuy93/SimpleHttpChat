/**
 * 
 */
package com.example.chat.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.chat.model.Message;
import com.example.chat.model.Chatter;
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
	public List<Message> findByChatters(Chatter u1, Chatter u2) {
		// Search by user id
		String queryCnt = String.format(
				"{$or:[{\"sender._id\": {$oid:\"%s\"}, \"receiver._id\": {$oid:\"%s\"}}, {\"sender._id\": {$oid:\"%s\"}, \"receiver._id\": {$oid:\"%s\"}}]}",
				u1.getId().toString(), u2.getId().toString(), u2.getId().toString(), u1.getId().toString());
		
		BasicQuery query = new BasicQuery(queryCnt);
		query.with(new Sort(Sort.Direction.ASC, "createdTime"));
		List<Message> msgs = mongoTpl.find(query, Message.class);
		return msgs;
	}
	
	@Override
	public List<Message> findByChatterEmail(String email1, String email2) {
		Chatter u1 = mongoTpl.findOne(new Query(Criteria.where("email").is(email1)), Chatter.class);
		Chatter u2 = mongoTpl.findOne(new Query(Criteria.where("email").is(email2)), Chatter.class);
		List<Message> msgs = this.findByChatters(u1, u2);
		return msgs;
	}

}
