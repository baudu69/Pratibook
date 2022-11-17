package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {
}