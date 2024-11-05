package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// La clase Mesa representa una mesa en el restaurante.
// Se utiliza la anotación @Entity para definirla como una entidad de JPA (Java Persistence API).
@Entity
@Table(name = "Mesas")  // Define el nombre de la tabla en la base de datos como "Mesas".
public class Mesa {

    // Atributos de la clase Mesa que representan las columnas de la tabla "Mesas" en la base de datos.

    @Id  // Indica que el atributo 'id' es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Genera valores únicos de manera automática.
    @Column(name = "id")  // Define el nombre de la columna en la base de datos.
    private int id;

    @Column(name = "numero")  // Define el nombre de la columna en la base de datos.
    private int numero;

    @Column(name = "capacidad")  // Define el nombre de la columna en la base de datos.
    private int capacidad;

    // Relación uno a muchos con la clase Reserva.
    // Una mesa puede tener muchas reservas, pero cada reserva pertenece a una única mesa.
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    // "mappedBy" indica que la relación es gestionada por el atributo "mesa" en la clase Reserva.
    // "cascade = CascadeType.ALL" significa que cualquier operación realizada en Mesa (ej. eliminación)
    // también se aplicará automáticamente a todas sus reservas asociadas.
    private List<Reserva> reservas = new ArrayList<>();

    // Constructor vacío requerido por JPA.
    public Mesa() {
    }

    // Constructor con parámetros para inicializar los atributos numero y capacidad.
    public Mesa(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
    }

    // Métodos Getter y Setter para acceder y modificar los atributos de la clase.

    // Devuelve el ID de la mesa.
    public int getId() {
        return id;
    }

    // Establece el ID de la mesa.
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el número de la mesa.
    public int getNumero() {
        return numero;
    }

    // Establece el número de la mesa.
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Devuelve la capacidad de la mesa.
    public int getCapacidad() {
        return capacidad;
    }

    // Establece la capacidad de la mesa.
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    // Devuelve la lista de reservas asociadas a la mesa.
    public List<Reserva> getReservas() {
        return reservas;
    }

    // Establece la lista de reservas asociadas a la mesa.
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // Método que devuelve una representación en forma de cadena del objeto Mesa.
    // Útil para imprimir información de la mesa de una manera legible.
    @Override
    public String toString() {
        return "Mesa [id=" + id + ", numero=" + numero + ", capacidad=" + capacidad + "]";
    }
}
