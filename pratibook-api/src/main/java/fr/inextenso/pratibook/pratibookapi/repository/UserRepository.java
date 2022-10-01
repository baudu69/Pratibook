package fr.inextenso.pratibook.pratibookapi.repository;

import fr.inextenso.pratibook.pratibookapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

}