import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Functions Funcs = new Functions();

        int option;

        do {
            System.out.println("===========================================");
            System.out.println("|           GESTIÓN DE LA BOTIGA          |");
            System.out.println("===========================================");
            System.out.println("| 1. Gestión de Categorías                |");
            System.out.println("| 2. Gestión de Productos                 |");
            System.out.println("| 3. Gestión de Clientes                  |");
            System.out.println("| 4. Gestión de Comandes                  |");
            System.out.println("| 0. Salir                                |");
            System.out.println("===========================================");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    gestionarCategorias(scanner, Funcs);
                    break;
                case 2:
                    gestionarProductos(scanner, Funcs);
                    break;
                case 3:
                    gestionarClientes(scanner, Funcs);
                    break;
                case 4:
                    gestionarComandes(scanner, Funcs);
                    break;
                case 0:
                    System.out.println("Saliendo del programa... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 0 y 4.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }

    private static void gestionarCategorias(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓN DE CATEGORÍAS          |");
            System.out.println("===========================================");
            System.out.println("| 1. Insertar nueva categoría             |");
            System.out.println("| 0. Volver al menú principal             |");
            System.out.println("===========================================");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.print("Introduce el nombre de la nueva categoría: ");
                    String categoria = scanner.nextLine();
                    Funcs.insertarCategoria(categoria);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 0 y 1.");
                    break;
            }
        } while (option != 0);
    }

    private static void gestionarProductos(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓN DE PRODUCTOS           |");
            System.out.println("===========================================");
            System.out.println("| 1. Insertar nuevo producto              |");
            System.out.println("| 2. Actualizar precio de un producto     |");
            System.out.println("| 3. Eliminar un producto                 |");
            System.out.println("| 4. Listar productos por categoría       |");
            System.out.println("| 0. Volver al menú principal             |");
            System.out.println("===========================================");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir salto de línea
            switch (option) {
                case 1:
                    System.out.print("Introduce el nombre del producto: ");
                    String producto = scanner.nextLine();
                    System.out.print("Introduce el precio del producto: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Introduce el ID de la categoría asociada: ");
                    int idCategoria = scanner.nextInt();
                    Funcs.insertarProducto(producto, precio, idCategoria);
                    break;
                case 2:
                    System.out.print("Introduce el nombre del producto a actualizar: ");
                    String productoActualizar = scanner.nextLine();
                    System.out.print("Introduce el nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    Funcs.actualizarPrecioProducto(productoActualizar, nuevoPrecio);
                    break;
                case 3:
                    System.out.print("Introduce el nombre del producto a eliminar: ");
                    String productoEliminar = scanner.nextLine();
                    Funcs.eliminarProducto(productoEliminar);
                    break;
                case 4:
                    Funcs.listarCategorias();
                    System.out.print("Introduce el ID de la categoría para ver los productos: ");
                    int idCat = scanner.nextInt();
                    scanner.nextLine();  // Consumir salto de línea
                    Funcs.consultarProductosPorCategoria(String.valueOf(idCat));
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 0 y 4.");
                    break;
            }
        } while (option != 0);
    }

    private static void gestionarClientes(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓN DE CLIENTES            |");
            System.out.println("===========================================");
            System.out.println("| 1. Insertar nuevo cliente               |");
            System.out.println("| 2. Modificar correo de un cliente       |");
            System.out.println("| 3. Consultar usuarios por nombre/email  |");
            System.out.println("| 4. Listar todos los clientes            |");
            System.out.println("| 0. Volver al menú principal             |");
            System.out.println("===========================================");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            switch (option) {
                case 1:
                    System.out.print("Introduce el nombre del cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Introduce el email del cliente: ");
                    String email = scanner.nextLine();
                    Funcs.insertarCliente(cliente, email);
                    break;
                case 2:
                    System.out.print("Introduce el ID del cliente: ");
                    int idModificarCliente = scanner.nextInt();
                    scanner.nextLine();  // Consumir salto de línea
                    System.out.print("Introduce el nuevo correo electrónico: ");
                    String nuevoCorreo = scanner.nextLine();
                    Funcs.modificarCorreoCliente(idModificarCliente, nuevoCorreo);
                    break;
                case 3:
                    System.out.print("Introduce el nombre o email del usuario a consultar: ");
                    String coincidencia = scanner.nextLine();
                    Funcs.consultarUsuariosPorCoincidencia(coincidencia);
                    break;
                case 4:
                    Funcs.listarTodosUsuarios();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 0 y 4.");
                    break;
            }
        } while (option != 0);
    }

    private static void gestionarComandes(Scanner scanner, Functions Funcs) {
        int option;
        do {
            System.out.println("===========================================");
            System.out.println("|          GESTIÓN DE COMANDES            |");
            System.out.println("===========================================");
            System.out.println("| 1. Insertar nueva comanda               |");
            System.out.println("| 2. Consultar comandes por usuario       |");
            System.out.println("| 3. Eliminar una comanda                 |");
            System.out.println("| 0. Volver al menú principal             |");
            System.out.println("===========================================");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Introduce el ID del cliente: ");
                    int idCliente = scanner.nextInt();
                    System.out.print("Introduce el ID del producto: ");
                    int idProducto = scanner.nextInt();
                    Funcs.insertarComanda(idCliente, idProducto);
                    break;
                case 2:
                    System.out.print("Introduce el ID del usuario para consultar sus comandes: ");
                    int idUsuario = scanner.nextInt();
                    Funcs.consultarComandesPorUsuario(idUsuario);
                    break;
                case 3:
                    System.out.print("Introduce el ID de la comanda a eliminar: ");
                    int idComanda = scanner.nextInt();
                    Funcs.eliminarComanda(idComanda);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 0 y 3.");
                    break;
            }
        } while (option != 0);
    }
}
