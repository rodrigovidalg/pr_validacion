/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class Usuario {
    private Conexion cn;
    private ResultSet rs;

    public Empleado validar(String usuario, String password) {
        Empleado empleado = new Empleado();

        try {
            cn = new Conexion();
            PreparedStatement parametro;
            cn.abrir_conexion();
            String query = "SELECT * FROM usuario WHERE usuario=? AND password=?";
            parametro = cn.conexionDB.prepareStatement(query);
            parametro.setString(1, usuario);
            parametro.setString(2, password);
            rs = parametro.executeQuery();

            if (rs.next()) {
                empleado.setNombres(rs.getString("usuario"));
                empleado.setDpi(rs.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cn != null) cn.cerrar_conexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return empleado;
    }
}