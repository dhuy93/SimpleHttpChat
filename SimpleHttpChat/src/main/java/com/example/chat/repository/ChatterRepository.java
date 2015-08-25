/**
 * 
 */
package com.example.chat.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.chat.model.Chatter;
import com.example.chat.repository.extend.ChatterRepositoryExtend;

/**
 * @author ldhuy
 *
 */
public interface ChatterRepository extends MongoRepository<Chatter, ObjectId>, ChatterRepositoryExtend {

}
