package fr.inextenso.pratibook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DemandeReservationId implements Serializable {
	private static final long serialVersionUID = 9071410995052924723L;
	@Column(name = "id_user", nullable = false)
	private Integer idUser;

	@Column(name = "id_oeuvre", nullable = false)
	private Integer idOeuvre;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdOeuvre() {
		return idOeuvre;
	}

	public void setIdOeuvre(Integer idOeuvre) {
		this.idOeuvre = idOeuvre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		DemandeReservationId entity = (DemandeReservationId) o;
		return Objects.equals(this.idUser, entity.idUser) &&
				Objects.equals(this.idOeuvre, entity.idOeuvre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser, idOeuvre);
	}

}