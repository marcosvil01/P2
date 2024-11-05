package model;

import javax.persistence.*;

@Entity
@Table(name = "usuaris")
public class Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "correu")
    private String correu;

    // Constructor sin paràmetres
    public Usuari() {
    }

    // Constructor amb paràmetres
    public Usuari(String nom, String correu) {
        this.nom = nom;
        this.correu = correu;
    }

    // Getters i Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    @Override
    public String toString() {
        return "Usuari [id=" + id + ", nom=" + nom + ", correu=" + correu + "]";
    }
}
