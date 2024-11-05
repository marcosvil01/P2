import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Functions {

    // Mètode per connectar a la base de dades
    private Connection conectar() {
        // Connexió a la base de dades MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega el driver de MySQL
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/botiga", "root", ""); // Retorna la connexió a la base de dades
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema amb la connexió
            return null;
        }
    }

    // Mètode per inserir una nova categoria a la base de dades
    public void insertarCategoria(String nom) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "INSERT INTO categories (nom) VALUES (?)"; // Consulta SQL per inserir una categoria
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom); // Assigna el nom de la categoria
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Categoria inserida correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per inserir un nou producte a la base de dades
    public void insertarProducto(String nom, double preu, int idCategoria) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "INSERT INTO productes (nom, preu, id_categoria) VALUES (?, ?, ?)"; // Consulta SQL per inserir un producte
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom); // Assigna el nom del producte
            pstmt.setDouble(2, preu); // Assigna el preu del producte
            pstmt.setInt(3, idCategoria); // Assigna l'ID de la categoria del producte
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Producte inserit correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per inserir un nou client a la base de dades
    public void insertarCliente(String nom, String email) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "INSERT INTO clients (nom, email) VALUES (?, ?)"; // Consulta SQL per inserir un client
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom); // Assigna el nom del client
            pstmt.setString(2, email); // Assigna el correu electrònic del client
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Client inserit correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per inserir una nova comanda a la base de dades
    public void insertarComanda(int idCliente, int idProducto) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sqlComanda = "INSERT INTO comandes (id_client) VALUES (?)"; // Consulta SQL per inserir una comanda
            PreparedStatement pstmtComanda = con.prepareStatement(sqlComanda, Statement.RETURN_GENERATED_KEYS);
            pstmtComanda.setInt(1, idCliente); // Assigna l'ID del client
            pstmtComanda.executeUpdate(); // Executa la consulta per inserir la comanda

            ResultSet generatedKeys = pstmtComanda.getGeneratedKeys(); // Obté l'ID de la comanda generat
            int idComanda = -1;
            if (generatedKeys.next()) {
                idComanda = generatedKeys.getInt(1); // Assigna l'ID de la comanda
            }

            String sqlDetalls = "INSERT INTO detalls_comanda (id_comanda, id_producte) VALUES (?, ?)"; // Consulta SQL per inserir els detalls de la comanda
            PreparedStatement pstmtDetalls = con.prepareStatement(sqlDetalls);
            pstmtDetalls.setInt(1, idComanda); // Assigna l'ID de la comanda
            pstmtDetalls.setInt(2, idProducto); // Assigna l'ID del producte
            pstmtDetalls.executeUpdate(); // Executa la consulta per inserir els detalls de la comanda
            System.out.println("Comanda inserida correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per llistar totes les categories de la base de dades
    public void listarCategorias() {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "SELECT * FROM categories"; // Consulta SQL per obtenir totes les categories
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql); // Executa la consulta
            System.out.println("Categories disponibles:");
            while (rs.next()) { // Itera sobre els resultats
                System.out.println("ID: " + rs.getInt("id") + " - Nom: " + rs.getString("nom")); // Mostra l'ID i el nom de cada categoria
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per consultar productes per categoria
    public void consultarProductosPorCategoria(String idCategoria) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "SELECT nom, preu FROM productes WHERE id_categoria = ?"; // Consulta SQL per obtenir els productes d'una categoria específica
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(idCategoria)); // Assigna l'ID de la categoria
            ResultSet rs = pstmt.executeQuery(); // Executa la consulta
            System.out.println("Productes de la categoria seleccionada:");
            while (rs.next()) { // Itera sobre els resultats
                System.out.println(rs.getString("nom") + " - " + rs.getDouble("preu") + "€"); // Mostra el nom i el preu de cada producte
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per eliminar un producte de la base de dades
    public void eliminarProducto(String nom) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "DELETE FROM productes WHERE nom = ?"; // Consulta SQL per eliminar un producte
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom); // Assigna el nom del producte
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Producte eliminat correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per actualitzar el preu d'un producte
    public void actualizarPrecioProducto(String nom, double preu) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "UPDATE productes SET preu = ? WHERE nom = ?"; // Consulta SQL per actualitzar el preu d'un producte
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, preu); // Assigna el nou preu
            pstmt.setString(2, nom); // Assigna el nom del producte
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Preu actualitzat correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per consultar els clients amb una coincidència en el nom o correu electrònic
    public void consultarUsuariosPorCoincidencia(String coincidencia) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "SELECT * FROM clients WHERE nom LIKE ? OR email LIKE ?"; // Consulta SQL per obtenir els clients amb una coincidència
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + coincidencia + "%"); // Assigna la coincidència per al nom
            pstmt.setString(2, "%" + coincidencia + "%"); // Assigna la coincidència per al correu electrònic
            ResultSet rs = pstmt.executeQuery(); // Executa la consulta
            while (rs.next()) { // Itera sobre els resultats
                System.out.println(rs.getString("nom") + " - " + rs.getString("email")); // Mostra el nom i el correu electrònic de cada client
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per llistar tots els clients de la base de dades
    public void listarTodosUsuarios() {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "SELECT * FROM clients"; // Consulta SQL per obtenir tots els clients
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql); // Executa la consulta
            while (rs.next()) { // Itera sobre els resultats
                System.out.println(rs.getString("nom") + " - " + rs.getString("email")); // Mostra el nom i el correu electrònic de cada client
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per eliminar una comanda de la base de dades
    public void eliminarComanda(int idComanda) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "DELETE FROM comandes WHERE id = ?"; // Consulta SQL per eliminar una comanda
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idComanda); // Assigna l'ID de la comanda
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Comanda eliminada correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per consultar les comandes d'un usuari específic
    public void consultarComandesPorUsuario(int idUsuario) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "SELECT id, data FROM comandes WHERE id_client = ?"; // Consulta SQL per obtenir les comandes d'un usuari
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idUsuario); // Assigna l'ID de l'usuari
            ResultSet rs = pstmt.executeQuery(); // Executa la consulta
            System.out.println("Comandes de l'usuari " + idUsuario + ":");
            while (rs.next()) { // Itera sobre els resultats
                System.out.println("ID: " + rs.getInt("id") + " - Data: " + rs.getTimestamp("data")); // Mostra l'ID i la data de cada comanda
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }

    // Mètode per modificar el correu electrònic d'un client
    public void modificarCorreoCliente(int id, String nuevoEmail) {
        try (Connection con = conectar()) { // Obre la connexió a la base de dades
            String sql = "UPDATE clients SET email = ? WHERE id = ?"; // Consulta SQL per actualitzar el correu electrònic d'un client
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nuevoEmail); // Assigna el nou correu electrònic
            pstmt.setInt(2, id); // Assigna l'ID del client
            pstmt.executeUpdate(); // Executa la consulta
            System.out.println("Correu del client actualitzat correctament."); // Mostra un missatge de confirmació
        } catch (Exception e) {
            e.printStackTrace(); // Mostra l'error si hi ha algun problema
        }
    }
}
