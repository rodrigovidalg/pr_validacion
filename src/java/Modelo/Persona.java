/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL
 */
abstract class Persona {
    private String nombres, apellidos, telefono, genero, fecha_ingreso;
    public Persona (){}

    public Persona(String nombres, String apellidos, String telefono, String genero, String fecha_ingreso) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.genero = genero;
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
        
    public int agregar(){return 0;}
    public int actualizar(){return 0;}
    public int eliminar(){return 0;}
 
}
