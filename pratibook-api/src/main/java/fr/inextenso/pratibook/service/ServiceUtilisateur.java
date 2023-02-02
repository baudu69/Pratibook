package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.UserPublicDTO;
import fr.inextenso.pratibook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUtilisateur {
	private final UserRepository userRepository;

	public ServiceUtilisateur(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserPublicDTO> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserPublicDTO::fromUtilisateur)
				.toList();
	}
}
