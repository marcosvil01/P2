package controller;

import model.Cliente;
import model.Mesa;
import model.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservaController {

    // Fábrica de sesiones de Hibernate (SessionFactory) para gestionar las conexiones a la base de datos.
    private SessionFactory factory;

    // Constructor del controlador de Reserva. Inicializa la SessionFactory usando la configuración de Hibernate.
    public ReservaController() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")  // Carga la configuración de Hibernate desde el archivo 'hibernate.cfg.xml'
                .addAnnotatedClass(Cliente.class)  // Registra la clase Cliente como una entidad manejada por Hibernate
                .addAnnotatedClass(Mesa.class)  // Registra la clase Mesa como una entidad manejada por Hibernate
                .addAnnotatedClass(Reserva.class)  // Registra la clase Reserva como una entidad manejada por Hibernate
                .buildSessionFactory();  // Construye la SessionFactory para las sesiones
    }

    // Método para agregar una nueva reserva a la base de datos
    public void agregarReserva(String fechaReserva, int clienteId, int mesaId) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción (puede ser commit o rollback)

        try {
            transaction = session.beginTransaction();  // Inicia la transacción

            // Convertir el String recibido a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fechaReservaParsed = LocalDateTime.parse(fechaReserva, formatter);

            // Busca el cliente y la mesa en la base de datos usando los IDs proporcionados
            Cliente cliente = session.get(Cliente.class, clienteId);
            Mesa mesa = session.get(Mesa.class, mesaId);

            // Verifica que tanto el cliente como la mesa existen antes de crear la reserva
            if (cliente != null && mesa != null) {
                Reserva reserva = new Reserva(fechaReservaParsed, cliente, mesa);  // Crea una nueva reserva
                session.save(reserva);  // Guarda la reserva en la base de datos
                System.out.println("Reserva agregada: " + reserva);  // Muestra un mensaje confirmando la adición de la reserva
                transaction.commit();  // Confirma la transacción para que los cambios se persistan
            } else {
                System.out.println("Cliente o Mesa no encontrada.");  // Muestra un mensaje si el cliente o la mesa no existen
            }
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para listar todas las reservas de la base de datos
    public void listarReservas() {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Consulta todas las reservas usando HQL (Hibernate Query Language)
            List<Reserva> reservas = session.createQuery("from Reserva", Reserva.class).getResultList();
            for (Reserva reserva : reservas) {
                System.out.println(reserva);  // Muestra cada reserva en la consola
            }
            transaction.commit();  // Confirma la transacción
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para modificar una reserva existente en la base de datos
    public void modificarReserva(int reservaId, String nuevaFecha) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Obtiene la reserva desde la base de datos usando el ID
            Reserva reserva = session.get(Reserva.class, reservaId);
            if (reserva != null) {
                // Convertir el String recibido a LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime nuevaFechaParsed = LocalDateTime.parse(nuevaFecha, formatter);

                // Actualiza la fecha de la reserva
                reserva.setFechaReserva(nuevaFechaParsed);
                session.update(reserva);  // Actualiza la reserva en la base de datos
                transaction.commit();  // Confirma la transacción
                System.out.println("Reserva modificada: " + reserva);  // Muestra un mensaje confirmando la modificación
            } else {
                System.out.println("Reserva no encontrada.");  // Muestra un mensaje si la reserva no existe
            }
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para eliminar una reserva de la base de datos
    public void eliminarReserva(int reservaId) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Obtiene la reserva desde la base de datos usando el ID
            Reserva reserva = session.get(Reserva.class, reservaId);
            if (reserva != null) {
                session.delete(reserva);  // Elimina la reserva de la base de datos
                transaction.commit();  // Confirma la transacción
                System.out.println("Reserva eliminada: " + reserva);  // Muestra un mensaje confirmando la eliminación
            } else {
                System.out.println("Reserva no encontrada.");  // Muestra un mensaje si la reserva no existe
            }
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para cerrar la SessionFactory y liberar todos los recursos asociados
    public void cerrar() {
        factory.close();  // Cierra la SessionFactory
    }
}