package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.Cree;
import fr.inextenso.pratibook.model.CreeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreeRepository extends JpaRepository<Cree, CreeId> {
    List<Cree> findById_IdOeuvre(String idOeuvre);
}