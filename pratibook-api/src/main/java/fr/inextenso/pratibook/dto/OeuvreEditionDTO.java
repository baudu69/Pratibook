package fr.inextenso.pratibook.dto;

import java.util.List;

public record OeuvreEditionDTO(
        Integer id,
        String titre,
        Short anneeSortie,
        String isbn,
        List<Integer> genres,
        List<Integer> auteurs
) {}
