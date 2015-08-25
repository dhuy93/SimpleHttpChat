/**
 * 
 */
package com.example.chat.dto;

import java.util.List;

/**
 * @author ldhuy
 *
 */
public class ChatterDTO {
	
	private String _id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private List<String> emailList;
	
	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the emailList
	 */
	public List<String> getEmailList() {
		return emailList;
	}
	/**
	 * @param emailList the emailList to set
	 */
	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
	
	

}
