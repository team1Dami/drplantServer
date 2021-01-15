/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.exceptions;

/**
 *
 * @author rubir
 */
public class UserNoExistException {
    
    
     private String Login;

    /**
     * Method to set the message
     *
     * @param log
     */
    public UserNoExistException(String log) {
        this.Login = log;
    }

    /**
     * Method to get the message
     *
     * @return a string with the information to the user or null
     */
    public String getMessage() {
        if (Login == null) {
            return "El usuario no esta registrado";
        } else {
            return null;
        }
    }
}
