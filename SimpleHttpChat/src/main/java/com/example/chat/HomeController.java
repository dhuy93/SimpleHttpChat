package com.example.chat;

import java.text.DateFormat;
import java.util.Date;
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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	MongoTemplate mongoTpl;
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
		
		User sender = new User();
		User receiver = new User();
		sender.setEmail("abc@cba.com");
		sender.setFirstname("Huy");
		receiver.setEmail("xyz@xyz.com");
		receiver.setFirstname("Binh");
		Message msg = new Message();
		msg.setSender(sender);
		msg.setReceiver(receiver);
		msg.setCreatedTime(new Date(System.currentTimeMillis()));
		msg.setContent("test 123");
		mongoTpl.insert(msg);
		
		return "home";
	}
	
}