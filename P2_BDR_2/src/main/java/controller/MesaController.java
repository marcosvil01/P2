package controller;

import model.Mesa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MesaController {

    // Fábrica de sesiones de Hibernate (SessionFactory) para gestionar las conexiones a la base de datos.
    private SessionFactory factory;

    // Constructor del controlador de Mesa. Inicializa la SessionFactory usando la configuración de Hibernate.
    public MesaController() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")  // Carga la configuración de Hibernate desde el archivo 'hibernate.cfg.xml'
                .addAnnotatedClass(Mesa.class)  // Registra la clase Mesa como una entidad manejada por Hibernate
                .buildSessionFactory();  // Construye la SessionFactory para las sesiones
    }

    // Método para añadir una nueva mesa a la base de datos
    public void agregarMesa(int numero, int capacidad) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción (puede ser commit o rollback)

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            Mesa mesa = new Mesa(numero, capacidad);  // Crea un nuevo objeto Mesa
            session.save(mesa);  // Guarda la mesa en la base de datos
            transaction.commit();  // Confirma la transacción para que los cambios se persistan
            System.out.println("Mesa agregada: " + mesa);  // Muestra un mensaje confirmando la adición de la mesa
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para listar todas las mesas de la base de datos
    public void listarMesas() {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Consulta todas las mesas usando HQL (Hibernate Query Language)
            List<Mesa> mesas = session.createQuery("from Mesa", Mesa.class).getResultList();
            for (Mesa mesa : mesas) {
                System.out.println(mesa);  // Muestra cada mesa en la consola
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

    // Método para modificar los datos de una mesa existente
    public void modificarMesa(int mesaId, int nuevoNumero, int nuevaCapacidad) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Obtiene la mesa desde la base de datos usando el ID
            Mesa mesa = session.get(Mesa.class, mesaId);
            if (mesa != null) {
                // Si la mesa existe, actualiza su número y capacidad
                mesa.setNumero(nuevoNumero);
                mesa.setCapacidad(nuevaCapacidad);
                session.update(mesa);  // Actualiza los datos de la mesa en la base de datos
                transaction.commit();  // Confirma la transacción
                System.out.println("Mesa modificada: " + mesa);  // Muestra un mensaje confirmando la modificación
            } else {
                System.out.println("Mesa no encontrada.");  // Muestra un mensaje si la mesa no existe
            }
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para eliminar una mesa de la base de datos
    public void eliminarMesa(int mesaId) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Obtiene la mesa desde la base de datos usando el ID
            Mesa mesa = session.get(Mesa.class, mesaId);
            if (mesa != null) {
                session.delete(mesa);  // Elimina la mesa de la base de datos
                transaction.commit();  // Confirma la transacción
                System.out.println("Mesa eliminada: " + mesa);  // Muestra un mensaje confirmando la eliminación
            } else {
                System.out.println("Mesa no encontrada.");  // Muestra un mensaje si la mesa no existe
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