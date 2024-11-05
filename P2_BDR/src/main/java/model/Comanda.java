package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comandes")  // Definim la taula a la base de dades amb el nom "comandes"
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;  // Identificador únic per a cada comanda, generat automàticament

    @Column(name = "producte")
    private String producte;  // Nom del producte de la comanda

    @Column(name = "preu")
    private double preu;  // Preu del producte

    @Column(name = "data")
    private LocalDateTime data;  // Data i hora en què es va crear la comanda

    @Column(name = "detall")
    private String detall;  // Detalls addicionals de la comanda

    @ManyToOne
    @JoinColumn(name = "usuari_id")
    private Usuari usuari;  // Relació amb la classe Usuari: cada comanda està associada a un usuari

    // Constructor buit, necessari per a l'ús de JPA
    public Comanda() {}

    // Constructor amb paràmetres per crear una nova instància de Comanda amb els detalls proporcionats
    public Comanda(String producte, double preu, String detall, Usuari usuari) {
        this.producte = producte;  // Assignem el producte
        this.preu = preu;  // Assignem el preu del producte
        this.data = LocalDateTime.now();  // Establim la data actual com la data de la comanda
        this.detall = detall;  // Assignem els detalls de la comanda
        this.usuari = usuari;  // Assignem l'usuari a qui pertany la comanda
    }

    // Getters i setters per accedir i modificar els atributs de la classe
    public int getId() {
        return id;  // Retorna l'identificador de la comanda
    }

    public String getProducte() {
        return producte;  // Retorna el nom del producte
    }

    public void setProducte(String producte) {
        this.producte = producte;  // Assigna el nom del producte
    }

    public double getPreu() {
        return preu;  // Retorna el preu del producte
    }

    public void setPreu(double preu) {
        this.preu = preu;  // Assigna el preu del producte
    }

    public LocalDateTime getData() {
        return data;  // Retorna la data de la comanda
    }

    public void setData(LocalDateTime data) {
        this.data = data;  // Assigna una nova data a la comanda
    }

    public String getDetall() {
        return detall;  // Retorna els detalls addicionals de la comanda
    }

    public void setDetall(String detall) {
        this.detall = detall;  // Assigna els detalls de la comanda
    }

    public Usuari getUsuari() {
        return usuari;  // Retorna l'usuari associat a la comanda
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;  // Assigna l'usuari a la comanda
    }

    // Mètode toString per representar l'objecte Comanda en forma de cadena de text
    @Override
    public String toString() {
        return "Comanda [id=" + id + ", producte=" + producte + ", preu=" + preu + ", data=" + data + ", detall=" + detall + ", usuari=" + usuari.getNom() + "]";
    }
}
