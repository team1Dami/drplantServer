    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.encryption;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <b>Función de una sola vía (o función hash)</b> <br/>
 * <br/>
 * 
 * Dado que es <u>imposible</u> obtener el texto original a partir de un hash,
 * SHA es útil para guardar passwords en BBDD. Guardamos el hash, no el
 * password, de forma que cada vez que tengamos que autenticar a un usuario
 * primero calculamos el hash del password que nos llega con el hash de BBDD.
 * Si coinciden, genial. Si no, es que la clave es incorrecta.
 *
 */
public class Hash {

    /**
     * Aplica SHA al texto pasado por parámetro
     * 
     * @param texto
     */
    public void cifrarTexto(String texto) {
        MessageDigest messageDigest;
        try {
            // Obtén una instancia de MessageDigest que usa SHA
            messageDigest = MessageDigest.getInstance("SHA");
            // Convierte el texto en un array de bytes 
            byte[] text=texto.getBytes();
			// Actualiza el MessageDigest con el array de bytes 
            messageDigest.update(text);
			// Calcula el resumen (función digest)
            //messageDigest.digest();

            System.out.println("Mensaje original: "+texto);
            System.out.println("Número de Bytes: "+text.length);
            System.out.println("Algoritmo usado: "+messageDigest.getAlgorithm());
            System.out.println("Resumen del Mensaje: "+messageDigest.digest());
            System.out.println("Mensaje en Hexadecimal: "+Hexadecimal(messageDigest.digest()));
            System.out.println("Proveedor: "+messageDigest.getProvider());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Convierte Array de Bytes en hexadecimal
    static String Hexadecimal(byte[] resumen) {
        String HEX = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1)
                    HEX += "0";
            HEX += h;
        }
        return HEX.toUpperCase();
    }

    public static void main(String[] args) {
        Hash ejemploSHA = new Hash();
        ejemploSHA.cifrarTexto("Mensaje que se va a encriptar");
    }
}
