package yesdoing.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import yesdoing.dao.QnaRepository;
import yesdoing.domain.Qna;
import yesdoing.domain.User;

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
	public String form(HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		if(value != null) {
			return "/qna/form";	
		}
		return "/user/login";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id, Model model, HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		System.out.println("updateForm!!!!!!!");
		if(value != null) {
			User user = (User)value;
			
			if(user.getUserId().equals(qnaRepository.findOne(id).getWriter())) {
				model.addAttribute("qna", qnaRepository.findOne(id));
				return "qna/updateForm";
			} else return "qna/errorPages";
		}
		return "qna/errorPages";
	}
	
	@PostMapping("/{id}/form")
	public String update(@PathVariable long id, Qna qna, HttpSession session) {
		Qna updateQna = qnaRepository.findOne(id);
		updateQna.setTitle(qna.getTitle());
		updateQna.setContents(qna.getContents());
		qnaRepository.save(updateQna);
		return "redirect:/";
	}
}
