package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Genre;

public record GenreOeuvreDTO(int idGenre, String nomGenre) {

	public static GenreOeuvreDTO fromGenre(Genre genre) {
		return new GenreOeuvreDTO(genre.getId(), genre.getNomGenre());
	}

}
