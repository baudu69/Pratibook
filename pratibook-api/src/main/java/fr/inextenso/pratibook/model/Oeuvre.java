package fr.inextenso.pratibook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "oeuvre")
public class Oeuvre {
    @Id
    @Column(name = "id_oeuvre", nullable = false, length = 50)
    private String id;

    @Column(name = "titre", length = 50)
    private String titre;

    @Column(name = "date_sortie")
    private LocalDate dateSortie;

    @Column(name = "isbn", length = 13)
    private String isbn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}