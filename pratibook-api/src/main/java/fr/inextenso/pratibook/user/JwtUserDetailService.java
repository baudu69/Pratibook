package fr.inextenso.pratibook.user;

import fr.inextenso.pratibook.dto.UserDTO;
import fr.inextenso.pratibook.model.User;
import fr.inextenso.pratibook.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		return new UserDetails(this.userDao.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username)));
	}

	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setEmail(user.email());
		newUser.setPassword(passwordEncoder.encode(user.password()));
		newUser.setNom(user.nom());
		newUser.setPrenom(user.prenom());
		newUser.setAdresse(user.adresse());
		newUser.setCodePostal(user.codePostal());
		return userDao.save(newUser);
	}
}
