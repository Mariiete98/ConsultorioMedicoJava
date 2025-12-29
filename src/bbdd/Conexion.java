/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utilidades.Encriptado;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Consulta;
import modelo.ConsultaEnfermeria;
import modelo.Paciente;
import modelo.Personal;

/**
 *
 * @author mario
 */
public class Conexion {
    
    private static Connection conn;
    
    /* CON DATOS DE REYNALDO
    
    public static void conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://145.14.151.1/u812167471_consultorio25", "u812167471_consultorio25", "2025-Consultorio");
        
            if (conn != null) {
                System.out.println("Conexión establecida exitosamente.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        
        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    */
    
    // MÁS EFICIENTE:
    
    /*
    
    private static final String URL = "jdbc:mysql://145.14.151.1:3306/u812167471_consultorio25?useSSL=false&serverTimezone=UTC";
    private static final String USER = "u812167471_consultorio25";
    private static final String PASS = "2025-Consultorio";

    public static Connection conectar() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(Conexion.class.getName())
                  .log(System.Logger.Level.ERROR, "Error al conectar", ex);
        }

        return conn;
    }

    */
    
    // CON MI BBDD
    
    public static void conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mcano_medicos", "root", "");
        
            if (conn != null) {
                System.out.println("Conexión establecida exitosamente.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        
        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void cerrarConexion(){
        
        try {
            conn.close();
            System.out.println("Conexión cerrada exitosamente.");
        } catch (SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    
    
    
    // --------------- LOGIN
    
    
    public static boolean acceder (String user, String pass) {
        
        try {
            
            String consulta = "SELECT usuario, contrasenya FROM personal WHERE usuario = ? AND contrasenya = ?";
            
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            String passEnc = Encriptado.Mcano_Encriptar(pass);
            
            comando.setString(1, user);
            comando.setString(2, passEnc);
            
            ResultSet rs = comando.executeQuery();
            boolean existe = rs.next();
            
            rs.close();
            comando.close();
            
            return existe;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
  
    }
    
    
    
    public static String[] recuperaDatosUserLogado (String user) {
        
        String consulta = "SELECT numero_colegiado, nombre, apellidos, tipo FROM personal WHERE usuario = ?";
        String[] datos = new String[3];
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setString(1, user);
            
            ResultSet rs = comando.executeQuery();
            
            if(rs.next()) {

                datos[0] = rs.getString("nombre") + " " + rs.getString("apellidos");
                datos[1] = String.valueOf(rs.getInt("numero_colegiado"));
                datos[2] = rs.getString("tipo");
                
            } else {
                datos = null;
            }
            
            rs.close();
            comando.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            datos = null;
        }
        
        return datos;
        
    }
    
    
    
    
    
     // --------------- MENU PRINCIPAL
    
    
    public static void recuperaCitasMedicas (DefaultTableModel modelo) {
        
        String consulta = "SELECT nombre, dia, hora FROM citas WHERE dia >= ? ORDER BY dia ASC";
        Object[] citasm = new Object[3];
        
        LocalDate hoy = LocalDate.now();
        Date fechahoy = Date.valueOf(hoy);
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setDate(1, fechahoy);
            
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()) {
                
                String nombreEncriptado = rs.getString("nombre");
                String nombreDesencriptado = Encriptado.Mcano_Desencriptar(nombreEncriptado);

                citasm[0] = nombreDesencriptado;
                citasm[1] = rs.getDate("dia");
                citasm[2] = rs.getDouble("hora");
                
                modelo.addRow(citasm);
                
            } 
            
            rs.close();
            comando.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public static void recuperaCitasEnfermeria (DefaultTableModel modelo) {
        
        String consulta = "SELECT nombre, dia, hora FROM citasEnfermeria WHERE dia >= ? ORDER BY dia ASC";
        Object[] citasm = new Object[3];
        
        LocalDate hoy = LocalDate.now();
        Date fechahoy = Date.valueOf(hoy);
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setDate(1, fechahoy);
            
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()) {
                
                String nombreEncriptado = rs.getString("nombre");
                String nombreDesencriptado = Encriptado.Mcano_Desencriptar(nombreEncriptado);

                citasm[0] = nombreDesencriptado;
                citasm[1] = rs.getDate("dia");
                citasm[2] = rs.getDouble("hora");
                
                modelo.addRow(citasm);
                
            } 
            
            rs.close();
            comando.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    // --------------- NUEVA CONSULTA
    
    
    public static boolean compruebaDNI (String dni) {
        
        String consulta = "SELECT dni FROM paciente WHERE dni = ?";

        
        try {   
            
            String dniEncriptado = Encriptado.Mcano_Encriptar(dni);
            
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setString(1, dniEncriptado);
            
            ResultSet rs = comando.executeQuery();
            boolean existe = rs.next();
            
            rs.close();
            comando.close();
            
            return existe;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
   
    }
    
    public static Paciente recuperaDatosPaciente (String dni) {
        
        // DNI NOMBRE Y APELLIDOS ESTAN ENCRIPTADOS
        
        Paciente pacienteObtenido = null;
        
        String consulta = "SELECT nombre, apellidos, telefono, email FROM paciente WHERE dni = ?";
       
        try {   
            
            // Encriptamos el DNI para buscarlo en el BBDD
            
            String dniEncriptado = Encriptado.Mcano_Encriptar(dni);
            
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setString(1, dniEncriptado);
            
            ResultSet rs = comando.executeQuery();
            
            if (rs.next()) {
                
                String nombreEncriptado = rs.getString("nombre");
                String nombreDesencriptado = Encriptado.Mcano_Desencriptar(nombreEncriptado);
                String apellidosEncriptado = rs.getString("apellidos");
                String apellidosDesencriptados = Encriptado.Mcano_Desencriptar(apellidosEncriptado);
                int telefono = rs.getInt("telefono");
                String email = rs.getString("email");
                
                pacienteObtenido = new Paciente(
                        dni,
                        //Desencriptar nombre y apellidos
                        nombreDesencriptado,
                        apellidosDesencriptados,
                        telefono, // QUE COINCIDA INT CON BBDD
                        email
                );

            } 
            
            rs.close();
            comando.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pacienteObtenido;

    }
    
    public static void cargarTablaConsultasMedicas (DefaultTableModel modelo, String dni) {
        
        Object[] consultasm = new Object[4];
        
        String consulta = "SELECT fechaConsulta, diagnostico, tratamiento, observaciones FROM consultas WHERE dniPaciente = ? ORDER BY fechaConsulta ASC";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            // IGUAL NO HAY QUE ENCRIPTAR
          
            String dniEncriptado = Encriptado.Mcano_Encriptar(dni);
            
            comando.setString(1, dniEncriptado);
            
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()) {
                
                consultasm[0] = rs.getDate("fechaConsulta");
                consultasm[1] = rs.getString("diagnostico");
                consultasm[2] = rs.getString("tratamiento");
                consultasm[3] = rs.getString("observaciones");
                
                modelo.addRow(consultasm);
                
            } 
            
            rs.close();
            comando.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public static void cargarTablaConsultasEnfermeria (DefaultTableModel modelo, String dni) {
        
        Object[] consultase = new Object[5];
        
        String consulta = "SELECT fechaConsulta, tensionMax, tensionMin, glucosa, peso FROM enfermeria WHERE dniPaciente = ? ORDER BY fechaConsulta ASC";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            // IGUAL NO HAY QUE ENCRIPTAR
          
            String dniEncriptado = Encriptado.Mcano_Encriptar(dni);
            
            comando.setString(1, dniEncriptado);
            
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()) {
                
                consultase[0] = rs.getDate("fechaConsulta");
                consultase[1] = rs.getDouble("tensionMax");
                consultase[2] = rs.getDouble("tensionMin");
                consultase[3] = rs.getDouble("glucosa");
                consultase[4] = rs.getDouble("peso");
                
                modelo.addRow(consultase);
                
            } 
            
            rs.close();
            comando.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    
    
    
    
    
    
    
     
    
    // --------------- NUEVA CITA MEDICA
    
    
    
    public static boolean registrarCitaMedica (Cita c) {
        
         String consulta = "INSERT INTO citas (dniPaciente, nombre, dia, hora) VALUES (?, ?, ?, ?)";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setString(1, c.getDniPaciente());
            comando.setString(2, c.getNombre());
            
            java.sql.Date diaUtilDate = new java.sql.Date(c.getDia().getTime()); // se recibe como Local Date pero el objeto Empleado usa Util Date
            comando.setDate(3, diaUtilDate);
            comando.setDouble(4, c.getHora());
            
            comando.executeUpdate();

            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    // --------------- NUEVA CITA ENFERMERIA
    
    
    
    public static boolean registrarCitaEnfermeria (Cita c) {
        
        String consulta = "INSERT INTO citasenfermeria (dniPaciente, nombre, dia, hora) VALUES (?, ?, ?, ?)";
        
        // con Reynadldo es citasEnfermeria
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setString(1, c.getDniPaciente());
            comando.setString(2, c.getNombre());
            
            java.sql.Date diaUtilDate = new java.sql.Date(c.getDia().getTime()); // se recibe como Local Date pero el objeto Empleado usa Util Date
            comando.setDate(3, diaUtilDate);
            comando.setDouble(4, c.getHora());
            
            comando.executeUpdate();

            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    
    
    
    // --------------- NUEVO INFORME MEDICO
    
    
    
    public static boolean registrarConsultaMedica (Consulta c) {
        
        String consulta = "INSERT INTO consultas (dniPaciente, fechaConsulta, diagnostico, tratamiento, observaciones, codigofacultativo) VALUES (?, ?, ?, ?, ?, ?)";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setString(1, c.getDniPaciente());
            
            java.sql.Date fechaSQL = new java.sql.Date(c.getFechaConsulta().getTime());
            comando.setDate(2, fechaSQL);
            
            comando.setString(3, c.getDiagnostico());
            comando.setString(4, c.getTratamiento());
            comando.setString(5, c.getObservaciones());
            comando.setInt(6, c.getCodigoFacultativo());
            
            comando.executeUpdate();

            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    
    
    
     // --------------- NUEVO INFORME ENFERMERIA
    
    
    
    public static boolean registrarConsultaEnfermeria (ConsultaEnfermeria c) {
        
        String consulta = "INSERT INTO enfermeria  (dniPaciente, fechaConsulta, tensionMax, tensionMin, glucosa, peso, codigoFacultativo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setString(1, c.getDniPaciente());
            
            // se recibe ya bien?
            java.sql.Date fechaSQL = new java.sql.Date(c.getFechaConsulta().getTime());
            comando.setDate(2, fechaSQL);
            
            comando.setDouble(3, c.getMaxima());
            comando.setDouble(4, c.getMinima());
            comando.setDouble(5, c.getGlucosa());
            comando.setDouble(6, c.getPeso());
            comando.setInt(7, c.getCodigoFacultativo());
            
            comando.executeUpdate();

            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    
    
    
    
    
    
    // --------------- NUEVO PACIENTE
    
    
    
    public static void cargasComboCp (JComboBox c) {
        
        String consulta = "SELECT DISTINCT codigopostal FROM codigospostales ORDER BY codigopostal ASC";
        
        
        try {   
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()) {
                
                c.addItem(rs.getString("codigopostal"));
                
            } 
            
            rs.close();
            st.close();
            
            } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static boolean registrarPaciente (Paciente p) {
        
        String consulta = "INSERT INTO paciente (dni, nombre, apellidos, fechaNacimiento, telefono, email, cp, sexo, tabaquismo, consumoalcohol, antecedentesSalud, datosSaludGeneral, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setString(1, p.getDni());
            comando.setString(2, p.getNombre());
            comando.setString(3, p.getApellidos());
            
            comando.setDate(4, new java.sql.Date(p.getFechaNacimiento().getTime()));
            
            comando.setInt(5, p.getTelefono());
            comando.setString(6, p.getEmail());
            comando.setInt(7, p.getCp());
            comando.setString(8, p.getSexo());
            comando.setString(9, p.getTabaquismo());
            comando.setString(10, p.getConsumoalcohol());
            comando.setString(11, p.getAntecedentesSalud());
            comando.setString(12, p.getDatosSaludGeneral());
            
            comando.setDate(13, new java.sql.Date(p.getFechaRegistro().getTime()));
          
            comando.executeUpdate();
            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    
    // ---------------------------- Pacientes
    
     public static void cargaTablaPacientes (DefaultTableModel modelo) {
        
        Object[] fila = new Object[5];
        
        String consulta = "SELECT dni, nombre, apellidos, telefono, cp FROM paciente ORDER BY nombre  ASC";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()) {
                
                fila[0] = Encriptado.Mcano_Desencriptar(rs.getString("dni"));
                fila[1] = Encriptado.Mcano_Desencriptar(rs.getString("nombre"));
                fila[2] = Encriptado.Mcano_Desencriptar(rs.getString("apellidos"));
                fila[3] = rs.getInt("telefono");
                fila[4] = rs.getInt("cp");
                
                modelo.addRow(fila);
                
            } 
            
            rs.close();
            comando.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
      public static boolean actualizaPacientes (Paciente p, String dni) {
        
        String consulta = "UPDATE paciente SET nombre=?, apellidos=?, telefono=?, cp=? WHERE dni=?";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setString(1, Encriptado.Mcano_Encriptar(p.getNombre()));
            comando.setString(2, Encriptado.Mcano_Encriptar(p.getApellidos()));
            comando.setInt(3, p.getTelefono());
            comando.setInt(4, p.getCp());
            comando.setString(5, dni);
            

            comando.executeUpdate();
            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     
    }
    
    
      
      
    
    // ---------------------------- Personal Medico
    
      
    public static boolean compruebaUser (String user) {
        
        String consulta = "SELECT usuario FROM personal WHERE usuario = ?";

        
        try {   

            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setString(1, user);
            
            ResultSet rs = comando.executeQuery();
            boolean existe = rs.next();
            
            rs.close();
            comando.close();
            
            return existe;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   
    }
    
     public static boolean compruebaNumeroColegiado (int numero) {
        
        String consulta = "SELECT numero_colegiado FROM personal WHERE numero_colegiado = ?";

        
        try {   

            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);
            
            comando.setInt(1, numero);
            
            ResultSet rs = comando.executeQuery();
            boolean existe = rs.next();
            
            rs.close();
            comando.close();
            
            return existe;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
   
    }
    
    
    public static boolean registrarPersonal (Personal p) {
        
        String consulta = "INSERT INTO personal (numero_colegiado, nombre, apellidos, telefono, usuario, contrasenya, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        
        try {   
            PreparedStatement comando = Conexion.conn.prepareStatement(consulta);

            comando.setInt(1, p.getNumero_colegiado());
            comando.setString(2, p.getNombre());
            comando.setString(3, p.getApellidos());
            comando.setInt(4, p.getTelefono());
            comando.setString(5, p.getUsuario());
            comando.setString(6, p.getContrasenya());
            comando.setString(7, p.getTipo());
            
          
            comando.executeUpdate();
            comando.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
