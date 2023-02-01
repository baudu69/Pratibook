package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.UserPublicDTO;
import fr.inextenso.pratibook.model.User;
import fr.inextenso.pratibook.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ServiceUtilisateurTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private ServiceUtilisateur serviceUtilisateur;

	@Test
	void getAllUsers() {
		User user = new User();
		user.setNom("nom");
		user.setPrenom("prenom");
		user.setEmail("mail");
		user.setPassword("motDePasse");
		user.setAdresse("adresse");
		user.setVille("ville");
		user.setCodePostal("codePostal");
		user.setId(1);
		List<UserPublicDTO> expected = List.of(UserPublicDTO.fromUtilisateur(user));
		when(userRepository.findAll()).thenReturn(List.of(user));
		List<UserPublicDTO> actual = serviceUtilisateur.getAllUsers();
		assertEquals(expected, actual);
	}
}