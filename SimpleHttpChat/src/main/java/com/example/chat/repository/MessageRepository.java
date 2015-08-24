/**
 * 
 */
package com.example.chat.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.chat.model.Message;
import com.example.chat.repository.extend.MessageRepositoryExtend;

/**
 * @author ldhuy
 *
 */
public interface MessageRepository extends MongoRepository<Message, ObjectId>, MessageRepositoryExtend {

}
