package com.example.chat.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.chat.model.Message;
import com.example.chat.model.User;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	MongoTemplate mongoTpl;
	@Autowired
	UserService userService;
	@Autowired
	MessageService msgService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		// Add 2 new users
//		User sender = new User();
//		User receiver = new User();
//		sender.setEmail("abc@cba.com");
//		sender.setFirstname("Huy");
//		receiver.setEmail("xyz@xyz.com");
//		receiver.setFirstname("Binh");
//		userService.addUser(sender);
//		userService.addUser(receiver);
		
		

		// Get 2 users
		User u1 = userService.getUserByEmail("abc@cba.com");
		User u2 = userService.getUserByEmail("xyz@xyz.com");
//		Gson gson = new Gson();
//		String msg = gson.toJson(u1);
//		model.addAttribute("msg", msg);
		
		List<Message> msgList = new ArrayList<Message>();
		for (int i=0;i<20; ++i) {
			Message msg = new Message();
			msg.setSender(u1);
			msg.setReceiver(u2);
			msg.setCreatedTime(new Date(System.currentTimeMillis()));
			msg.setContent("test msg #" + i);
			msgService.addMessage(msg);
			msgList.add(msg);
		}
		
		
		
		
		return "home";
	}
	
}
