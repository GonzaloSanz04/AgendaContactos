package com.agenda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExportManager {
    public static void exportarCSV(){
        try(Connection con = DBConnection.getConnection(); PrintWriter pw = new PrintWriter("contactos.csv")){


            String sql = "SELECT * FROM contacto";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            pw.println("id,nombre,telefono,email"); // cabecera

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                pw.println(id + "," + nombre + "," + telefono + "," + email);
            }

            System.out.println("✅ Exportación a CSV completada: contactos.csv");


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void exportarJSON(){
        try (Connection con = DBConnection.getConnection()){
            String sql = "SELECT * FROM contacto";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<Contacto> lista = new ArrayList<>();
            while (rs.next()){
                lista.add(new Contacto(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")));
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter fw = new FileWriter("contactos.json")) {
                gson.toJson(lista, fw);
            }

            System.out.println("✅ Exportación a JSON completada: contactos.json");


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
