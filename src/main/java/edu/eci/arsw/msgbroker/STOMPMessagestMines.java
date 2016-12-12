/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.aplicacion.Casilla;
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
     * @param datos
     * @throws Exception 
     */
    @MessageMapping("/crearJuego")    
    public void crearJuego(Datos datos) throws Exception {
        System.out.println("Creando partida: "+datos.getIdPartida());
        datos.setEstado(juego.crearPartida(datos));
        msgt.convertAndSend("/topic/patidaCreada"+datos.getIdPartida(),datos);
    }
    
    @MessageMapping("/cargarPartida")    
    public void cargarPartida(DatosCarga datos) throws Exception {
        Datos carga = juego.cargarPartida(datos);
        carga.setJugador(datos.getJugador());
        msgt.convertAndSend("/topic/patidaCreada"+datos.getIdPartida(),carga);
        DatosTablero carga2 = juego.getVidasMinas(datos.getJugador(), datos.getIdPartida());
        msgt.convertAndSend("/topic/vidasMinas"+datos.getIdPartida(),carga2);
        System.out.println("datos enviados");
    }
    
    @MessageMapping("/Casilla")    
    public void descubrirCasilla(DatosSeleccion datos) throws Exception {
        System.out.println("agregando casillas a:"+datos.getIdPartida());
        Casilla casilla = juego.realizarMovimiento(datos);
        System.out.println("Casilla seleccionada:"+casilla.getEstado());
        msgt.convertAndSend("/topic/casillaSeleccionada"+datos.getIdPartida(),casilla);
        DatosTablero carga2 = juego.getVidasMinas(datos.getJugador(), datos.getIdPartida());
        if(carga2.isIsVivo()){
            msgt.convertAndSend("/topic/vidasMinas"+datos.getIdPartida(),carga2);
        }
        else{
            msgt.convertAndSend("/topic/retirarJugador"+datos.getIdPartida(),carga2);
        }
        
            
    }
    
      
}
