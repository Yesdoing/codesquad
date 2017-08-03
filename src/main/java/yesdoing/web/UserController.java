package yesdoing.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import yesdoing.dao.UserRepository;
import yesdoing.domain.User;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/users")
	public ModelAndView create(User user) {		
		userRepository.addUser(user);
		return new ModelAndView("redirect:/users");
	}
	
	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("user/list");
		mav.addObject("users", userRepository.getUsers());
		return mav;
	}
	
	@GetMapping("/users/form")
	public ModelAndView form() {
		return new ModelAndView("user/form");
	}
	
	@GetMapping("/users/{index}")
	public ModelAndView show(@PathVariable int index) {
		ModelAndView mav = new ModelAndView("user/profile");
		mav.addObject("user", userRepository.getUser(index));
		return mav;
	}
	
	@GetMapping("/users/{index}/form")
	public ModelAndView updateUser(@PathVariable int index) {
		User user = userRepository.getUser(index);
		ModelAndView mav = new ModelAndView("user/updateForm");
		mav.addObject("index", index);
		mav.addObject("user", user);
		return mav;
	}
	
	@PostMapping("/users/{index}/form")
	public ModelAndView updateSaveUser(@PathVariable int index, User user) {
		userRepository.updateUser(index, user);
		return new ModelAndView("redirect:/users");
	}
}
