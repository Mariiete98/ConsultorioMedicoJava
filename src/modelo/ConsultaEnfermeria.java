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
public class ConsultaEnfermeria {
    
    private int id;
    private String dniPaciente;
    private Date fechaConsulta;
    private double maxima, minima;
    // private int glucosa;
    private double glucosa; // en la bbdd sale double pero podria ser int aqui...
    private double peso;
    private int codigoFacultativo;

    public ConsultaEnfermeria(int id, String dniPaciente, Date fechaConsulta, double maxima, double minima, double glucosa, double peso, int codigoFacultativo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.fechaConsulta = fechaConsulta;
        this.maxima = maxima;
        this.minima = minima;
        this.glucosa = glucosa;
        this.peso = peso;
        this.codigoFacultativo = codigoFacultativo;
    }

    public ConsultaEnfermeria(String dniPaciente, Date fechaConsulta, double maxima, double minima, double glucosa, double peso, int codigoFacultativo) {
        this.dniPaciente = dniPaciente;
        this.fechaConsulta = fechaConsulta;
        this.maxima = maxima;
        this.minima = minima;
        this.glucosa = glucosa;
        this.peso = peso;
        this.codigoFacultativo = codigoFacultativo;
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

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public double getMaxima() {
        return maxima;
    }

    public void setMaxima(double maxima) {
        this.maxima = maxima;
    }

    public double getMinima() {
        return minima;
    }

    public void setMinima(double minima) {
        this.minima = minima;
    }

    public double getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(double glucosa) {
        this.glucosa = glucosa;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCodigoFacultativo() {
        return codigoFacultativo;
    }

    public void setCodigoFacultativo(int codigoFacultativo) {
        this.codigoFacultativo = codigoFacultativo;
    }
    
    
    
    
}
