package fr.inextenso.pratibook.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "demande_reservation")
public class DemandeReservation {
	@EmbeddedId
	private DemandeReservationId id;

	@Column(name = "date_demande")
	private LocalDateTime dateDemande;

	public DemandeReservation() {
	}

	public DemandeReservation(int idOeuvre, int idUser, LocalDateTime dateDemande) {
		this.id = new DemandeReservationId();
		this.id.setIdOeuvre(idOeuvre);
		this.id.setIdUser(idUser);
		this.dateDemande = dateDemande;
	}

	public DemandeReservationId getId() {
		return id;
	}

	public void setId(DemandeReservationId id) {
		this.id = id;
	}

	public LocalDateTime getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(LocalDateTime dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DemandeReservation that = (DemandeReservation) o;
		return Objects.equals(id, that.id) && Objects.equals(dateDemande, that.dateDemande);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dateDemande);
	}
}