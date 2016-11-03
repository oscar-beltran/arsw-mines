/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.aplicacion.Juego;
import edu.eci.arsw.aplicacion.Jugador;
import edu.eci.arsw.aplicacion.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    @Autowired
    SimpMessagingTemplate msgt;
    
    /**
     * Se crea un juego nuevo publico, se recibe una partida
     * @param partida
     * @throws Exception 
     */
    @MessageMapping("/crearJuego")    
    public void crearJuego(Datos datos) throws Exception {
        System.out.println("creacion de partida:"+datos.getNombre());
        //juego.crearPartida(partida);
    }
    
    /**
     * El estado muestra si el jugador sido agregado
     * @param jugador
     * @param partidaId
     * @throws Exception 
     */
    @MessageMapping("/agregarJugador/{partidaId}")    
    public void crearJuego(Jugador jugador,@DestinationVariable  String partidaId) throws Exception {
        boolean estado;
        System.out.println("creacion de jugador:" + jugador.getNick());
        estado=juego.agregarJugador(jugador, partidaId);
    }
    
    @MessageMapping("/descubrirCasilla/{partidaId}/{posX}/{posY}")    
    public void descubrirCasilla(Jugador jugador,@DestinationVariable String partidaId,@DestinationVariable Integer posX, @DestinationVariable Integer posY) throws Exception {
        System.out.println("jugador:" + jugador.getNick()+"realizo movimiento:"+posX+","+posY);
        juego.realizarMovimiento(jugador, partidaId, posX, posY);
    }
}
