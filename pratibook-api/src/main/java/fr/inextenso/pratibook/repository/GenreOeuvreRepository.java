package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.GenreOeuvre;
import fr.inextenso.pratibook.model.GenreOeuvreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreOeuvreRepository extends JpaRepository<GenreOeuvre, GenreOeuvreId> {
    List<GenreOeuvre> findById_IdOeuvre(String idOeuvre);
}