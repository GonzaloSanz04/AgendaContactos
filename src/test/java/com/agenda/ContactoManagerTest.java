package com.agenda;

import org.junit.jupiter.api.Test;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContactoManagerTest {
    @Test
    public void testInsertarContacto(){
        try (Connection con = DBConnection.getConnection()) {
            String nombre = "TestUser";
            String telefono = "123456789";
            String email = "test@example.com";

            String sqlInsert = "INSERT INTO contacto (nombre, telefono, email) VALUES ('"
                    + nombre + "','" + telefono + "','" + email + "')";
            Statement st = con.createStatement();
            int filas = st.executeUpdate(sqlInsert);

            assertEquals(1, filas, "Se debería insertar 1 fila");

            // Limpiar la base después de la prueba
            st.executeUpdate("DELETE FROM contacto WHERE nombre = '" + nombre + "'");

        } catch (Exception e) {
            fail("Error al insertar contacto: " + e.getMessage());
        }
    }
}
