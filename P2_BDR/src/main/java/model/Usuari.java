package model;

import javax.persistence.*;

@Entity
@Table(name = "usuaris")  // Defineix la taula "usuaris" a la base de dades que correspondrà a aquesta classe
public class Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;  // Identificador únic per a cada usuari, generat automàticament per la base de dades

    @Column(name = "nom")
    private String nom;  // Nom de l'usuari

    @Column(name = "correu")
    private String correu;  // Correu electrònic de l'usuari

    // Constructor buit necessari per a JPA
    public Usuari() {
    }

    // Constructor amb paràmetres per crear un nou usuari amb nom i correu electrònic
    public Usuari(String nom, String correu) {
        this.nom = nom;  // Assignem el nom de l'usuari
        this.correu = correu;  // Assignem el correu electrònic de l'usuari
    }

    // Getters i Setters per accedir i modificar els atributs de l'usuari
    public int getId() {
        return id;  // Retorna l'identificador de l'usuari
    }

    public void setId(int id) {
        this.id = id;  // Assigna l'identificador de l'usuari
    }

    public String getNom() {
        return nom;  // Retorna el nom de l'usuari
    }

    public void setNom(String nom) {
        this.nom = nom;  // Assigna un nou nom a l'usuari
    }

    public String getCorreu() {
        return correu;  // Retorna el correu electrònic de l'usuari
    }

    public void setCorreu(String correu) {
        this.correu = correu;  // Assigna un nou correu electrònic a l'usuari
    }

    // Mètode toString per representar l'usuari en forma de cadena de text
    @Override
    public String toString() {
        return "Usuari [id=" + id + ", nom=" + nom + ", correu=" + correu + "]";
    }
}
