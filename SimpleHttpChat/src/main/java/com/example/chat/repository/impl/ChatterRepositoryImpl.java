/**
 * 
 */
package com.example.chat.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.chat.model.Chatter;
import com.example.chat.repository.extend.ChatterRepositoryExtend;

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

}
