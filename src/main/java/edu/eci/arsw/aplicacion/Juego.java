/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import edu.eci.arsw.msgbroker.Datos;
import edu.eci.arsw.msgbroker.DatosCarga;
import edu.eci.arsw.msgbroker.DatosSeleccion;
import edu.eci.arsw.msgbroker.DatosTablero;
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
     * @return 
     */
    public boolean crearPartida(Datos datos){
        Partida partida = datos.getNuevaPartida();
        partida.inicializar();
        System.out.println(datos.getJugador());
        boolean estado = partida.setJugador(datos.getJugador());
        partida.setNumeroJugadores(partida.getNumeroJugadores()+1);
        boolean add = partidas.add(partida);
        return estado;
    }
    
    /**
     * Retorna una partida
     * @param datos
     * @return 
     */
    public Datos cargarPartida(DatosCarga datos){
        //System.out.println("cargando 2");
        Datos carga = new Datos();
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getIdPartida().equals(datos.getIdPartida())){
               //System.out.println("cargo partida");
               Partida p = partidas.get(i);
               carga.setIdPartida(p.getIdPartida());
               carga.setFilas(p.getFilas());
               carga.setColumnas(p.getColumnas());
               carga.setNombre(p.getNombre());
               carga.setJugador(datos.getJugador());
               carga.setTipoPartida(p.getTipoPartida());
            } 
        }
        //System.out.println("retornando.."+carga.getIdPartida());
        return carga;
    }
    
    /**
     * Datos de minas y vidas de un jugador
     * @param jugador
     * @param partida
     * @return 
     */
    public DatosTablero getVidasMinas(String jugador, String partida){
        DatosTablero datos = new DatosTablero();
        int vidas = 0;
        int minas = 0;
        int banderas = 0;
        boolean isVivo = true;
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getIdPartida().equals(partida)){
               //System.out.println("cargo partida");
               Partida p = partidas.get(i);
               Tablero t = p.getTablero();
               for(Jugador j : p.getJugadores()){
                   if(j.getNick().equals(jugador)){
                      vidas = j.getVidas();
                      if(vidas==0){
                          isVivo=false;
                      }
                   }
               }
               minas = p.getMinas();
               banderas = p.getBanderas();
            } 
        }
        datos.setMinas(minas);
        datos.setVidas(vidas);
        datos.setBanderas(banderas);
        if(isVivo==false){eliminaJugador(jugador, partida);}
        datos.setIsVivo(isVivo);
        return datos;
    }
    
    /**
     * Elimina un jugador con cero vidas
     * @param jugador
     * @param partida
     */
    public  void eliminaJugador(String jugador, String partida){
       boolean estado = true;
       Jugador jug = null;
       int index = 0;
       for(int i=0;i<partidas.size() && estado;i++){
           if(partidas.get(i).getIdPartida().equals(partida)){
               for(Jugador j : partidas.get(i).getJugadores()){
                   if(j.getNick().equals(jugador) && estado){
                       jug=j;
                       index = i;
                   }
               }
            } 
        } 
       if(jug!= null){
           partidas.get(index).getJugadores().remove(jug);
           System.out.println("eliminado con exito QQQQQQQQQQQ");
       }
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
    
    public Casilla[][] consultaCasilla(DatosSeleccion datos){
        Casilla[][] casilla=null;
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getIdPartida().equals(datos.getIdPartida())){
              casilla = partidas.get(i).getTablero().getCasillas();
            } 
        }
        return casilla;
    }
    
    /**
     *
     * @param datos
     * @param value
     * @return
     */
    public int consultaDatosPartida(DatosSeleccion datos, String value){
        int valor =0;
        for(int i=0;i<partidas.size();i++){
           if(partidas.get(i).getIdPartida().equals(datos.getIdPartida())){
              if(value == "filas"){
                  valor = partidas.get(i).getFilas();
              }
              if(value == "columnas"){
                  valor = partidas.get(i).getColumnas();
              }
             
            } 
        }
        return valor;
    }
    
    /**
     * Se agrega un jugador a una partida publica
     * @param nick
     * @param namePartida
     * @return true si este es agregado, false si no
     */
    public boolean agregarJugador(String nick,String idPartida){
        boolean agrego = false;
        for(int i=0;i<partidas.size();i++){
            //System.out.println(partidas.get(i).getNombre()+","+namePartida);
            if(partidas.get(i).getIdPartida().equals(idPartida)){
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
    
    /**
     * Indica la existencia de una partida en el juego.
     * 
     * @param clavePartida
     * @return true si existe la partida, false de lo contrario
     */
    public boolean buscaPartida(String clavePartida){
        boolean aparece = false;        
        for(Partida p : partidas){
            aparece = p.getIdPartida().equals(clavePartida);            
            if(aparece){
                if(p.getTipoPartida().equals("Publica")){
                    aparece = false;
                }
                break;
            }
        }
        return aparece;
    }    
}
