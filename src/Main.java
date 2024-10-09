
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establecer la conexión
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/botiga", "root", "");
            // 3. Crear un objeto Statement
            Statement stmt = con.createStatement();

            // 4. Ejecutar una consulta SQL para seleccionar productos
            ResultSet rs = stmt.executeQuery("SELECT * FROM productes");

            // 5. Procesar los resultados
            while (rs.next()) {
                System.out.println(rs.getString("nom") + " - " + rs.getDouble("preu") + " €");
            }

            // 6. Cerrar la conexión
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
