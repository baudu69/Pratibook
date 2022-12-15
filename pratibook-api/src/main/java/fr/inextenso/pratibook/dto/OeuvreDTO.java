package fr.inextenso.pratibook.dto;

import java.time.LocalDate;
import java.util.List;

public record OeuvreDTO(String idOeuvre, String titre, LocalDate dateSortie, String isbn, long nbInstanceDisponibles, List<GenreOeuvreDTO> genres, List<AuteurOeuvreDTO> auteurs) {
}
