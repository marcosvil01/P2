package view;

import controller.ClienteController;
import controller.MesaController;
import controller.ReservaController;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        // Escáner para recibir la entrada del usuario desde la consola
        Scanner scanner = new Scanner(System.in);

        // Creación de instancias de controladores para manejar Clientes, Mesas y Reservas
        ClienteController clienteController = new ClienteController();
        MesaController mesaController = new MesaController();
        ReservaController reservaController = new ReservaController();

        // Variable para mantener el bucle del menú hasta que el usuario decida salir
        boolean continuar = true;

        // Bucle del menú principal
        while (continuar) {
            // Menú principal del sistema
            System.out.println("--- Menú de Gestión de Restaurante ---");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Modificar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Agregar Mesa");
            System.out.println("6. Listar Mesas");
            System.out.println("7. Modificar Mesa");
            System.out.println("8. Eliminar Mesa");
            System.out.println("9. Agregar Reserva");
            System.out.println("10. Listar Reservas");
            System.out.println("11. Modificar Reserva");
            System.out.println("12. Eliminar Reserva");
            System.out.println("0. Salir");
            System.out.print("Escoge una opción: ");

            // Lee la opción elegida por el usuario
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea que queda en el buffer

            // Lógica de selección del menú según la opción elegida por el usuario
            switch (opcion) {
                case 1:
                    // Agregar un nuevo cliente
                    System.out.print("Introduce el nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Introduce el teléfono del cliente: ");
                    String telefonoCliente = scanner.nextLine();
                    clienteController.agregarCliente(nombreCliente, telefonoCliente);
                    break;
                case 2:
                    // Listar todos los clientes
                    clienteController.listarClientes();
                    break;
                case 3:
                    // Modificar un cliente existente
                    System.out.print("Introduce el ID del cliente a modificar: ");
                    int clienteId = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Introduce el nuevo nombre del cliente: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Introduce el nuevo teléfono del cliente: ");
                    String nuevoTelefono = scanner.nextLine();
                    clienteController.modificarCliente(clienteId, nuevoNombre, nuevoTelefono);
                    break;
                case 4:
                    // Eliminar un cliente existente
                    System.out.print("Introduce el ID del cliente a eliminar: ");
                    int clienteIdEliminar = scanner.nextInt();
                    clienteController.eliminarCliente(clienteIdEliminar);
                    break;
                case 5:
                    // Agregar una nueva mesa
                    System.out.print("Introduce el número de la mesa: ");
                    int numeroMesa = scanner.nextInt();
                    System.out.print("Introduce la capacidad de la mesa: ");
                    int capacidadMesa = scanner.nextInt();
                    mesaController.agregarMesa(numeroMesa, capacidadMesa);
                    break;
                case 6:
                    // Listar todas las mesas
                    mesaController.listarMesas();
                    break;
                case 7:
                    // Modificar una mesa existente
                    System.out.print("Introduce el ID de la mesa a modificar: ");
                    int mesaId = scanner.nextInt();
                    System.out.print("Introduce el nuevo número de la mesa: ");
                    int nuevoNumero = scanner.nextInt();
                    System.out.print("Introduce la nueva capacidad de la mesa: ");
                    int nuevaCapacidad = scanner.nextInt();
                    mesaController.modificarMesa(mesaId, nuevoNumero, nuevaCapacidad);
                    break;
                case 8:
                    // Eliminar una mesa existente
                    System.out.print("Introduce el ID de la mesa a eliminar: ");
                    int mesaIdEliminar = scanner.nextInt();
                    mesaController.eliminarMesa(mesaIdEliminar);
                    break;
                case 9:
                    // Agregar una nueva reserva
                    System.out.print("Introduce el ID del cliente: ");
                    int clienteIdReserva = scanner.nextInt();
                    System.out.print("Introduce el ID de la mesa: ");
                    int mesaIdReserva = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Introduce la fecha de la reserva (ej. 2024-10-30 14:00:00): ");
                    String fechaReserva = scanner.nextLine();
                    reservaController.agregarReserva(fechaReserva, clienteIdReserva, mesaIdReserva);
                    break;
                case 10:
                    // Listar todas las reservas
                    reservaController.listarReservas();
                    break;
                case 11:
                    // Modificar una reserva existente
                    System.out.print("Introduce el ID de la reserva a modificar: ");
                    int reservaId = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Introduce la nueva fecha de la reserva (ej. 2024-10-30 14:00:00): ");
                    String nuevaFecha = scanner.nextLine();
                    reservaController.modificarReserva(reservaId, nuevaFecha);
                    break;
                case 12:
                    // Eliminar una reserva existente
                    System.out.print("Introduce el ID de la reserva a eliminar: ");
                    int reservaIdEliminar = scanner.nextInt();
                    reservaController.eliminarReserva(reservaIdEliminar);
                    break;
                case 0:
                    // Salir del sistema
                    continuar = false;
                    clienteController.cerrar();  // Cierra el SessionFactory del controlador de clientes
                    mesaController.cerrar();  // Cierra el SessionFactory del controlador de mesas
                    reservaController.cerrar();  // Cierra el SessionFactory del controlador de reservas
                    System.out.println("Saliendo...");
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }

        // Cerrar el escáner al terminar el programa
        scanner.close();
    }
}
