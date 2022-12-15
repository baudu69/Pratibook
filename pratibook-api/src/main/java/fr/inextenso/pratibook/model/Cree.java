package fr.inextenso.pratibook.model;

import javax.persistence.*;

@Entity
@Table(name = "cree")
public class Cree {
    @EmbeddedId
    private CreeId id;

    @MapsId("idAuteur")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_auteur", nullable = false)
    private Auteur idAuteur;

    public CreeId getId() {
        return id;
    }

    public void setId(CreeId id) {
        this.id = id;
    }

    public Auteur getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Auteur idAuteur) {
        this.idAuteur = idAuteur;
    }

}