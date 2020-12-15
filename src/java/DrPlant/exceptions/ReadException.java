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
public class ReadException extends Exception{
    
      private String message;
    //  private Error code;

    /**
     * Method to set the message
     *
     * @param m
     */
    public ReadException(String m) {
        this.message = m;
    }

    /**
     * Method to get the message
     *
     * @return a string with the information to the user or null
     */
    public String getMessage() {
        if (message == null) {
            return "Ha ocurrido un error en el update";
        } else {
            return null;
        }
    }
}
