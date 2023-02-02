package fr.inextenso.pratibook.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "location")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_location", nullable = false)
	private Integer id;

	@Column(name = "code_barre", nullable = false)
	private String codeBarre;

	@Column(name = "id_employe")
	private Integer idEmploye;

	@Column(name = "id_utilisateur", nullable = false)
	private Integer idUtilisateur;

	@Column(name = "date_reservation")
	private LocalDateTime dateReservation;

	@Column(name = "date_location")
	private LocalDateTime dateLocation;

	@Column(name = "date_rendu_theorique")
	private LocalDate dateRenduTheorique;

	@Column(name = "etat_physique_location")
	@Enumerated(EnumType.ORDINAL)
	private Etat etatPhysiqueLocation;

	@Column(name = "etat_physique_rendu")
	@Enumerated(EnumType.ORDINAL)
	private Etat etatPhysiqueRendu;

	@Column(name = "date_rendu_reel")
	private LocalDateTime dateRenduReel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public Integer getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public LocalDateTime getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(LocalDateTime dateLocation) {
		this.dateLocation = dateLocation;
	}

	public LocalDate getDateRenduTheorique() {
		return dateRenduTheorique;
	}

	public void setDateRenduTheorique(LocalDate dateRenduTheorique) {
		this.dateRenduTheorique = dateRenduTheorique;
	}

	public Etat getEtatPhysiqueLocation() {
		return etatPhysiqueLocation;
	}

	public void setEtatPhysiqueLocation(Etat etatPhysiqueLocation) {
		this.etatPhysiqueLocation = etatPhysiqueLocation;
	}

	public Etat getEtatPhysiqueRendu() {
		return etatPhysiqueRendu;
	}

	public void setEtatPhysiqueRendu(Etat etatPhysiqueRendu) {
		this.etatPhysiqueRendu = etatPhysiqueRendu;
	}

	public LocalDateTime getDateRenduReel() {
		return dateRenduReel;
	}

	public void setDateRenduReel(LocalDateTime dateRenduReel) {
		this.dateRenduReel = dateRenduReel;
	}

}