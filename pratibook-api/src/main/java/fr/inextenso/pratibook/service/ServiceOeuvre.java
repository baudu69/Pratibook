package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.AuteurOeuvreDTO;
import fr.inextenso.pratibook.dto.GenreOeuvreDTO;
import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.model.Disponibilite;
import fr.inextenso.pratibook.model.InstanceOeuvre;
import fr.inextenso.pratibook.model.Oeuvre;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOeuvre {

    private final OeuvreRepository oeuvreRepository;

    public ServiceOeuvre(OeuvreRepository oeuvreRepository) {
        this.oeuvreRepository = oeuvreRepository;
    }

    @Transactional
    public List<OeuvreDTO> findAll() {
        List<Oeuvre> oeuvres = oeuvreRepository.findAll();
        return oeuvres
                .stream()
                .map(oeuvre -> new OeuvreDTO(
                        oeuvre.getId(),
                        oeuvre.getTitre(),
                        oeuvre.getAnneeSortie(),
                        oeuvre.getIsbn(),
                        this.getNbInstanceDisponibles(oeuvre),
                        this.getGenreOeuvreDTOs(oeuvre),
                        this.getAuteurOeuvreDTOs(oeuvre)
                ))
                .toList();
    }

    private List<AuteurOeuvreDTO> getAuteurOeuvreDTOs(Oeuvre oeuvre) {
        return oeuvre.getAuteurs().stream()
                .map(AuteurOeuvreDTO::new)
                .toList();
    }

    private List<GenreOeuvreDTO> getGenreOeuvreDTOs(Oeuvre oeuvre) {
        return oeuvre.getGenres().stream()
                .map(GenreOeuvreDTO::new)
                .toList();
    }

    public static long getNbInstanceDisponibles(Oeuvre oeuvre) {
        return oeuvre.getInstances().stream()
                .map(InstanceOeuvre::getEtatDisponibilite)
                .filter(Disponibilite.DISPONIBLE::equals)
                .count();
    }

}
