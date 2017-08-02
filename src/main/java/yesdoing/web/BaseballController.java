package yesdoing.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import baseball.Baseball;

@Controller
public class BaseballController {
    ArrayList<Integer> computerBalls;
    
	@GetMapping("/baseball")
	public ModelAndView input(String inputValue) {
		if (computerBalls == null) {
			computerBalls = Baseball.generateComputerBalls();
		}
		
        ArrayList<Integer> userBalls = Baseball.inputUserBalls(inputValue);

        int strike = 0;
        int ball = 0;
        
        for (int i = 0; i < userBalls.size(); i++) {
            int result = Baseball.calculateBall(computerBalls, userBalls.get(i), i);
            if (result == 2) {
                strike++;
            } else if (result == 1) {
                ball++;
            }
        }
		
		ModelAndView mav = new ModelAndView("baseball/result");
		mav.addObject("value", inputValue);
		mav.addObject("strike", strike);
		mav.addObject("ball", ball);
		return mav;
	}
}
