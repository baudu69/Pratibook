package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Disponibilite;
import fr.inextenso.pratibook.model.Etat;

import java.time.LocalDate;

public record InstanceDTO(String codeBarre, Etat etat, Disponibilite disponibilite, String nomReserveur,
                          String prenomReserveur, LocalDate dateRenduTheorique) {
}
