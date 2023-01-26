package fr.inextenso.pratibook.dto;

import java.util.List;

public record OeuvreDTO(
        Integer idOeuvre,
        String titre,
        Short anneeSortie,
        String isbn,
        long nbInstanceDisponibles,
        List<GenreOeuvreDTO> genres,
        List<AuteurOeuvreDTO> auteurs
) {}
