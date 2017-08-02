package yesdoing.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import yesdoing.dao.QnaRepository;
import yesdoing.domain.Qna;

@Controller
public class QnaController {
	@Autowired
	QnaRepository qnaRepository;
	
	@PostMapping("/questions")
	public ModelAndView create(Qna qna) {
		qnaRepository.insert(qna);
		return new ModelAndView("redirect:/");
	}
		
	@GetMapping("/questions/{index}")
	public ModelAndView show(@PathVariable int index) {
		ModelAndView mav = new ModelAndView("/qna/show");
		mav.addObject("qna", qnaRepository.getQna(index));
		return mav;
	}
}
