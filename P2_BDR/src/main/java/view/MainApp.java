package view;

import controller.UsuariController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        // Instanciem el controlador per gestionar les operacions amb els usuaris i comandes
        UsuariController controller = new UsuariController();
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false; // Variable per controlar la sortida del bucle principal

        // Bucle principal del menú de gestió
        while (!sortir) {
            try {
                // Mostrem el menú d'opcions
                System.out.println("\n--- Menú de Gestió ---");
                System.out.println("1. Inserir un nou usuari");
                System.out.println("2. Consultar les comandes d'un usuari");
                System.out.println("3. Modificar el correu d'un usuari");
                System.out.println("4. Eliminar una comanda");
                System.out.println("5. Llistar tots els usuaris");
                System.out.println("6. Afegir una nova comanda");
                System.out.println("0. Sortir");
                System.out.print("Escull una opció: ");

                // Capturar l'opció seleccionada per l'usuari
                int opcio = scanner.nextInt();
                scanner.nextLine(); // Netejar el buffer després de llegir l'enter

                // Processar l'opció seleccionada
                switch (opcio) {
                    case 1:
                        // Inserir un nou usuari
                        System.out.print("Introdueix el nom de l'usuari: ");
                        String nom = scanner.nextLine();
                        System.out.print("Introdueix el correu de l'usuari: ");
                        String correu = scanner.nextLine();
                        controller.afegirUsuari(nom, correu);
                        break;
                    case 2:
                        // Consultar les comandes d'un usuari
                        System.out.print("Introdueix l'ID de l'usuari: ");
                        int idUsuariConsulta = scanner.nextInt();
                        controller.consultarComandes(idUsuariConsulta);
                        break;
                    case 3:
                        // Modificar el correu d'un usuari
                        System.out.print("Introdueix l'ID de l'usuari: ");
                        int idUsuariModifica = scanner.nextInt();
                        scanner.nextLine(); // Netejar el buffer
                        System.out.print("Introdueix el nou correu: ");
                        String nouCorreu = scanner.nextLine();
                        controller.modificarCorreu(idUsuariModifica, nouCorreu);
                        break;
                    case 4:
                        // Eliminar una comanda
                        System.out.print("Introdueix l'ID de la comanda a eliminar: ");
                        int idComandaEliminar = scanner.nextInt();
                        controller.eliminarComanda(idComandaEliminar);
                        break;
                    case 5:
                        // Llistar tots els usuaris
                        controller.listarUsuaris();
                        break;
                    case 6:
                        // Afegir una nova comanda per a un usuari
                        System.out.print("Introdueix l'ID de l'usuari: ");
                        int idUsuariComanda = scanner.nextInt();
                        scanner.nextLine(); // Netejar el buffer
                        System.out.print("Introdueix el producte: ");
                        String producte = scanner.nextLine();
                        System.out.print("Introdueix el preu del producte: ");
                        double preu = scanner.nextDouble();
                        scanner.nextLine(); // Netejar el buffer
                        System.out.print("Introdueix els detalls de la comanda: ");
                        String detall = scanner.nextLine();
                        controller.afegirComanda(idUsuariComanda, producte, preu, detall);
                        break;
                    case 0:
                        // Sortir del programa
                        sortir = true;
                        controller.tancar();
                        System.out.println("Sortint del programa...");
                        break;
                    default:
                        // Opció no vàlida
                        System.out.println("Opció no vàlida. Torna a intentar-ho.");
                }
            } catch (InputMismatchException e) {
                // Gestionar l'excepció si l'usuari introdueix un tipus d'entrada incorrecte
                System.out.println("Entrada no vàlida. Torna a intentar-ho.");
                scanner.nextLine(); // Netejar el buffer per evitar bucle infinit
            }
        }

        scanner.close(); // Tancar l'escàner per alliberar recursos
    }
}
