package fr.inextenso.pratibook.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CreeId implements Serializable {
    private static final long serialVersionUID = -5139340440492079686L;
    @Column(name = "id_oeuvre", nullable = false, length = 50)
    private Integer idOeuvre;

    @Column(name = "id_auteur", nullable = false)
    private Integer idAuteur;

    public Integer getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public Integer getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Integer idAuteur) {
        this.idAuteur = idAuteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CreeId entity = (CreeId) o;
        return Objects.equals(this.idAuteur, entity.idAuteur) &&
                Objects.equals(this.idOeuvre, entity.idOeuvre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuteur, idOeuvre);
    }

}
