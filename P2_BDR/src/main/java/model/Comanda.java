package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comandes")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "producte")
    private String producte;

    @Column(name = "preu")
    private double preu;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "detall")
    private String detall;

    @ManyToOne
    @JoinColumn(name = "usuari_id")
    private Usuari usuari;

    // Constructor vacío
    public Comanda() {}

    // Constructor con parámetros
    public Comanda(String producte, double preu, String detall, Usuari usuari) {
        this.producte = producte;
        this.preu = preu;
        this.data = LocalDateTime.now();
        this.detall = detall;
        this.usuari = usuari;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getProducte() {
        return producte;
    }

    public void setProducte(String producte) {
        this.producte = producte;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getDetall() {
        return detall;
    }

    public void setDetall(String detall) {
        this.detall = detall;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    @Override
    public String toString() {
        return "Comanda [id=" + id + ", producte=" + producte + ", preu=" + preu + ", data=" + data + ", detall=" + detall + ", usuari=" + usuari.getNom() + "]";
    }
}
