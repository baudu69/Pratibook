package fr.inextenso.pratibook.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenreOeuvreId implements Serializable {
    private static final long serialVersionUID = 5399066202650534497L;
    @Column(name = "id_genre", nullable = false)
    private Integer idGenre;

    @Column(name = "id_oeuvre", nullable = false, length = 50)
    private Integer idOeuvre;

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    public Integer getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenreOeuvreId entity = (GenreOeuvreId) o;
        return Objects.equals(this.idGenre, entity.idGenre) &&
                Objects.equals(this.idOeuvre, entity.idOeuvre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenre, idOeuvre);
    }

}
