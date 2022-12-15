package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}