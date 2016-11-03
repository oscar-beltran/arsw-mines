/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import edu.eci.arsw.msgbroker.Datos;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
@Service
public class Juego {
   
    private ArrayList<Partida> partidas = new ArrayList<Partida>();
    
    /**
     * Se reciben los datos de creacion de nueva partida
     * @param datos suministrados por el usuario
     */
    public void crearPartida(Datos datos){
        Partida partida = datos.getNuevaPartida();
        partida.inicializar();
        boolean estado = partida.setJugador(datos.getJugador());
        partidas.add(partida);
        
    }
    
    public void realizarMovimiento(Jugador jugador, String namePartida, Integer posX, Integer posY){
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getNombre().equals(namePartida)){
              partidas.get(i).mover(jugador,posX,posY);
            } 
        }
    }
    
    /**
     * Se agrega un jugador a una partida publica
     * @param nick
     * @param namePartida
     * @return true si este es agregado, false si no
     */
    public boolean agregarJugador(String nick,String namePartida){
        boolean agrego = false;
        for(int i=0;i<partidas.size();i++){
            System.out.println(partidas.get(i).getNombre()+","+namePartida);
            if(partidas.get(i).getNombre().equals(namePartida)){
              agrego=partidas.get(i).setJugador(nick);
            }
        }
        return agrego;
    }
    
    /**
     * Verifica la existencia del nombre de la partida para que esta sea unica
     * @param nombre
     * @return tru si el nombre no se encuentra, false si ya esta en la lista de partidas
     */
    public boolean validaNombre(String nombre){
        boolean verifica = false;
        for(int i=0;i<partidas.size();i++){
            if(partidas.get(i).getNombre()==nombre){
                verifica = true;
            }
        }
        return verifica;    
    }
    
    
}
