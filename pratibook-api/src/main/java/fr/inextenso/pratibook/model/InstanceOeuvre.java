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
    private Oeuvre oeuvre;

    @Column(name = "etat_disponibilite")
    @Enumerated(EnumType.ORDINAL)
    private Disponibilite etatDisponibilite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Disponibilite getEtatDisponibilite() {
        return etatDisponibilite;
    }

    public void setEtatDisponibilite(Disponibilite etatDisponibilite) {
        this.etatDisponibilite = etatDisponibilite;
    }

}
