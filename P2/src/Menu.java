import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Functions Funcs = new Functions();

        int option;

        do {
            System.out.println("===========================================");
            System.out.println("|           GESTIÓ DE LA BOTIGA          |");
            System.out.println("===========================================");
            System.out.println("| 1. Gestió de Categories                |");
            System.out.println("| 2. Gestió de Productes                 |");
            System.out.println("| 3. Gestió de Clients                   |");
            System.out.println("| 4. Gestió de Comandes                  |");
            System.out.println("| 0. Sortir                               |");
            System.out.println("===========================================");
            System.out.print("Tria una opció: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salt de línia

            switch (option) {
                case 1:
                    gestionarCategories(scanner, Funcs);
                    break;
                case 2:
                    gestionarProductes(scanner, Funcs);
                    break;
                case 3:
                    gestionarClients(scanner, Funcs);
                    break;
                case 4:
                    gestionarComandes(scanner, Funcs);
                    break;
                case 0:
                    System.out.println("Sortint del programa... Adéu!");
                    break;
                default:
                    System.out.println("Opcio no vàlida. Si us plau, tria una opció entre 0 i 4.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }

    // Mètode per gestionar categories
    private static void gestionarCategories(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓ DE CATEGORIES          |");
            System.out.println("===========================================");
            System.out.println("| 1. Inserir nova categoria               |");
            System.out.println("| 0. Tornar al menú principal            |");
            System.out.println("===========================================");
            System.out.print("Tria una opció: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salt de línia

            switch (option) {
                case 1:
                    System.out.print("Introdueix el nom de la nova categoria: ");
                    String categoria = scanner.nextLine();
                    Funcs.insertarCategoria(categoria);
                    break;
                case 0:
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opcio no vàlida. Si us plau, tria una opció entre 0 i 1.");
                    break;
            }
        } while (option != 0);
    }

    // Mètode per gestionar productes
    private static void gestionarProductes(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓ DE PRODUCTES           |");
            System.out.println("===========================================");
            System.out.println("| 1. Inserir nou producte                 |");
            System.out.println("| 2. Actualitzar preu d'un producte       |");
            System.out.println("| 3. Eliminar un producte                 |");
            System.out.println("| 4. Llistar productes per categoria      |");
            System.out.println("| 0. Tornar al menú principal            |");
            System.out.println("===========================================");
            System.out.print("Tria una opció: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir salt de línia
            switch (option) {
                case 1:
                    System.out.print("Introdueix el nom del producte: ");
                    String producte = scanner.nextLine();
                    System.out.print("Introdueix el preu del producte: ");
                    double preu = scanner.nextDouble();
                    System.out.print("Introdueix l'ID de la categoria associada: ");
                    int idCategoria = scanner.nextInt();
                    Funcs.insertarProducto(producte, preu, idCategoria);
                    break;
                case 2:
                    System.out.print("Introdueix el nom del producte a actualitzar: ");
                    String producteActualitzar = scanner.nextLine();
                    System.out.print("Introdueix el nou preu: ");
                    double nouPreu = scanner.nextDouble();
                    Funcs.actualizarPrecioProducto(producteActualitzar, nouPreu);
                    break;
                case 3:
                    System.out.print("Introdueix el nom del producte a eliminar: ");
                    String producteEliminar = scanner.nextLine();
                    Funcs.eliminarProducto(producteEliminar);
                    break;
                case 4:
                    Funcs.listarCategorias();
                    System.out.print("Introdueix l'ID de la categoria per veure els productes: ");
                    int idCat = scanner.nextInt();
                    scanner.nextLine();  // Consumir salt de línia
                    Funcs.consultarProductosPorCategoria(String.valueOf(idCat));
                    break;
                case 0:
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opcio no vàlida. Si us plau, tria una opció entre 0 i 4.");
                    break;
            }
        } while (option != 0);
    }

    // Mètode per gestionar clients
    private static void gestionarClients(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓ DE CLIENTS             |");
            System.out.println("===========================================");
            System.out.println("| 1. Inserir nou client                   |");
            System.out.println("| 2. Modificar correu d'un client         |");
            System.out.println("| 3. Consultar usuaris per nom/correu     |");
            System.out.println("| 4. Llistar tots els clients             |");
            System.out.println("| 0. Tornar al menú principal            |");
            System.out.println("===========================================");
            System.out.print("Tria una opció: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salt de línia
            switch (option) {
                case 1:
                    System.out.print("Introdueix el nom del client: ");
                    String client = scanner.nextLine();
                    System.out.print("Introdueix el correu electrònic del client: ");
                    String email = scanner.nextLine();
                    Funcs.insertarCliente(client, email);
                    break;
                case 2:
                    System.out.print("Introdueix l'ID del client: ");
                    int idModificarClient = scanner.nextInt();
                    scanner.nextLine();  // Consumir salt de línia
                    System.out.print("Introdueix el nou correu electrònic: ");
                    String nouCorreu = scanner.nextLine();
                    Funcs.modificarCorreoCliente(idModificarClient, nouCorreu);
                    break;
                case 3:
                    System.out.print("Introdueix el nom o correu de l'usuari a consultar: ");
                    String coincidencia = scanner.nextLine();
                    Funcs.consultarUsuariosPorCoincidencia(coincidencia);
                    break;
                case 4:
                    Funcs.listarTodosUsuarios();
                    break;
                case 0:
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opcio no vàlida. Si us plau, tria una opció entre 0 i 4.");
                    break;
            }
        } while (option != 0);
    }

    // Mètode per gestionar comandes
    private static void gestionarComandes(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓ DE COMANDES            |");
            System.out.println("===========================================");
            System.out.println("| 1. Inserir nova comanda                 |");
            System.out.println("| 2. Consultar comandes per usuari        |");
            System.out.println("| 3. Eliminar una comanda                 |");
            System.out.println("| 0. Tornar al menú principal            |");
            System.out.println("===========================================");
            System.out.print("Tria una opció: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Introdueix l'ID del client: ");
                    int idClient = scanner.nextInt();
                    System.out.print("Introdueix l'ID del producte: ");
                    int idProducte = scanner.nextInt();
                    Funcs.insertarComanda(idClient, idProducte);
                    break;
                case 2:
                    System.out.print("Introdueix l'ID de l'usuari per consultar les comandes: ");
                    int idUsuari = scanner.nextInt();
                    Funcs.consultarComandesPorUsuario(idUsuari);
                    break;
                case 3:
                    System.out.print("Introdueix l'ID de la comanda a eliminar: ");
                    int idComanda = scanner.nextInt();
                    Funcs.eliminarComanda(idComanda);
                    break;
                case 0:
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opcio no vàlida. Si us plau, tria una opció entre 0 i 3.");
                    break;
            }
        } while (option != 0);
    }
}
