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
public class Paciente {
    
    private String dni, nombre, apellidos;
    private Date fechaNacimiento;
    private int telefono;
    private String email; 
    private int cp;
    private String sexo, tabaquismo, consumoalcohol, antecedentesSalud, datosSaludGeneral; 
    private Date fechaRegistro;

    public Paciente(String dni, String nombre, String apellidos, Date fechaNacimiento, int telefono, String email, int cp, String sexo, String tabaquismo, String consumoalcohol, String antecedentesSalud, String datosSaludGeneral, Date fechaRegistro) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.cp = cp;
        this.sexo = sexo;
        this.tabaquismo = tabaquismo;
        this.consumoalcohol = consumoalcohol;
        this.antecedentesSalud = antecedentesSalud;
        this.datosSaludGeneral = datosSaludGeneral;
        this.fechaRegistro = fechaRegistro;
    }

    public Paciente(String dni, String nombre, String apellidos, int telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    public Paciente(String nombre, String apellidos, int telefono, int cp) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.cp = cp;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(String tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public String getConsumoalcohol() {
        return consumoalcohol;
    }

    public void setConsumoalcohol(String consumoalcohol) {
        this.consumoalcohol = consumoalcohol;
    }

    public String getAntecedentesSalud() {
        return antecedentesSalud;
    }

    public void setAntecedentesSalud(String antecedentesSalud) {
        this.antecedentesSalud = antecedentesSalud;
    }

    public String getDatosSaludGeneral() {
        return datosSaludGeneral;
    }

    public void setDatosSaludGeneral(String datosSaludGeneral) {
        this.datosSaludGeneral = datosSaludGeneral;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

  
    
    
    
    
    
    
}
