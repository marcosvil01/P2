package model;

import javax.persistence.*;

@Entity
@Table(name = "dispositius")
public class Dispositiu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "estat", columnDefinition = "BIT")
    private boolean estat;

    public Dispositiu() {
    }

    public Dispositiu(boolean estat) {
        this.estat = estat;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public boolean isEstat() {
        return estat;
    }

    public void setEstat(boolean estat) {
        this.estat = estat;
    }

    @Override
    public String toString() {
        return "Dispositiu [id=" + id + ", estat=" + (estat ? "encès" : "apagat") + "]";
    }
}
