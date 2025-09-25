import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("✅ Conexión establecida con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
