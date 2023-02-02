package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.User;

public record UserPublicDTO(Integer id, String email, String nom, String prenom, String adresse, String codePostal,
                            String ville) {
	public static UserPublicDTO fromUtilisateur(User user) {
		return new UserPublicDTO(user.getId(), user.getEmail(), user.getNom(), user.getPrenom(), user.getAdresse(), user.getCodePostal(), user.getVille());
	}
}
