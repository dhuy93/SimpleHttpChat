/**
 * 
 */
package com.example.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.chat.model.Chatter;
import com.example.chat.model.User;
import com.example.chat.service.ChatterService;
import com.example.chat.service.UserService;

/**
 * @author ldhuy
 *
 */
@Controller
public class RegisterController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ChatterService chatterServicce;
	@Autowired
	private UserService userService;

	@RequestMapping(value="register", method=RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "command", new Chatter());
	}
	
	@RequestMapping(value="register/submit", method=RequestMethod.POST)
	public String submitNewUser(@ModelAttribute("chatter") Chatter newChatter, ModelMap model) {
		// Encode password
		//TODO: change to md5
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(newChatter.getPassword());
		newChatter.setPassword(hashedPassword);
		
		Chatter savedChatter = chatterServicce.saveChatter(newChatter);
		model.addAttribute("email", savedChatter.getEmail());
		model.addAttribute("firstname", savedChatter.getFirstname());
		model.addAttribute("lastname", savedChatter.getLastname());
		model.addAttribute("emaillist", savedChatter.getEmailList());
		
		// Save new chatter to 'users' collection
		User newUser = new User();
		newUser.setPassword(hashedPassword);
		newUser.setRole(1);
		newUser.setUsername(newChatter.getEmail());
		
		userService.saveUser(newUser);
		return "userinfo";
	}
}
