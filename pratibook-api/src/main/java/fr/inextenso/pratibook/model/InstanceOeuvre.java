package fr.inextenso.pratibook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instance_oeuvre")
public class InstanceOeuvre {
	@Id
	@Column(name = "code_barre", nullable = false, length = 50)
	private String codeBarre;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_oeuvre", nullable = false, insertable = false, updatable = false)
	private Oeuvre oeuvre;

	@Column(name = "id_oeuvre", nullable = false)
	private Integer idOeuvre;

	@Column(name = "etat_disponibilite")
	@Enumerated(EnumType.ORDINAL)
	private Disponibilite etatDisponibilite;

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public Oeuvre getOeuvre() {
		return oeuvre;
	}

	public void setOeuvre(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}

	public Disponibilite getEtatDisponibilite() {
		return etatDisponibilite;
	}

	public void setEtatDisponibilite(Disponibilite etatDisponibilite) {
		this.etatDisponibilite = etatDisponibilite;
	}

	public Integer getIdOeuvre() {
		return idOeuvre;
	}

	public void setIdOeuvre(Integer idOeuvre) {
		this.idOeuvre = idOeuvre;
	}
}
