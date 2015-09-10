/**
 * 
 */
package com.example.chat.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.chat.dto.ChatterDTO;
import com.example.chat.service.ChatterService;

/**
 * @author ldhuy
 *
 */
@Controller
public class LoginController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ChatterService chatterService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "success", required = false) String success) {
		// Get current logged in user (if there is one)
		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CHATTER"))) {
			model.addObject("loggedIn", true);
			model.addObject("user", auth.getName());
		} else {
			model.addObject("loggedIn", false);
		}

		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		if (success != null) {
			return this.viewUserInfo();
		}
		return model;

	}

	@RequestMapping(value = "/login/success", method = RequestMethod.GET)
	public ModelAndView handleLoginSuccess(@RequestParam("success") String success, Map<String, Object> model) {
		return this.viewUserInfo();

	}

	@RequestMapping(value = "userinfo", method = RequestMethod.GET)
	public ModelAndView viewUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		ChatterDTO chatter = chatterService.getChatterDTOByEmail(username);
		return new ModelAndView("userinfo", "chatter", chatter);
	}

}
