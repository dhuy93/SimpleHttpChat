package com.example.chat.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.chat.model.User;
import com.example.chat.service.ChatterService;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	MongoTemplate mongoTpl;
	@Autowired
	ChatterService chatterService;
	@Autowired
	MessageService msgService;
	@Autowired
	UserService userService;
	
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
//		Chatter sender = new Chatter();
//		Chatter receiver = new Chatter();
//		sender.setEmail("abc@cba.com");
//		sender.setFirstname("Huy");
//		receiver.setEmail("xyz@xyz.com");
//		receiver.setFirstname("Binh");
//		chatterService.saveChatter(sender);
//		chatterService.saveChatter(receiver);
		
		

		// Get 2 users
//		Chatter u1 = chatterService.getChatterByEmail("abc@cba.com");
//		Chatter u2 = chatterService.getChatterByEmail("xyz@xyz.com");
		
//		Gson gson = new Gson();
//		String msg = gson.toJson(u1);
//		model.addAttribute("msg", msg);
		
//		List<Message> msgList = new ArrayList<Message>();
//		for (int i=0;i<20; ++i) {
//			Message msg = new Message();
//			msg.setSender(u1);
//			msg.setReceiver(u2);
//			msg.setCreatedTime(new Date(System.currentTimeMillis()));
//			msg.setContent("test msg #" + i);
//			msgService.addMessage(msg);
//			msgList.add(msg);
//		}
		
		// Get conversation
//		List<MessageDTO> msgList = msgService.getMessagesDTOByUserEmail(u1.getEmail(), u2.getEmail());
//		Gson gson = new Gson();
//		String msg = gson.toJson(msgList);
//		model.addAttribute("msg", msg);
		
		
		// Save user
//		User user = new User();
//		user.setUsername(System.currentTimeMillis() + ".com");
//		user.setPassword("password");
//		user.setSalt("salt");
//		user.setRole(0);
//		User savedUser = userService.saveUser(user);
//		Gson gson = new Gson();
//		String str = gson.toJson(savedUser);
//		System.out.println(str);
		
		return "home";
	}
	
}
