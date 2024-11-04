package model;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "model")
    private String model;

    public Vehicle() {
    }

    public Vehicle(String marca, String model) {
        this.marca = marca;
        this.model = model;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", marca=" + marca + ", model=" + model + "]";
    }
}

@Entity
@Table(name = "cotxes")
class Cotxe extends Vehicle {

    @Column(name = "nombre_de_portes")
    private int nombreDePortes;

    public Cotxe() {
    }

    public Cotxe(String marca, String model, int nombreDePortes) {
        super(marca, model);
        this.nombreDePortes = nombreDePortes;
    }

    // Getters y Setters
    public int getNombreDePortes() {
        return nombreDePortes;
    }

    public void setNombreDePortes(int nombreDePortes) {
        this.nombreDePortes = nombreDePortes;
    }

    @Override
    public String toString() {
        return "Cotxe [marca=" + getMarca() + ", model=" + getModel() + ", nombreDePortes=" + nombreDePortes + "]";
    }
}
