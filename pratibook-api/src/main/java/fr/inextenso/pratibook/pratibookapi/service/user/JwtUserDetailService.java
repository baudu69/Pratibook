package fr.inextenso.pratibook.pratibookapi.service.user;

import fr.inextenso.pratibook.pratibookapi.dto.UserDTO;
import fr.inextenso.pratibook.pratibookapi.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {
	private final UserRepository userDao;

	private final PasswordEncoder passwordEncoder;

	public JwtUserDetailService(UserRepository userDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userDao.findByUsername(username)
				.map(user -> new User(user.getUsername(), user.getPassword(), new ArrayList<>()))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

	public fr.inextenso.pratibook.pratibookapi.model.User save(UserDTO user) {
		fr.inextenso.pratibook.pratibookapi.model.User newUser = new fr.inextenso.pratibook.pratibookapi.model.User();
		newUser.setUsername(user.username());
		newUser.setPassword(passwordEncoder.encode(user.password()));
		return userDao.save(newUser);
	}
}
