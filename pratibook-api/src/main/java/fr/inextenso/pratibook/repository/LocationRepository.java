package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}