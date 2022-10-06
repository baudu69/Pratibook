package fr.inextenso.pratibook.pratibookapi.dto;

public record UserDTO(String email, String password, String nom, String prenom, String adresse, String codePostal, String ville) {
}
