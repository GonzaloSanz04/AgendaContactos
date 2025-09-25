import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_contactos";
    private static final String USER = "root";
    private static final String PASSWORD = "nocilla";

    // Método estático que devuelve la conexión
    public static Connection getConnection() throws SQLException {
        try {
            // Cargar el driver (opcional en Java 8+, pero recomendable)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver MySQL JDBC", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
