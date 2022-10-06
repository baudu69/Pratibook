package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.config.CryptoConfig;
import fr.inextenso.pratibook.config.JwtTokenUtil;
import fr.inextenso.pratibook.dto.JwtRequest;
import fr.inextenso.pratibook.dto.JwtResponse;
import fr.inextenso.pratibook.dto.UserDTO;
import fr.inextenso.pratibook.user.JwtUserDetailService;
import fr.inextenso.pratibook.user.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthController {
	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final JwtUserDetailService userDetailsService;
	private final CryptoConfig cryptoConfig;

	public JwtAuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailService userDetailsService, CryptoConfig cryptoConfig) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.cryptoConfig = cryptoConfig;
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.email(), authenticationRequest.password());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.email());

		final String token = jwtTokenUtil.generateToken(userDetails);

		final List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		final LocalDateTime tokenExp = LocalDateTime.now().plusSeconds(this.cryptoConfig.getExp());

		return ResponseEntity.ok(new JwtResponse(token, roles, tokenExp));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
