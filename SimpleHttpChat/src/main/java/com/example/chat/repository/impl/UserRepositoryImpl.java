/**
 * 
 */
package com.example.chat.repository.impl;

import java.util.List;

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
	 * @see com.example.chat.repository.extend.UserRepositoryExtend#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		List<User> foundUsers = mongoTpl.find(query, User.class);
		if (foundUsers != null) {
			return foundUsers.get(0);
		} else {
			return null;
		}
	}

}
