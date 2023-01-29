package fr.inextenso.pratibook.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "oeuvre")
public class Oeuvre {
    @Id
    @Column(name = "id_oeuvre", nullable = false)
    private Integer id;

    @Column(name = "titre", length = 50)
    private String titre;

    @Column(name = "annee_sortie")
    private Short anneeSortie;

    @Column(name = "isbn", length = 13)
    private String isbn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cree",
            joinColumns = {@JoinColumn(name = "id_oeuvre")},
            inverseJoinColumns = {@JoinColumn(name = "id_auteur")}
    )
    private Set<Auteur> auteurs;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "genre_oeuvre",
            joinColumns = {@JoinColumn(name = "id_oeuvre")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")}
    )
    private Set<Genre> genres;
    
    @OneToMany(mappedBy = "oeuvre", fetch = FetchType.LAZY)
    private Set<InstanceOeuvre> instances;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Short getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(Short anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(Set<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<InstanceOeuvre> getInstances() {
        return instances;
    }

}
