package fr.inextenso.pratibook.dto;

import fr.inextenso.pratibook.model.Etat;
import fr.inextenso.pratibook.model.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public record EmpruntDTO(
		String codeBarre,
		LocalDateTime dateReservation,
		LocalDateTime dateLocation,
		LocalDate dateRenduTheorique,
		LocalDateTime dateRenduReel,
		Integer etatPhysiqueLocation,
		Integer etatPhysiqueRendu
) {

	public EmpruntDTO(Location location) {
		this(
				location.getCodeBarre(),
				location.getDateReservation(),
				location.getDateLocation(),
				location.getDateRenduTheorique(),
				location.getDateRenduReel(),
				Optional.ofNullable(location.getEtatPhysiqueLocation())
						.map(Etat::getValue)
						.orElse(null),
				Optional.ofNullable(location.getEtatPhysiqueRendu())
						.map(Etat::getValue)
						.orElse(null)
		);
	}

}
