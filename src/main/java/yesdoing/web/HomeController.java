package yesdoing.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import yesdoing.dao.QnaRepository;
import yesdoing.domain.Qna;

@Controller
public class HomeController {
	@Autowired
	QnaRepository qnaRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		ArrayList<Qna> qnas = qnaRepository.getQnas();
		model.addAttribute("qnas", qnas);
		return "/index";
	}
	
}
