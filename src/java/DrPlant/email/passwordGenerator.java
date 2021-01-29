/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.email;

/**
 *
 * @author Eneko, Ruben
 */
public class passwordGenerator {

    /**
     * Posible numbers on the password generation
     */
    public static String NUMEROS = "0123456789";

    /**
     *  Posible capital letters on the password generation
     */
    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Posible lower cases on the password generation
     */
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    /**
     *  Gets the final password
     * @return The generated pasword
     */
    public static String getPassword() {
        return getPassword(8);
    }

    /**
     * Generate the password
     * @param length The lenght of the password
     * @return Te new pasword
     */
    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    /**
     * Builds the new password
     * @param key The available characters that can be in the password
     * @param length The length of the password
     * @return The new pasword
     */
    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }
}
