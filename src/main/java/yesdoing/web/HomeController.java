package yesdoing.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import yesdoing.dao.QnaRepository;

@Controller
public class HomeController {
	@Autowired
	QnaRepository qnaRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("qnas", qnaRepository.findAll());
		return "/index";
	}
	
}
