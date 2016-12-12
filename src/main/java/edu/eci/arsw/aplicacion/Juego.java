/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import edu.eci.arsw.msgbroker.Datos;
import edu.eci.arsw.msgbroker.DatosSeleccion;
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
    public boolean crearPartida(Datos datos){
        Partida partida = datos.getNuevaPartida();
        partida.inicializar();
        boolean estado = partida.setJugador(datos.getJugador());
        partidas.add(partida);
        return estado;
    }
    
    /**
     * Retorna una partida
     * @param datos
     * @return 
     */
    public Datos cargarPartida(Datos datos){
        System.out.println("cargando 2");
        Datos carga = new Datos();
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getIdPartida().equals(datos.getIdPartida())){
               System.out.println("cargo partida");
               Partida p = partidas.get(i);
               carga.setIdPartida(p.getIdPartida());
               carga.setFilas(p.getFilas());
               carga.setColumnas(p.getColumnas());
               carga.setNombre(p.getNombre());
            } 
        }
        System.out.println("retornando.."+carga.getIdPartida());
        return carga;
    }
    
    /**
     * Se realizan los movimientos para descubrir una nueva casilla
     * @param datos
     * @return 
     */
    public Casilla realizarMovimiento(DatosSeleccion datos){
        Casilla casilla=null;
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getIdPartida().equals(datos.getIdPartida())){
              casilla = partidas.get(i).mover(datos.getJugador(),datos.getPosX(),datos.getPosY());
            } 
        }
        return casilla;
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
