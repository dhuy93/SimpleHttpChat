/**
 * 
 */
package com.example.chat.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.chat.model.User;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;

/**
 * @author ldhuy
 *
 */
@Controller
public class ChatController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService msgService;
	
	@RequestMapping(value="/chat/{email}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getConversation(@PathVariable(value = "email") String email) {
		List<String> conversation = new ArrayList<String>();
		User user = userService.findByUsername(email);
		if (user != null) {
			//TODO: get logged in username then get conversation here
//			List<Message> msgList = msgService.getMessagesByUserEmail(email, email);
			System.out.print("");
		}
		
		return new ModelAndView("chat", "conversation", conversation);
	}
}
