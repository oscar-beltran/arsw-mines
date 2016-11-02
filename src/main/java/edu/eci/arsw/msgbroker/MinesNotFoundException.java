/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

/**
 *
 * @author USUARIO
 */
public class MinesNotFoundException extends Exception{
    
    public MinesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinesNotFoundException(String cause) {
        super(cause);
    }
    
}
