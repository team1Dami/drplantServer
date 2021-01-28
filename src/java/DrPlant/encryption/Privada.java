/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.encryption;

import java.io.*;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;

/**
 * <b>Criptografía Simétrica (Clave Secreta)</b> <br/>
 * <br/>
 *
 * Esta clase permite descifrar un array de bytes mediante una <b>clave
 * secreta</b>
 *
 */
public class Privada {

    public byte[] descifrarPass(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            // Cargamos la clave privada
            byte fileKey[] = fileReader("/DrPlant/encryption/RSA_Private.key");

            // Obtenemos una instancia de KeyFactory, algoritmo RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Creamos un nuevo PKCS8EncodedKeySpec del fileKey
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            // Generamos la public key con el keyFactory
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
            // Obtenemos una instancia del Cipher "RSA/ECB/PKCS1Padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // Iniciamos el cipher (DECRYPT_MODE)
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // El método doFinal nos descifra el mensaje
            decodedMessage = cipher.doFinal(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;
    }

    public byte[] fileReader(String path) { 
        try {
            InputStream keyfis = Privada.class.getClassLoader()
                    .getResourceAsStream(path);          
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            // read bytes from the input stream and store them in buffer
            while ((len = keyfis.read(buffer)) != -1) {
                // write bytes from the buffer into output stream
                os.write(buffer, 0, len);
            }
            keyfis.close();
            return os.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Privada.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
