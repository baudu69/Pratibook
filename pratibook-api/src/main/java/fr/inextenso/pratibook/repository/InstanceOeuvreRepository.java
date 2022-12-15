package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.InstanceOeuvre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstanceOeuvreRepository extends JpaRepository<InstanceOeuvre, String> {
    List<InstanceOeuvre> findByIdOeuvre_Id(String id);
}