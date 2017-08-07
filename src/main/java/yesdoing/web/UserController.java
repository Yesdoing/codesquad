package yesdoing.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.dao.UserRepository;
import yesdoing.domain.User;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("")
	public String create(User user) {		
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "user/profile";
	}
	
	@GetMapping("/{id}/form")
	public String updateUser(@PathVariable long id, Model model, HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		if(value != null) {
			User user = (User)value;			
			if(user.getId().equals(userRepository.findOne(id).getId())) {
				model.addAttribute("user", user);
				return "/user/updateForm";
			}
		}
		
		return "user/errorPages";
	}
	
	@PostMapping("/{id}/form")
	public String updateSaveUser(@PathVariable long id, User user) {
		User updateUser = userRepository.findOne(id);
		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());
		userRepository.save(updateUser);
		return "redirect:/users";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				session.setAttribute("sessionedUser", user);
				return "redirect:/";
			} else {
				return "/user/login_failed";
			}
		} else return "/user/login_failed";		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
