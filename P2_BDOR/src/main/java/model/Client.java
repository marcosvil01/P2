package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @ElementCollection
    @CollectionTable(name = "telefons", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "telefon")
    private List<String> telefons;

    public Client() {
    }

    public Client(String nom, List<String> telefons) {
        this.nom = nom;
        this.telefons = telefons;
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

    public List<String> getTelefons() {
        return telefons;
    }

    public void setTelefons(List<String> telefons) {
        this.telefons = telefons;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", nom=" + nom + ", telefons=" + telefons + "]";
    }
}
