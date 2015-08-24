/**
 * 
 */
package com.example.chat.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.chat.model.User;
import com.example.chat.repository.extend.UserRepositoryExtend;

/**
 * @author ldhuy
 *
 */
public interface UserRepository extends MongoRepository<User, ObjectId>, UserRepositoryExtend {

}
