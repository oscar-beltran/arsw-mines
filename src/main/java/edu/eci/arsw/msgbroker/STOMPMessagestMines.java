/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.aplicacion.Juego;
import edu.eci.arsw.aplicacion.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
@Controller
public class STOMPMessagestMines {
    
    @Autowired
    Juego juego;
    
    @MessageMapping("/crearJuego")    
    public void crearJuego(Partida partida) throws Exception {
        System.out.println("creacion de partida: ingresa");
        
    }
}
