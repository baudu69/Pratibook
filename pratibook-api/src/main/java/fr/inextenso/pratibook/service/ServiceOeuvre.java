package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.AuteurOeuvreDTO;
import fr.inextenso.pratibook.dto.GenreOeuvreDTO;
import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.model.Auteur;
import fr.inextenso.pratibook.model.Disponibilite;
import fr.inextenso.pratibook.model.Oeuvre;
import fr.inextenso.pratibook.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceOeuvre {
    private final OeuvreRepository oeuvreRepository;
    private final GenreOeuvreRepository genreOeuvreRepository;
    private final GenreRepository genreRepository;
    private final CreeRepository creeRepository;
    private final AuteurRepository auteurRepository;
    private final InstanceOeuvreRepository instanceOeuvreRepository;

    public ServiceOeuvre(OeuvreRepository oeuvreRepository, GenreOeuvreRepository genreOeuvreRepository, GenreRepository genreRepository, CreeRepository creeRepository, AuteurRepository auteurRepository, InstanceOeuvreRepository instanceOeuvreRepository) {
        this.oeuvreRepository = oeuvreRepository;
        this.genreOeuvreRepository = genreOeuvreRepository;
        this.genreRepository = genreRepository;
        this.creeRepository = creeRepository;
        this.auteurRepository = auteurRepository;
        this.instanceOeuvreRepository = instanceOeuvreRepository;
    }

    @Transactional
    public List<OeuvreDTO> findAll() {
        List<Oeuvre> oeuvres = oeuvreRepository.findAll();
        return oeuvres
                .stream()
                .map(oeuvre -> new OeuvreDTO(
                        oeuvre.getId(),
                        oeuvre.getTitre(),
                        oeuvre.getDateSortie(),
                        oeuvre.getIsbn(),
                        this.getNbInstanceDisponibles(oeuvre.getId()),
                        this.getGenreOeuvreDTOs(oeuvre.getId()),
                        this.getAuteurOeuvreDTOs(oeuvre.getId())
                ))
                .toList();
    }

    private List<AuteurOeuvreDTO> getAuteurOeuvreDTOs(String idOeuvre) {
        return creeRepository.findById_IdOeuvre(idOeuvre)
                .stream()
                .map(cree -> cree.getId().getIdAuteur())
                .map(idAuteur -> {
                    Auteur auteur = this.auteurRepository.findById(idAuteur).orElseThrow();
                    return new AuteurOeuvreDTO(idAuteur, auteur.getNomAuteur(), auteur.getPrenomAuteur());
                })
                .toList();
    }

    private List<GenreOeuvreDTO> getGenreOeuvreDTOs(String idOeuvre) {
        return genreOeuvreRepository.findById_IdOeuvre(idOeuvre)
                .stream()
                .map(genreOeuvre -> genreOeuvre.getId().getIdGenre())
                .map(idGenre -> new GenreOeuvreDTO(idGenre, this.genreRepository.findById(idGenre).orElseThrow().getNomGenre()))
                .toList();
    }

    private long getNbInstanceDisponibles(String idOeuvre) {
        return this.instanceOeuvreRepository.findByIdOeuvre_Id(idOeuvre)
                .stream()
                .filter(instanceOeuvre -> instanceOeuvre.getEtatDisponibilite().equals(Disponibilite.DISPONIBLE))
                .count();
    }
}
