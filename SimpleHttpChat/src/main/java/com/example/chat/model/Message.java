/**
 * 
 */
package com.example.chat.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.Gson;

/**
 * @author ldhuy
 *
 */
@Document(collection = "messages")
public class Message {

	@Id
	private ObjectId id;
	private Chatter sender;
	private Chatter receiver;
	private Date createdTime;
	private String content;
	
	public Message() {
		
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		String str = gson.toJson(this);
		return str;
	}

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the sender
	 */
	public Chatter getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(Chatter sender) {
		this.sender = sender;
	}

	/**
	 * @return the receiver
	 */
	public Chatter getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(Chatter receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
