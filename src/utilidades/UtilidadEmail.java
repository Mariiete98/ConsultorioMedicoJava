/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author mario
 */
public class UtilidadEmail {
    
    private final String asunto;
    private final String mensaje;
    private final String destinatario;
    //String ruta; // No se va a usar ningún archivo
    
    
    // Estaría mejor hacer 5 variables private static final de Configuración SMTP 
    // y un metodo private void...
    

    /**
     * Constructor con ruta, no se usará archivo adjunto
     * @param asunto
     * @param mensaje
     * @param destinatario
     * @param ruta 
     */
    /*
    public UtilidadEmail(String asunto, String mensaje, String destinatario, String ruta) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
        this.ruta = ruta;
    } */
    
    /**
     * Constructor sin ruta, sí se usa
     * @param asunto
     * @param mensaje
     * @param destinatario 
     */
    public UtilidadEmail(String asunto, String mensaje, String destinatario) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
    }
    
    
    public boolean enviaMailSimple() {
        
        try {
            
            Email email = new SimpleEmail();
            
            // Parámetros de coenxión con la cuenta de correo
            
            email.setHostName("smtp.hostinger.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("consultorio@reynaldomd.com", "2024-Consultorio"));
            email.setSSLOnConnect(true);
            email.setCharset("UTF-8");
            email.setFrom("consultorio@reynaldomd.com"); // ¿phising si se pone otro correo?
            
            // Motar mensaje
            
            email.setSubject(asunto);
            email.setMsg(mensaje);
            email.addTo(destinatario);
            
            // Envio de correo
            
            email.send();
            return true;
              
        } catch (EmailException ex) {
            System.getLogger(UtilidadEmail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false; 
        
    }
    
    public boolean enaviaMailHtml() {
    
        try {
            HtmlEmail email = new HtmlEmail();
            
            // Parámetros de coenxión con la cuenta de correo
            
            email.setHostName("smtp.hostinger.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("consultorio@reynaldomd.com", "2024-Consultorio"));
            email.setSSLOnConnect(true);
            email.setCharset("UTF-8");
            email.setFrom("consultorio@reynaldomd.com");
                    
            // Motar mensaje
                    
            email.setSubject(asunto);
            email.setMsg(mensaje);
            email.addTo(destinatario);
                    
            // Envio de correo
            
            email.send();
            return true;
                    
            } catch (EmailException ex) {
            System.getLogger(UtilidadEmail.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return false;
    
    }
    
    
    
    
    
}
