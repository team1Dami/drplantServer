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
    
    
     private String Login;

    /**
     * Method to set the message
     *
     * @param log
     */
    public UserExistException(String log) {
        this.Login = log;
    }

    /**
     * Method to get the message
     *
     * @return a string with the information to the user or null
     */
    public String getMessage() {
        if (Login == null) {
            return "El login ya está registrado";
        } else {
            return null;
        }
    }
}
