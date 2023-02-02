package fr.inextenso.pratibook.ressource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {
	@GetMapping(value = "/**")
	public String error() {
		return "forward:/index.html";
	}
}
