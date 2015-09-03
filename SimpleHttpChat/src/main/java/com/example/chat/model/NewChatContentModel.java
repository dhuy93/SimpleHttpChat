/**
 * 
 */
package com.example.chat.model;

/**
 * @author ldhuy
 *
 */
//@Document(collection = "newChatContentModel")
public class NewChatContentModel {
	
	private String content;
	private String receiver;
	
	/**
	 * 
	 */
	public NewChatContentModel() {
		
	}
	
	
	/**
	 * @param content
	 * @param receiver
	 */
	public NewChatContentModel(String content, String receiver) {
		this.content = content;
		this.receiver = receiver;
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

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
