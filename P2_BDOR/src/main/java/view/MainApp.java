package view;

import controller.LlibreController;
import controller.VehicleController;
import controller.ClientController;
import controller.PersonaController;
import controller.DispositiuController;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LlibreController llibreController = new LlibreController();
        VehicleController vehicleController = new VehicleController();
        ClientController clientController = new ClientController();
        PersonaController personaController = new PersonaController();
        DispositiuController dispositiuController = new DispositiuController();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Gestió Principal ---");
            System.out.println("1. Gestionar Llibres");
            System.out.println("2. Gestionar Vehicles");
            System.out.println("3. Gestionar Clients i Telèfons");
            System.out.println("4. Gestionar Persones i Professors");
            System.out.println("5. Gestionar Dispositius i LogTable");
            System.out.println("0. Sortir");
            System.out.print("Escoge una opció: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    gestionarLlibres(scanner, llibreController);
                    break;
                case 2:
                    gestionarVehicles(scanner, vehicleController);
                    break;
                case 3:
                    gestionarClients(scanner, clientController);
                    break;
                case 4:
                    gestionarPersones(scanner, personaController);
                    break;
                case 5:
                    gestionarDispositius(scanner, dispositiuController);
                    break;
                case 0:
                    continuar = false;
                    llibreController.cerrar();
                    vehicleController.cerrar();
                    clientController.cerrar();
                    personaController.cerrar();
                    dispositiuController.cerrar();
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }

        scanner.close();
    }

    // Métodos para gestionar libros
    private static void gestionarLlibres(Scanner scanner, LlibreController llibreController) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Gestió de Llibres ---");
            System.out.println("1. Agregar Llibre");
            System.out.println("2. Listar Llibres amb ISBN que comenci per '978'");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Escoge una opció: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el títol del llibre: ");
                    String titol = scanner.nextLine();
                    System.out.print("Introduce l'autor del llibre: ");
                    String autor = scanner.nextLine();
                    System.out.print("Introduce l'ISBN del llibre: ");
                    String isbn = scanner.nextLine();
                    llibreController.agregarLlibre(titol, autor, isbn);
                    break;
                case 2:
                    llibreController.listarLlibresAmbIsbn("978");
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    // Métodos para gestionar vehículos
    private static void gestionarVehicles(Scanner scanner, VehicleController vehicleController) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Gestió de Vehicles ---");
            System.out.println("1. Agregar Vehicle");
            System.out.println("2. Listar Vehicles");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Escoge una opció: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la marca del vehicle: ");
                    String marca = scanner.nextLine();
                    System.out.print("Introduce el model del vehicle: ");
                    String model = scanner.nextLine();
                    System.out.print("Introduce el nombre de portes (si és un cotxe): ");
                    int nombrePortes = scanner.nextInt();
                    vehicleController.agregarVehicle(marca, model, nombrePortes);
                    break;
                case 2:
                    vehicleController.listarVehicles();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    // Métodos para gestionar clientes y teléfonos
    private static void gestionarClients(Scanner scanner, ClientController clientController) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Gestió de Clients ---");
            System.out.println("1. Agregar Client amb Telèfons");
            System.out.println("2. Listar Clients");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Escoge una opció: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nom del client: ");
                    String nom = scanner.nextLine();
                    System.out.print("Introduce fins a 5 telèfons (separats per espai): ");
                    String telefons = scanner.nextLine();
                    clientController.agregarClient(nom, telefons);
                    break;
                case 2:
                    clientController.listarClients();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    // Métodos para gestionar personas y profesores
    private static void gestionarPersones(Scanner scanner, PersonaController personaController) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Gestió de Persones i Professors ---");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Professor");
            System.out.println("3. Listar Persones");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Escoge una opció: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nom de la persona: ");
                    String nom = scanner.nextLine();
                    System.out.print("Introduce el cognom de la persona: ");
                    String cognom = scanner.nextLine();
                    personaController.agregarPersona(nom, cognom);
                    break;
                case 2:
                    System.out.print("Introduce el nom del professor: ");
                    String nomProfessor = scanner.nextLine();
                    System.out.print("Introduce el cognom del professor: ");
                    String cognomProfessor = scanner.nextLine();
                    System.out.print("Introduce el departament: ");
                    String departament = scanner.nextLine();
                    personaController.agregarProfessor(nomProfessor, cognomProfessor, departament);
                    break;
                case 3:
                    personaController.listarPersones();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    // Métodos para gestionar dispositivos y logs
    private static void gestionarDispositius(Scanner scanner, DispositiuController dispositiuController) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Gestió de Dispositius ---");
            System.out.println("1. Agregar Dispositiu");
            System.out.println("2. Listar Dispositius");
            System.out.println("3. Mostrar Log");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Escoge una opció: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Introduce l'estat del dispositiu (1 per encès, 0 per apagat): ");
                    int estat = scanner.nextInt();
                    dispositiuController.agregarDispositiu(estat);
                    break;
                case 2:
                    dispositiuController.listarDispositius();
                    break;
                case 3:
                    dispositiuController.mostrarLog();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }
}
