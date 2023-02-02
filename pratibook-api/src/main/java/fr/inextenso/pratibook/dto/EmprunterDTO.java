package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Etat;

public record EmprunterDTO(String codeBarre, Integer userID, Etat etat) {
}
