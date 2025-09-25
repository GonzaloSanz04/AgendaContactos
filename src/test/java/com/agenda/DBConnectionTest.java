package com.agenda;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DBConnectionTest {

    @Test
    public void testConexion() {
        try (Connection con = DBConnection.getConnection()) {
            assertNotNull(con, "La conexión no debe ser null");
            assertFalse(con.isClosed(), "La conexión no debe estar cerrada");
        } catch (Exception e) {
            fail("No se pudo establecer la conexión: " + e.getMessage());
        }
    }
}
