package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Auteur;
import fr.inextenso.pratibook.model.Oeuvre;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record AuteurWithOeuvresDTO(
        Integer idAuteur,
        String nomAuteur,
        String prenomAuteur,
        LocalDate dateNaissanceAuteur,
        LocalDate dateDecesAuteur,
        Set<OeuvreListItemDTO> oeuvres
) {

    public AuteurWithOeuvresDTO(Auteur auteur) {
        this(
                auteur.getId(),
                auteur.getNomAuteur(),
                auteur.getPrenomAuteur(),
                auteur.getDateNaissanceAuteur(),
                auteur.getDateDecesAuteur(),
                auteur.getOeuvres().stream()
                        .map(OeuvreListItemDTO::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public record OeuvreListItemDTO(
            Integer idOeuvre,
            String titre,
            Short anneeSortie,
            Set<GenreOeuvreDTO> genres
    ) {

        public OeuvreListItemDTO(Oeuvre oeuvre) {
            this(
                    oeuvre.getId(),
                    oeuvre.getTitre(),
                    oeuvre.getAnneeSortie(),
                    oeuvre.getGenres().stream()
                            .map(GenreOeuvreDTO::new)
                            .collect(Collectors.toUnmodifiableSet())
            );
        }

    }

}
