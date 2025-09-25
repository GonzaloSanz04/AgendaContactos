package com.agenda;

import com.agenda.ExportManager;
import java.sql.Connection;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n===== AGENDA DE CONTACTOS =====");
            System.out.println("1. Insertar contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Editar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Exportar contactos (CSV)");
            System.out.println("6. Exportar contactos (JSON)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0 -> {
                    System.out.println("Hasta luego!");
                    return;
                }
                case 1 -> insertarContacto(sc);
                case 2 -> listarContactos();
                case 3 -> editarContacto(sc);
                case 4 -> eliminarContacto(sc);
                case 5 -> ExportManager.exportarCSV();
                case 6 -> ExportManager.exportarJSON();
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void insertarContacto(Scanner sc) {
        try (Connection con = DBConnection.getConnection()){
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();

            String sql = "INSERT INTO contacto (nombre, telefono, email) VALUES (?, ?, ?)";
            var ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, email);

            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ classes.java.com.agenda.Contacto agregado correctamente.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void listarContactos() {
        try (Connection con = DBConnection.getConnection()){
            String sql = "SELECT * FROM contacto";

            var st = con.createStatement();
            var rs = st.executeQuery(sql);

            System.out.println("\n--- Lista de contactos ---");
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                System.out.printf("[%d] %s | %s | %s%n", id, nombre, telefono, email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void editarContacto(Scanner sc) {
        try (Connection con = DBConnection.getConnection()){
            System.out.print("Indique id del contacto que desee editar: ");
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Nuevo nombre: "); String nombre = sc.nextLine();
            System.out.print("Nuevo telefono: "); String telefono = sc.nextLine();
            System.out.print("Nuevo email: "); String email = sc.nextLine();

            String sql = "UPDATE contacto SET nombre = ?, telefono = ?, email = ? WHERE id = ?";
            var ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, email);
            ps.setInt(4, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ classes.java.com.agenda.Contacto actualizado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró un contacto con ese id.");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void eliminarContacto(Scanner sc) {
        try (Connection con = DBConnection.getConnection()){
            System.out.print("Indique id del contacto que desee eliminar: ");
            int id = sc.nextInt(); sc.nextLine();

            String sql = "DELETE FROM contacto WHERE id = ?";
            var ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ classes.java.com.agenda.Contacto eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró un contacto con ese id.");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
