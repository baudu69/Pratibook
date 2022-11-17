package fr.inextenso.pratibook.model;

import javax.persistence.*;

@Entity
@Table(name = "genre_oeuvre")
public class GenreOeuvre {
    @EmbeddedId
    private GenreOeuvreId id;

    public GenreOeuvreId getId() {
        return id;
    }

    public void setId(GenreOeuvreId id) {
        this.id = id;
    }

}