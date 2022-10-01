package fr.inextenso.pratibook.pratibookapi.ressource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public ResponseEntity<String> getHello(Authentication authentication) {
		this.logger.info("REST GET Hello");
		return ResponseEntity.ok("Hello World");
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<String> getHelloAdmin(Authentication authentication) {
		this.logger.info("REST GET getHelloAdmin");
		return ResponseEntity.ok("Hello ADMIN");
	}
}
