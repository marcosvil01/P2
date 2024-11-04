package model;

import javax.persistence.*;

@Entity
@Table(name = "persones")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "cognom")
    private String cognom;

    public Persona() {
    }

    public Persona(String nom, String cognom) {
        this.nom = nom;
        this.cognom = cognom;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    @Override
    public String toString() {
        return "Persona [id=" + id + ", nom=" + nom + ", cognom=" + cognom + "]";
    }
}

@Entity
@Table(name = "professors")
class Professor extends Persona {

    @Column(name = "departament")
    private String departament;

    public Professor() {
    }

    public Professor(String nom, String cognom, String departament) {
        super(nom, cognom);
        this.departament = departament;
    }

    // Getters y Setters
    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Professor [nom=" + getNom() + ", cognom=" + getCognom() + ", departament=" + departament + "]";
    }
}
