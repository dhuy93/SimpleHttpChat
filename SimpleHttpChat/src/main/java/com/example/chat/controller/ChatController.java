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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.chat.dto.MessageDTO;
import com.example.chat.model.Message;
import com.example.chat.model.NewChatContentModel;
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
			//TODO: get logged in username then get conversation here
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
	
//	@RequestMapping(value="/chatWindow", params={"pal"})
//	public @ResponseBody ModelAndView getConversation(@RequestParam(value="pal") String palEmail) {
//		List<String> conversation = new ArrayList<String>();
//		User user = userService.findByUsername(palEmail);
//		if (user != null) {
//			//TODO: get logged in username then get conversation here
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			String username = auth.getName();
//			List<MessageDTO> msgList = msgService.getMessagesDTOByUserEmail(username, palEmail);
//			for (MessageDTO ms : msgList) {
//				String str = ms.getSender() + " @ " + ms.getCreatedTime() + ":\t" + ms.getContent();
//				conversation.add(str);
//			}
//			
//		}
//		ModelAndView model = new ModelAndView("chat", "conversation", conversation);
//		model.addObject("palEmail", palEmail);
//		model.addObject("command", new NewChatContentModel());
//		return model;
//	}
	
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
//		String newAddr = "/chat/chatWindow?pal=" + newMessage.getReceiver();
		String newAddr = "/chat/chatWindow/" + newMessage.getReceiver();
		return newAddr;
	}
}
