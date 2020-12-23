/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.exceptions;

/**
 *
 * @author 2dam
 */
public class UserExistException extends Exception {
       
    /**
     * Method to set the message
     *
     * @param log
     */
    public UserExistException(String log) {
        super(log);
    }

    /**
     * Method to get the message
     *
     * @return a string string with the information
     */
    public UserExistException() {      
        super("El login ya está registrado");  
    }
}
