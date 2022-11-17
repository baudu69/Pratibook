package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OeuvreRepository extends JpaRepository<Oeuvre, String> {
}