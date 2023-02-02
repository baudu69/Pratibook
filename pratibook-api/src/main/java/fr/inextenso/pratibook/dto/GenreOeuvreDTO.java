package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Genre;

public record GenreOeuvreDTO(int idGenre, String nomGenre) {

	public GenreOeuvreDTO(Genre genre) {
		this(genre.getId(), genre.getNomGenre());
	}

}
