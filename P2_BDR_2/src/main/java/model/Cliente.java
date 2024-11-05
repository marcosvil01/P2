package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase representa el modelo de un cliente en una base de datos.
// Se utiliza la anotación @Entity para definirla como una entidad de JPA (Java Persistence API).
@Entity
@Table(name = "Clientes")  // Define el nombre de la tabla en la base de datos como "Clientes".
public class Cliente {

    // Atributos de la clase Cliente que representan las columnas de la tabla "Clientes" en la base de datos.

    @Id  // Indica que el atributo 'id' es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Genera valores únicos de manera automática.
    @Column(name = "id")  // Define el nombre de la columna en la base de datos.
    private int id;

    @Column(name = "nombre")  // Define el nombre de la columna en la base de datos.
    private String nombre;

    @Column(name = "telefono")  // Define el nombre de la columna en la base de datos.
    private String telefono;

    // Relación uno a muchos con la clase Reserva.
    // Un cliente puede tener muchas reservas, pero cada reserva pertenece a un único cliente.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    // "mappedBy" indica que la relación es gestionada por el atributo "cliente" en la clase Reserva.
    // "cascade = CascadeType.ALL" significa que cualquier operación realizada en Cliente (ej. eliminación)
    // también se aplicará automáticamente a todas sus reservas asociadas.
    private List<Reserva> reservas = new ArrayList<>();

    // Constructor vacío requerido por JPA.
    public Cliente() {
    }

    // Constructor con parámetros para inicializar los atributos nombre y teléfono.
    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Métodos Getter y Setter para acceder y modificar los atributos de la clase.

    // Devuelve el ID del cliente.
    public int getId() {
        return id;
    }

    // Establece el ID del cliente.
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre del cliente.
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre del cliente.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el número de teléfono del cliente.
    public String getTelefono() {
        return telefono;
    }

    // Establece el número de teléfono del cliente.
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Devuelve la lista de reservas asociadas al cliente.
    public List<Reserva> getReservas() {
        return reservas;
    }

    // Establece la lista de reservas asociadas al cliente.
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // Método que devuelve una representación en forma de cadena del objeto Cliente.
    // Útil para imprimir información del cliente de una manera legible.
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + "]";
    }
}
