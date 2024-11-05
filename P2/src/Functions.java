import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Functions {

    private Connection conectar() {
        // Conexión a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/botiga", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertarCategoria(String nom) {
        try (Connection con = conectar()) {
            String sql = "INSERT INTO categories (nom) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
            System.out.println("Categoría insertada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarProducto(String nom, double preu, int idCategoria) {
        try (Connection con = conectar()) {
            String sql = "INSERT INTO productes (nom, preu, id_categoria) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setDouble(2, preu);
            pstmt.setInt(3, idCategoria);
            pstmt.executeUpdate();
            System.out.println("Producto insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarCliente(String nom, String email) {
        try (Connection con = conectar()) {
            String sql = "INSERT INTO clients (nom, email) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Cliente insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarComanda(int idCliente, int idProducto) {
        try (Connection con = conectar()) {
            String sqlComanda = "INSERT INTO comandes (id_client) VALUES (?)";
            PreparedStatement pstmtComanda = con.prepareStatement(sqlComanda, Statement.RETURN_GENERATED_KEYS);
            pstmtComanda.setInt(1, idCliente);
            pstmtComanda.executeUpdate();

            ResultSet generatedKeys = pstmtComanda.getGeneratedKeys();
            int idComanda = -1;
            if (generatedKeys.next()) {
                idComanda = generatedKeys.getInt(1);
            }

            String sqlDetalls = "INSERT INTO detalls_comanda (id_comanda, id_producte) VALUES (?, ?)";
            PreparedStatement pstmtDetalls = con.prepareStatement(sqlDetalls);
            pstmtDetalls.setInt(1, idComanda);
            pstmtDetalls.setInt(2, idProducto);
            pstmtDetalls.executeUpdate();
            System.out.println("Comanda insertada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarCategorias() {
        try (Connection con = conectar()) {
            String sql = "SELECT * FROM categories";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Categorías disponibles:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - Nombre: " + rs.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarProductosPorCategoria(String idCategoria) {
        try (Connection con = conectar()) {
            String sql = "SELECT nom, preu FROM productes WHERE id_categoria = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(idCategoria));  // Using category ID now
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Productos de la categoría seleccionada:");
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " - " + rs.getDouble("preu") + "€");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(String nom) {
        try (Connection con = conectar()) {
            String sql = "DELETE FROM productes WHERE nom = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarPrecioProducto(String nom, double preu) {
        try (Connection con = conectar()) {
            String sql = "UPDATE productes SET preu = ? WHERE nom = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, preu);
            pstmt.setString(2, nom);
            pstmt.executeUpdate();
            System.out.println("Precio actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarUsuariosPorCoincidencia(String coincidencia) {
        try (Connection con = conectar()) {
            String sql = "SELECT * FROM clients WHERE nom LIKE ? OR email LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + coincidencia + "%");
            pstmt.setString(2, "%" + coincidencia + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " - " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarTodosUsuarios() {
        try (Connection con = conectar()) {
            String sql = "SELECT * FROM clients";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " - " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarComanda(int idComanda) {
        try (Connection con = conectar()) {
            String sql = "DELETE FROM comandes WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idComanda);
            pstmt.executeUpdate();
            System.out.println("Comanda eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarComandesPorUsuario(int idUsuario) {
        try (Connection con = conectar()) {
            String sql = "SELECT id, data FROM comandes WHERE id_client = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Comandes del usuario " + idUsuario + ":");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - Fecha: " + rs.getTimestamp("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarCorreoCliente(int id, String nuevoEmail) {
        try (Connection con = conectar()) {
            String sql = "UPDATE clients SET email = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nuevoEmail);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Correo del cliente actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
