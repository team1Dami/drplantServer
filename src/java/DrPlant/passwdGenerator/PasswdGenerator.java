/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.passwdGenerator;


/**
 *
 * @author rubir
 */
public class PasswdGenerator {
   public static String NUMEROS = "0123456789";
 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	//public static String ESPECIALES = "ñÑ";
 
	
	public static String getPassword(String email) {
		return getPassword(8,email);
	}
 
	public static String getPassword(int length,String email) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length, email);
	}
 
	public static String getPassword(String key, int length, String email) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
                //Email mail = new Email(pswd, pswd, pswd, length))
		return pswd;
	}
}