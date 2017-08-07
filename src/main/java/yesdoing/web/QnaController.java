package yesdoing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.dao.QnaRepository;
import yesdoing.domain.Qna;

@Controller
@RequestMapping("/questions")
public class QnaController {
	@Autowired
	QnaRepository qnaRepository;
	
	@PostMapping("")
	public String create(Qna qna) {
		qnaRepository.save(qna);
		return "redirect:/";
	}
		
	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		model.addAttribute("qna", qnaRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/qna/form";
	}
}
