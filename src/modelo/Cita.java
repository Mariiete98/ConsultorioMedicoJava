/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author mario
 */
public class Cita {
    
    private int id;
    private String dniPaciente, nombre;
    private Date dia;
    private double hora;

    public Cita(int id, String dniPaciente, String nombre, Date dia, double hora) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
    }

    public Cita(String dniPaciente, String nombre, Date dia, double hora) {
        this.dniPaciente = dniPaciente;
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
    }

    public int getId() {
        return id; 
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }
    
    
    
    
    
}

