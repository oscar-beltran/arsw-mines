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
    
    @MessageMapping("/ValidarCodigo")    
    public void validarCodigo(DatosCarga datos) throws Exception {        
        //System.out.println(datos.getJugador());
        String clave = datos.getIdPartida();
        if(clave.trim().isEmpty()){
            msgt.convertAndSend("/topic/mensaje." + datos.getJugador(), "Por favor llene el campo solicitado...");
        }else{
            if(juego.buscaPartida(clave)){
                msgt.convertAndSend("/topic/respuesta." + datos.getJugador(), datos);
            }else{
                msgt.convertAndSend("/topic/mensaje." + datos.getJugador(), "La partida con identificación " + clave + " no existe...");
            }
        }
    }
    
    @MessageMapping("/Casilla")    
    public void descubrirCasilla(DatosSeleccion datos) throws Exception {
        System.out.println("agregando casillas a:"+datos.getIdPartida());
        Casilla casilla = juego.realizarMovimiento(datos);
        System.out.println("Casilla seleccionada:"+casilla.getEstado());
        msgt.convertAndSend("/topic/casillaSeleccionada"+datos.getIdPartida(),casilla);
        DatosTablero carga2 = juego.getVidasMinas(datos.getJugador(), datos.getIdPartida());
        System.out.println(carga2.isIsVivo());
        if(carga2.isIsVivo()){
            msgt.convertAndSend("/topic/vidasMinas"+datos.getIdPartida(),carga2);
        }
        else{
            msgt.convertAndSend("/topic/retirarJugador"+datos.getIdPartida(),carga2);
        }        
    }
    
    @MessageMapping("/poblarCasillas")    
    public void poblarCasillas(DatosSeleccion datos) throws Exception {
        System.out.println("Poblando tablero:"+datos.getIdPartida());
        Casilla[][] casillas = juego.consultaCasilla(datos);
        Casilla c = null;
        int filas = juego.consultaDatosPartida(datos,"filas");
        int columnas = juego.consultaDatosPartida(datos,"columnas");
        for(int i = 0; i<filas;i++){
            for(int j=0;j<columnas;j++){
                c=casillas[i][j];
                if(c.isActiva()){
                    System.out.println(c.getEstado());
                    msgt.convertAndSend("/topic/casillaSeleccionada"+datos.getIdPartida(),c);   
                }
            }
        }
    }
      
}
