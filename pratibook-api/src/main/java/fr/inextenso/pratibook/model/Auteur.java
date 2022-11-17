package fr.inextenso.pratibook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "auteur")
public class Auteur {
    @Id
    @Column(name = "id_auteur", nullable = false)
    private Integer id;

    @Column(name = "nom_auteur", length = 50)
    private String nomAuteur;

    @Column(name = "prenom_auteur", length = 50)
    private String prenomAuteur;

    @Column(name = "date_naissance_auteur")
    private LocalDate dateNaissanceAuteur;

    @Column(name = "date_deces_auteur")
    private LocalDate dateDecesAuteur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

    public LocalDate getDateNaissanceAuteur() {
        return dateNaissanceAuteur;
    }

    public void setDateNaissanceAuteur(LocalDate dateNaissanceAuteur) {
        this.dateNaissanceAuteur = dateNaissanceAuteur;
    }

    public LocalDate getDateDecesAuteur() {
        return dateDecesAuteur;
    }

    public void setDateDecesAuteur(LocalDate dateDecesAuteur) {
        this.dateDecesAuteur = dateDecesAuteur;
    }

}