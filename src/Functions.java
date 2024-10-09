
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

    public void consultarProductosPorCategoria(String categoria) {
        try (Connection con = conectar()) {
            String sql = "SELECT productes.nom, productes.preu FROM productes " +
                    "JOIN categories ON productes.id_categoria = categories.id " +
                    "WHERE categories.nom = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, categoria);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Productos de la categoría '" + categoria + "':");
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " - " + rs.getDouble("preu"));
            }
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
            System.out.println("Usuarios encontrados:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nom") + ", Email: " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarComandesPorUsuario(int idUsuario) {
        try (Connection con = conectar()) {
            String sql = "SELECT comandes.id, productes.nom, productes.preu " +
                    "FROM comandes JOIN detalls_comanda ON comandes.id = detalls_comanda.id_comanda " +
                    "JOIN productes ON detalls_comanda.id_producte = productes.id " +
                    "WHERE comandes.id_client = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Comandes del usuario con ID " + idUsuario + ":");
            while (rs.next()) {
                System.out.println("Comanda ID: " + rs.getInt("id") + ", Producto: " + rs.getString("nom") + ", Precio: " + rs.getDouble("preu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarCorreoCliente(int idCliente, String nuevoCorreo) {
        try (Connection con = conectar()) {
            String sql = "UPDATE clients SET email = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nuevoCorreo);
            pstmt.setInt(2, idCliente);
            pstmt.executeUpdate();
            System.out.println("Correo actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarPrecioProducto(String nom, double nuevoPrecio) {
        try (Connection con = conectar()) {
            String sql = "UPDATE productes SET preu = ? WHERE nom = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, nuevoPrecio);
            pstmt.setString(2, nom);
            pstmt.executeUpdate();
            System.out.println("Precio actualizado correctamente.");
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

    public void eliminarComanda(int idComanda) {
        try (Connection con = conectar()) {
            String sqlDetalles = "DELETE FROM detalls_comanda WHERE id_comanda = ?";
            PreparedStatement pstmtDetalles = con.prepareStatement(sqlDetalles);
            pstmtDetalles.setInt(1, idComanda);
            pstmtDetalles.executeUpdate();

            String sqlComanda = "DELETE FROM comandes WHERE id = ?";
            PreparedStatement pstmtComanda = con.prepareStatement(sqlComanda);
            pstmtComanda.setInt(1, idComanda);
            pstmtComanda.executeUpdate();
            System.out.println("Comanda eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarTodosUsuarios() {
        try (Connection con = conectar()) {
            String sql = "SELECT * FROM clients";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Lista de todos los usuarios:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nom") + ", Email: " + rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
