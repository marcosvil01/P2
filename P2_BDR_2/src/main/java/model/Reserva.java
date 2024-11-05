package model;

import javax.persistence.*;
import java.time.LocalDateTime;

// La clase Reserva representa una reserva en el sistema del restaurante.
// Se utiliza la anotación @Entity para definirla como una entidad JPA (Java Persistence API).
@Entity
@Table(name = "reserva")  // Define el nombre de la tabla en la base de datos como "reserva".
public class Reserva {

    // Atributos de la clase Reserva que representan las columnas de la tabla "reserva" en la base de datos.

    @Id  // Indica que el atributo 'id' es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Genera valores únicos de manera automática para el 'id'.
    @Column(name = "id")  // Define el nombre de la columna en la base de datos.
    private int id;

    @Column(name = "fecha_reserva")  // Define el nombre de la columna en la base de datos que almacena la fecha y hora de la reserva.
    private LocalDateTime fechaReserva;

    // Relación muchos a uno con la clase Cliente.
    // Una reserva está asociada a un único cliente, pero un cliente puede tener muchas reservas.
    @ManyToOne
    @JoinColumn(name = "cliente_id")  // Define la columna que actúa como clave foránea para asociar un cliente a una reserva.
    private Cliente cliente;

    // Relación muchos a uno con la clase Mesa.
    // Una reserva está asociada a una única mesa, pero una mesa puede tener muchas reservas a lo largo del tiempo.
    @ManyToOne
    @JoinColumn(name = "mesa_id")  // Define la columna que actúa como clave foránea para asociar una mesa a una reserva.
    private Mesa mesa;

    // Constructor sin parámetros requerido por JPA.
    public Reserva() {
    }

    // Constructor con parámetros para inicializar los atributos fechaReserva, cliente y mesa.
    public Reserva(LocalDateTime fechaReserva, Cliente cliente, Mesa mesa) {
        this.fechaReserva = fechaReserva;  // Asigna la fecha de la reserva.
        this.cliente = cliente;  // Asocia el cliente a la reserva.
        this.mesa = mesa;  // Asocia la mesa a la reserva.
    }

    // Métodos Getter y Setter para acceder y modificar los atributos de la clase.

    // Devuelve el ID de la reserva.
    public int getId() {
        return id;
    }

    // Devuelve la fecha y hora de la reserva.
    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    // Establece la fecha y hora de la reserva.
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    // Devuelve el cliente asociado a la reserva.
    public Cliente getCliente() {
        return cliente;
    }

    // Establece el cliente asociado a la reserva.
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Devuelve la mesa asociada a la reserva.
    public Mesa getMesa() {
        return mesa;
    }

    // Establece la mesa asociada a la reserva.
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    // Método que devuelve una representación en forma de cadena del objeto Reserva.
    // Útil para imprimir información de la reserva de una manera legible.
    @Override
    public String toString() {
        return "Reserva [id=" + id + ", fechaReserva=" + fechaReserva + ", cliente=" + cliente + ", mesa=" + mesa + "]";
    }
}
