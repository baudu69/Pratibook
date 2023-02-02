package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.AuteurOeuvreDTO;
import fr.inextenso.pratibook.dto.GenreOeuvreDTO;
import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.dto.OeuvreEditionDTO;
import fr.inextenso.pratibook.exception.NotFoundException;
import fr.inextenso.pratibook.exception.ReferencedEntityException;
import fr.inextenso.pratibook.model.*;
import fr.inextenso.pratibook.repository.AuteurRepository;
import fr.inextenso.pratibook.repository.GenreRepository;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceOeuvre {

    private final OeuvreRepository oeuvreRepository;
    private final AuteurRepository auteurRepository;
    private final GenreRepository genreRepository;

    public ServiceOeuvre(OeuvreRepository oeuvreRepository, AuteurRepository auteurRepository, GenreRepository genreRepository) {
        this.oeuvreRepository = oeuvreRepository;
        this.auteurRepository = auteurRepository;
        this.genreRepository = genreRepository;
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
		                getNbInstanceDisponibles(oeuvre),
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

    public OeuvreDTO findById(Integer idOeuvre) {
        Oeuvre oeuvre = oeuvreRepository.findById(idOeuvre).orElseThrow();
        return new OeuvreDTO(
                oeuvre.getId(),
                oeuvre.getTitre(),
                oeuvre.getAnneeSortie(),
                oeuvre.getIsbn(),
                getNbInstanceDisponibles(oeuvre),
                this.getGenreOeuvreDTOs(oeuvre),
                this.getAuteurOeuvreDTOs(oeuvre)
        );
    }

    public void addOeuvre(OeuvreEditionDTO dto) {
        this.oeuvreRepository.save(createOeuvreFromDTO(dto));
    }

    public void updateOeuvre(OeuvreEditionDTO dto) {
        final Oeuvre oeuvre = this.oeuvreRepository.findById(dto.id())
                .orElseThrow(NotFoundException::new);

        this.updateOeuvreFromDTO(oeuvre, dto);

        this.oeuvreRepository.save(oeuvre);
    }

    public void deleteOeuvre(int id) {
        final Oeuvre oeuvre = this.oeuvreRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        if (oeuvre.getInstances().size() > 0) throw new ReferencedEntityException();

        this.oeuvreRepository.delete(oeuvre);
    }

    private Oeuvre createOeuvreFromDTO(OeuvreEditionDTO dto) {
        final Oeuvre oeuvre = new Oeuvre();

        oeuvre.setTitre(dto.titre());
        oeuvre.setAnneeSortie(dto.anneeSortie());
        oeuvre.setIsbn(dto.isbn());

        final Set<Genre> genres = new HashSet<>();
        for (Integer genreId : dto.genres()) {
            genres.add(this.genreRepository.findById(genreId)
                    .orElseThrow(() -> new NotFoundException("Genre " + genreId)));
        }
        oeuvre.setGenres(genres);

        final Set<Auteur> auteurs = new HashSet<>();
        for (Integer auteurId : dto.auteurs()) {
            auteurs.add(this.auteurRepository.findById(auteurId)
                    .orElseThrow(() -> new NotFoundException("Auteur " + auteurId)));
        }
        oeuvre.setAuteurs(auteurs);

        return oeuvre;
    }

    private void updateOeuvreFromDTO(Oeuvre oeuvre, OeuvreEditionDTO dto) {
        oeuvre.setTitre(dto.titre());
        oeuvre.setAnneeSortie(dto.anneeSortie());
        oeuvre.setIsbn(dto.isbn());

        oeuvre.getGenres().clear();
        for (Integer genreId : dto.genres()) {
            oeuvre.getGenres().add(this.genreRepository.findById(genreId)
                    .orElseThrow(() -> new NotFoundException("Genre " + genreId)));
        }

        oeuvre.getAuteurs().clear();
        for (Integer auteurId : dto.auteurs()) {
            oeuvre.getAuteurs().add(this.auteurRepository.findById(auteurId)
                    .orElseThrow(() -> new NotFoundException("Auteur " + auteurId)));
        }
    }

}
