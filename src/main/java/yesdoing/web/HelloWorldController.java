package yesdoing.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/welcome")
	public String helloworld() {
		return "Hello World!";
	}
}
