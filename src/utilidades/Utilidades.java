/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author mario
 */
public class Utilidades {
    
    public static boolean ValidaFormuario(JPanel p) {
        for (java.awt.Component c : p.getComponents()) {
            
            if(c instanceof JTextComponent){
                JTextComponent micampo = (JTextComponent) c;
                if (micampo.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo " + c.getName() + " es obligatorio.");
                    micampo.setBackground(Color.RED);
                    return false;
                }
            }
            
            /* // sin textArea
            if (c instanceof JTextField) {
                JTextField micampo = (JTextField) c;
                if (micampo.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo " + c.getName() + " es obligatorio.");
                    micampo.setBackground(Color.RED);
                    return false;
                }
            }
            */
            
            if (c instanceof JComboBox) {
                JComboBox<?> combo = (JComboBox<?>) c;
                if (combo.getSelectedIndex() == 0) { // con Item() == null solo
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento en el combo " + c.getName() + ".");
                    c.setBackground(Color.RED);
                    return false;
                }
            }
            
        }
        return true;
    }
    
    public static boolean ValidaCampoVacio(JTextComponent campo) { // con (JTextField mcano_campo) solo
        if (campo.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " es obligatorio.");
                    campo.setBackground(Color.RED);
                    return false;
        }
        return true;
    }
    
    public static boolean ValidaComboVacio(JComboBox<?> c) { 
        if (c.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento en el combo " + c.getName() + ".");
                    c.setBackground(Color.RED);
                    return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    public static boolean CompruebaEntero(JTextField campo) { 
        try {
            Integer.parseInt(campo.getText().trim());
            return true;
        } catch (NumberFormatException ex) {
            // JOPtionPane ... mejor Alert
            return false;
        }
    }
    
    public static boolean CompruebaDecimal(JTextField campo) { 
        try {
            Double.parseDouble(campo.getText().trim());
            return true;
        } catch (NumberFormatException ex) {
            // JOPtionPane ... mejor Alert
            return false;
        }
    }
    
    public static void AlertaValoresNumericos(JTextField campo) { 
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " no acepta valores no numéricos.");
    }
    
    
    
    
    
    
    public static void LimpiarFormulario(JPanel p) { 
        
        try {
        for (java.awt.Component c : p.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox<?>) c).setSelectedItem(null);
            } else if (c instanceof JDateChooser) {
                ((JDateChooser) c).setDate(new java.util.Date());
            }
        }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al limpiar el formulario:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void ResetarFecha(JDateChooser fecha) { 
        if (fecha != null) {
            fecha.setDate(null);
        }
    }
    
    
    
    
    
    
    public static boolean ValidarDNI(String dni) {
        dni = dni.trim();
        String regex = "^[0-9]{8}[A-Za-z]{1}$";
        
        if (dni.isEmpty() || !Pattern.matches(regex, dni)) {
            JOptionPane.showMessageDialog(null, "El DNI no es correcto");
        }
        
        String dniNumeros = dni.substring(0, 8);
        char letra = dni.charAt(8);
        
        return LetraDNI(dniNumeros, letra);
    }
    
    private static boolean LetraDNI (String numero, char letra) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDNI = Integer.parseInt(numero);
        char letraCalculada = letras.charAt(numeroDNI % 23);
        return letra == letraCalculada;
    }
    
    
    
    public static boolean ValidarCP(String cp) {
        String regex = "^[0-9]{5}$";
        
        if (cp.isEmpty() || !Pattern.matches(regex, cp)) {
            JOptionPane.showMessageDialog(null, "El código postal no es correcto.");
            return false;
        }
        
        String prefijo = cp.substring(0, 2);
        
        return ValidarProvinciaCP(prefijo);
    }
    
    private static boolean ValidarProvinciaCP(String prefijo) {
        String[] prefijos = {
            "01","02","03","04","05","06","07","08","09",
        "10","11","12","13","14","15","16","17","18","19",
        "20","21","22","23","24","25","26","27","28","29",
        "30","31","32","33","34","35","36","37","38","39",
        "40","41","42","43","44","45","46","47","48","49",
        "50","51","52"
        };
        
        for (String p : prefijos) {
            if (p.equals(prefijo)) {
                return true;
            }
        }
        
        JOptionPane.showMessageDialog(null, "El código postal no pertenece a una provincia válida.");
        return false;
    } 
    
    
    
    
    public static boolean CompruebaFechaCita(JDateChooser fecha) {
        Date fechaActual = new Date();
        
        if (fecha == null || fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Selecciona una fecha.");
            fecha.setBackground(Color.RED);
            return false;
        }
        
        if (fecha.getDate().equals(fechaActual)) {
            JOptionPane.showMessageDialog(null, "Selecciona otra fecha.");
            fecha.setDate(fechaActual);
            fecha.setBackground(Color.RED);
            return false;
        }
        
        if (fecha.getDate().before(fechaActual)) {
            JOptionPane.showMessageDialog(null, "No se puede seleccionar una fecha pasada.");
            fecha.setDate(fechaActual);
            fecha.setBackground(Color.RED);
            return false;
        }
        
        return true;
    }
    
    
    
    public static boolean CompruebaFechaNacimiento(JDateChooser fecha) {
        Date fechaActual = new Date();
        
        if (fecha == null || fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Selecciona una fecha.");
            fecha.setBackground(Color.RED);
            return false;
        }
        
        if (fecha.getDate().after(fechaActual)) {
            JOptionPane.showMessageDialog(null, "La fecha debe ser anterior.");
            fecha.setDate(fechaActual);
            fecha.setBackground(Color.RED);
            return false;
        }
        
        return true;
    }
    
    
    
    
    
    
    
    public static boolean ValidaBotonSexo(JRadioButton mujer, JRadioButton hombre, JRadioButton otro) {
        if (!mujer.isSelected() && !hombre.isSelected() && !otro.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de género");
            return false;
        }
        return true;
    }
    
    public static boolean ValidaBotonTabaquismo(JRadioButton no, JRadioButton si) {
        if (!no.isSelected() && !si.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opción de consumo de tabaco.");
            return false;
        }
        return true;
    }
        
    public static boolean ValidaBotonAlcohol(JRadioButton ocasional, JRadioButton habitual, JRadioButton nulo) {
        if (!ocasional.isSelected() && !habitual.isSelected() && !nulo.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de consumo de alcohol");
            return false;
        }
        return true;
    }    
    
    
    
    
    public static boolean ValidarColegiado(String numColegiado) {
        
        String regex = "^[1-9][0-9]{8}$";
        
        if (numColegiado.isEmpty() && !Pattern.matches(regex, numColegiado)) {
            JOptionPane.showMessageDialog(null, "El número de colegiado no es correcto");
            return false;
        }
        return true;
    }   
    
    
    public static boolean ValidarTelefono(String tlf){
        String regex = "^(\\+34|34)?(6\\d{8}|7\\d{8}|9[1-9]\\d{7})$";

        if (tlf.isEmpty() || !Pattern.matches(regex, tlf)) {
            JOptionPane.showMessageDialog(null, "El número de teléfono no es correcto");
            return false;
        }

        return true;
    }
        
     public static boolean ValidarEmail(String mcano_email) {
        String mcano_regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (mcano_email.isEmpty() || !Pattern.matches(mcano_regex, mcano_email)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es correcto.");
            return false;
        }

        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
