package controller;

import model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClienteController {

    // Fábrica de sesiones de Hibernate (SessionFactory) para gestionar las conexiones a la base de datos.
    private SessionFactory factory;

    // Constructor del controlador de Cliente. Inicializa la SessionFactory usando la configuración de Hibernate.
    public ClienteController() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")  // Carga la configuración de Hibernate desde el archivo 'hibernate.cfg.xml'
                .addAnnotatedClass(Cliente.class)  // Registra la clase Cliente como una entidad manejada por Hibernate
                .buildSessionFactory();  // Construye la SessionFactory para las sesiones
    }

    // Método para añadir un nuevo cliente a la base de datos
    public void agregarCliente(String nombre, String telefono) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción (puede ser commit o rollback)

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            Cliente cliente = new Cliente(nombre, telefono);  // Crea un nuevo objeto Cliente
            session.save(cliente);  // Guarda el cliente en la base de datos
            transaction.commit();  // Confirma la transacción para que los cambios se persistan
            System.out.println("Cliente agregado: " + cliente);  // Muestra un mensaje confirmando la adición del cliente
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para listar todos los clientes de la base de datos
    public void listarClientes() {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Consulta todos los clientes usando HQL (Hibernate Query Language)
            List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).getResultList();
            for (Cliente cliente : clientes) {
                System.out.println(cliente);  // Muestra cada cliente en la consola
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

    // Método para modificar los datos de un cliente existente
    public void modificarCliente(int clienteId, String nuevoNombre, String nuevoTelefono) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Obtiene el cliente desde la base de datos usando el ID
            Cliente cliente = session.get(Cliente.class, clienteId);
            if (cliente != null) {
                // Si el cliente existe, actualiza su nombre y teléfono
                cliente.setNombre(nuevoNombre);
                cliente.setTelefono(nuevoTelefono);
                session.update(cliente);  // Actualiza los datos del cliente en la base de datos
                transaction.commit();  // Confirma la transacción
                System.out.println("Cliente modificado: " + cliente);  // Muestra un mensaje confirmando la modificación
            } else {
                System.out.println("Cliente no encontrado.");  // Muestra un mensaje si el cliente no existe
            }
        } catch (Exception e) {
            // En caso de error, realiza un rollback para deshacer los cambios
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();  // Cierra la sesión para liberar recursos
        }
    }

    // Método para eliminar un cliente de la base de datos
    public void eliminarCliente(int clienteId) {
        Session session = factory.openSession();  // Abre una nueva sesión con la base de datos
        Transaction transaction = null;  // Declara la transacción

        try {
            transaction = session.beginTransaction();  // Inicia la transacción
            // Obtiene el cliente desde la base de datos usando el ID
            Cliente cliente = session.get(Cliente.class, clienteId);
            if (cliente != null) {
                session.delete(cliente);  // Elimina el cliente de la base de datos
                transaction.commit();  // Confirma la transacción
                System.out.println("Cliente eliminado: " + cliente);  // Muestra un mensaje confirmando la eliminación
            } else {
                System.out.println("Cliente no encontrado.");  // Muestra un mensaje si el cliente no existe
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
