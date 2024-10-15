/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class Empleado extends Persona {
    private int id, id_puesto;
    private String direccion, dpi, fecha_nacimiento, fecha_inicio_labores;
    private Conexion cn;
    public Empleado() {}

    public Empleado(int id, String nombres, String apellidos, String direccion, String telefono, String dpi, String genero, String fecha_nacimiento, int id_puesto, String fecha_inicio_labores, String fecha_ingreso){
        super(nombres, apellidos, telefono, genero, fecha_ingreso);
        this.id = id;
        this.id_puesto = id_puesto;
        this.direccion = direccion;
        this.dpi = dpi;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }
    
    @Override
    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO empleados(nombres, apellidos, direccion, telefono, dpi, genero, fecha_nacimiento, id_puesto, fecha_inicio_labores, fecha_ingreso) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());
            parametro.setString(6, getGenero());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9, getFecha_inicio_labores());
            parametro.setString(10, getFecha_ingreso());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
    public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel(); 
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT e.id_empleados as id,e.nombres, e.apellidos, e.direccion, e.telefono, e.dpi, e.genero, e.fecha_nacimiento, e.fecha_inicio_labores, e.fecha_ingreso,p.puesto,p.id_puesto FROM empleados as e inner join puestos as p on e.id_puesto = p.id_puesto;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado [] = {"nombres", "apellidos", "direccion", "telefono", "dpi", "genero", "fecha_nacimiento", "id_puesto", "fecha_inicio_labores", "fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String [11];
            while (consulta.next()){
                datos [0] = consulta.getString("id");
                datos [1] = consulta.getString("nombres");
                datos [2] = consulta.getString("apellidos");
                datos [3] = consulta.getString("direccion");
                datos [4] = consulta.getString("telefono");
                datos [5] = consulta.getString("dpi");
                datos [6] = consulta.getString("genero");
                datos [7] = consulta.getString("fecha_nacimiento");
                datos [8] = consulta.getString("id_puesto");
                datos [9] = consulta.getString("fecha_inicio_labores");
                datos [10] = consulta.getString("fecha_ingreso");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error al leer empleados: " + ex.getMessage());
        }
        return tabla;
    }
    
    @Override
    public int actualizar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query= "UPDATE empleados SET nombres=?, apellidos=?, direccion=?, telefono=?, dpi=?, genero=?, fecha_nacimiento=?, id_puesto=?, fecha_inicio_labores=?, fecha_ingreso=? WHERE id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDpi());
            parametro.setString(6, getGenero());
            parametro.setString(7, this.getFecha_nacimiento());
            parametro.setInt(8, getId_puesto());
            parametro.setString(9, getFecha_inicio_labores());
            parametro.setString(10, getFecha_ingreso());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
           System.out.println("Error al actualizar empleado: " + ex.getMessage());
        }
        return retorno;
    }
    
    @Override
    public int eliminar(){
        int retorno = 0;
        try{
            PreparedStatement Parametro;
            cn = new Conexion();
            String query= "DELETE FROM empleados WHERE id_empleado = ?;";
            cn.abrir_conexion();
            Parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
            Parametro.setInt(1, getId());
            retorno = Parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
           System.out.println(ex.getMessage());
        }
        return retorno;
    }
    
}
