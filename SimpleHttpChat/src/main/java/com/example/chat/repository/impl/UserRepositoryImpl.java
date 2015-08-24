/**
 * 
 */
package com.example.chat.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.chat.model.User;
import com.example.chat.repository.extend.UserRepositoryExtend;

/**
 * @author ldhuy
 *
 */
public class UserRepositoryImpl implements UserRepositoryExtend {

	@Autowired
	private MongoTemplate mongoTpl;
	/* (non-Javadoc)
	 * @see com.example.chat.repository.extend.UserRepositoryExtend#findByEmail(java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {
		User foundUser = mongoTpl.findOne(new Query(Criteria.where("email").is(email)), User.class);
		return foundUser;
	}

}
