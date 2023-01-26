package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Auteur;

public record AuteurOeuvreDTO(int idAuteur, String nomAuteur, String prenomAuteur) {

    public AuteurOeuvreDTO(Auteur auteur) {
        this(auteur.getId(), auteur.getNomAuteur(), auteur.getPrenomAuteur());
    }

}
