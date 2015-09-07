/**
 * 
 */
package com.example.chat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.chat.dto.ChatterDTO;
import com.example.chat.dto.MessageDTO;
import com.example.chat.model.Chatter;
import com.example.chat.model.Message;
import com.example.chat.model.NewChatContentModel;
import com.example.chat.model.User;
import com.example.chat.service.ChatterService;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import com.google.gson.Gson;

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
	@Autowired
	private ChatterService chatterService;
	
	@RequestMapping(value="/chatWindow/{email:.+}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getConversation(@PathVariable(value = "email") String email) {
		int idx = email.lastIndexOf(".jsp");
		if (idx != -1) {
			String trimmedEmail = email.substring(0, idx);
			email = trimmedEmail;
		}
		
		List<String> conversation = new ArrayList<String>();
		User user = userService.findByUsername(email);
		if (user != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			List<MessageDTO> msgList = msgService.getMessagesDTOByUserEmail(username, email);
			for (MessageDTO ms : msgList) {
				String str = ms.getSender() + " @ " + ms.getCreatedTime() + ":\t" + ms.getContent();
				conversation.add(str);
			}
			
		}
		ModelAndView model = new ModelAndView("chat", "conversation", conversation);
		model.addObject("palEmail", email);
		model.addObject("command", new NewChatContentModel());
		return model;
	}
		
	@RequestMapping(value="chatWindow/sendNewMsg", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("newChatContentModel") NewChatContentModel newMessage, ModelMap model) {
		
		Message newMsg = new Message();
		newMsg.setContent(newMessage.getContent());
		newMsg.setCreatedTime(new Date());
		newMsg.setReceiver(newMessage.getReceiver());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String sender = auth.getName();
		newMsg.setSender(sender);
		msgService.addMessage(newMsg);
		
		
		model.addAttribute("palEmail", newMessage.getReceiver());
		
		List<String> conversation = new ArrayList<String>();
		
		List<MessageDTO> msgList = msgService.getMessagesDTOByUserEmail(newMessage.getReceiver(), sender);
		for (MessageDTO ms : msgList) {
			String str = ms.getSender() + " @ " + ms.getCreatedTime() + ":\t" + ms.getContent();
			conversation.add(str);
		}
		model.addAttribute("conversation", conversation);
		String newAddr = "/chat/chatWindow/" + newMessage.getReceiver();
		return newAddr;
	}
	
	@RequestMapping(value = "chatWindow/refreshconversation.html", method=RequestMethod.GET)
	public @ResponseBody String refreshConversation(@RequestParam("palEmail") String palEmail, @RequestParam("newContent") String newContent) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String sender = auth.getName();
		if (! newContent.equals("")) {
			Message newMsg = new Message();
			newMsg.setContent(newContent);
			newMsg.setCreatedTime(new Date());
			newMsg.setReceiver(palEmail);
			newMsg.setSender(sender);
			msgService.addMessage(newMsg);
		}
		List<MessageDTO> msgList = msgService.getMessagesDTOByUserEmail(palEmail, sender);
		Gson gson = new Gson();
		String responseData = gson.toJson(msgList);
		return responseData;
	}
	
	@RequestMapping(value = "addFriend", method = RequestMethod.GET)
	public ModelAndView addFriend(Model model) {
		return new ModelAndView("addFriend", "command", new ChatterDTO());
	}
	
	@RequestMapping(value = "doAddFriend", method = RequestMethod.POST)
	public String doAddFriend(@ModelAttribute("email") String email, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		chatterService.addFriend(username, email);
		
		Chatter chatter = chatterService.getChatterByEmail(username);
		model.addAttribute("chatter", chatter);
		return "userinfo";
	}
}
