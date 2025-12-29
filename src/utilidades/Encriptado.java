/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author mario
 */
public class Encriptado {
    
    // private static final String MILLAVE = "2025tareadam2025"; // 16bytes (16 carácteres) = 128 bits
    
    private static final String MILLAVE = "vJMnURwFuojTiaJT";
    
    public static String Mcano_Encriptar(String textoEncriptado) {
        
        
        String encriptado = null;
        
        try {
            
            Key millaveEnBytes = new SecretKeySpec(MILLAVE.getBytes(), "AES");
            
            // Cipher encriptador = Cipher.getInstance("AES");               // Esto antes no coincidia con lo registrado en CSharp
            
            Cipher encriptador = Cipher.getInstance("AES/CBC/PKCS5Padding");  // Igualar con CSharp
            IvParameterSpec iv = new IvParameterSpec(new byte[16]);          // IV de 16 Bytes (tamaño de bloque AES)
            
            // encriptador.init(Cipher.ENCRYPT_MODE, millaveEnBytes);        // Esto antes no coincidia con lo registrado en CSharp
            encriptador.init(Cipher.ENCRYPT_MODE, millaveEnBytes, iv);
            
            byte[] bytesEncriptados = encriptador.doFinal(textoEncriptado.getBytes());
            encriptado=Base64.encodeBase64String(bytesEncriptados);
            
            System.out.println(encriptado);
            System.out.println(Hex.encodeHexString(bytesEncriptados, true));
                    
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            System.getLogger(Encriptado.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return encriptado;
    }


public static String Mcano_Desencriptar(String textoDesencriptado) {
    
        String desencriptado = null;
            
        try {
            byte[] bytesEncriptados = Base64.decodeBase64(textoDesencriptado);
            
            Key millaveEnBytes = new SecretKeySpec(MILLAVE.getBytes(), "AES");
            
            // Cipher desencriptador = Cipher.getInstance("AES");               // Esto antes no coincidia con lo registrado en CSharp
            
            Cipher desencriptador = Cipher.getInstance("AES/CBC/PKCS5Padding");  // IGUALAR con CSHARP
            IvParameterSpec iv = new IvParameterSpec(new byte[16]);
            
            // desencriptador.init(Cipher.DECRYPT_MODE, millaveEnBytes);         // Esto antes no coincidia con lo registrado en CSharp
            desencriptador.init(Cipher.DECRYPT_MODE, millaveEnBytes, iv);
            
            byte[] bytesDesencriptados = desencriptador.doFinal(bytesEncriptados);
            desencriptado = new String(bytesDesencriptados);
            
            // desencriptado = new String(desencriptador.doFinal(bytesEncriptados));
        
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            System.getLogger(Encriptado.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return desencriptado;
}

}
