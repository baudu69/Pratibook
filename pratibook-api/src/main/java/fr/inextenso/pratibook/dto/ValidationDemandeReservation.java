package fr.inextenso.pratibook.dto;

import java.io.Serializable;

public record ValidationDemandeReservation(String codeBarre, int idUser) implements Serializable {
}
