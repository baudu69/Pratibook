package fr.inextenso.pratibook.dto;

import java.time.LocalDate;
import java.util.Set;

import fr.inextenso.pratibook.model.Auteur;

public record AuteurDTO(
		int idAuteur,
		String nomAuteur,
		String prenomAuteur,
		LocalDate dateNaissanceAuteur,
		LocalDate dateDecesAuteur,
		Set<GenreOeuvreDTO> genres
) {

	public static AuteurDTO fromAuteur(Auteur auteur, Set<GenreOeuvreDTO> genres) {
		return new AuteurDTO(
				auteur.getId(),
				auteur.getNomAuteur(),
				auteur.getPrenomAuteur(),
				auteur.getDateNaissanceAuteur(),
				auteur.getDateDecesAuteur(),
				genres
		);
	}

}
