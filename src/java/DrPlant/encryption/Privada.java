/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.encryption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <b>Criptografía Simétrica (Clave Secreta)</b> <br/>
 * <br/>
 *
 * Esta clase permite cifrar un texto mediante una <b>clave secreta</b> y lo
 * guarda en un fichero. La única forma de descifrar el texto es mediante dicha
 * clave, que tanto el <u>emisor</u> como el <u>receptor</u> la deben conocer.
 *
 * En este caso vamos a utilizar:
 * <ul>
 * <li>El algoritmo AES</li>
 * <li>El modo CBC: Existen dos, el ECB que es sencillo, y el CBC que necesita
 * un vector de inicialización(IV)</li>
 * <li>El padding PKCS5Padding (128): Si el mensaje no es múltiplo de la
 * longitud del algoritmo se necesita un relleno.</li>
 * </ul>
 * AES solo admite <b>tamaños de clave</b> de 16, 24 o 32 bytes. Se debe
 * proporcionar exactamente ese tamaño de clave o usar una
 * <b>"salt"(Semilla)</b>. En criptografía, un salt es un dato aleatorio que se
 * usa como una entrada adicional de cifrado. En este caso, vamos a utilizar
 * salt para crear una clave de exactamente 16 bytes. <br/>
 * <br/>
 * Generalmente un salt se genera aleatoriamente cuando creas la clave, así que
 * <u>necesitas guardar</u> la clave y su salt para poder cifrar y descifrar.
 */
public class Privada {

    private byte[] descifrarTexto(byte[] mensaje) {
        byte[] decodedMessage = null;
        try {
            // Cargamos la clave privada
            byte fileKey[] = fileReader("Public");
            System.out.println("Tamaño -> " + fileKey.length + " bytes");

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

    /**
     * Retorna el contenido de un fichero
     *
     * @param path Path del fichero
     * @return El texto del fichero
     */
    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
