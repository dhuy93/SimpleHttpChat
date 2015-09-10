/**
 * 
 */
package com.example.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value="register", method=RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "command", new Chatter());
	}
	
	@RequestMapping(value="register/submit", method=RequestMethod.POST)
	public String submitNewUser(@ModelAttribute("chatter") Chatter newChatter, ModelMap model) {
		// Check if the submitted email exists in the database
		Chatter chatterDB = chatterServicce.getChatterByEmail(newChatter.getEmail());
		if (chatterDB != null) {
			model.addAttribute("msg", "Error! The email is already existed in the database. Please try another email.");
			model.addAttribute("command", new Chatter());
			return "register";
		}
		
		String hashedPassword = passwordEncoder.encodePassword(newChatter.getPassword(), null);
		newChatter.setPassword(hashedPassword);
		
		Chatter savedChatter = chatterServicce.saveChatter(newChatter);
		model.addAttribute("chatter", savedChatter);

		User newUser = new User();
		newUser.setPassword(hashedPassword);
		newUser.setSalt("");
		newUser.setRole(1);
		newUser.setUsername(newChatter.getEmail());
		
		userService.saveUser(newUser);
		return "login";
	}
}
