package fr.inextenso.pratibook.dto;

import java.time.LocalDateTime;

public record DemandeReservationDTO(OeuvreDTO oeuvreDTO, int idUser, LocalDateTime dateDemande) {
}
