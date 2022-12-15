package fr.inextenso.pratibook.model;

import javax.persistence.*;

@Entity
@Table(name = "instance_oeuvre")
public class InstanceOeuvre {
    @Id
    @Column(name = "code_barre", nullable = false, length = 50)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_oeuvre", nullable = false)
    private Oeuvre idOeuvre;

    @Column(name = "etat_disponibilite")
    @Enumerated(EnumType.ORDINAL)
    private Disponibilite etatDisponibilite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Oeuvre getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Oeuvre idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public Disponibilite getEtatDisponibilite() {
        return etatDisponibilite;
    }

    public void setEtatDisponibilite(Disponibilite etatDisponibilite) {
        this.etatDisponibilite = etatDisponibilite;
    }

}