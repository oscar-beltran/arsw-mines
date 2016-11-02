/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import java.util.ArrayList;

/**
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
public class Partida {
    /**
     * Atributos asignados a la creacion por el usuario
     */
    private String nombre;
    private String tipoPartida;
    private int filas;
    private int columnas;
    private int numeroJugadores;
    private String modalidad;
    private double tiempo;
    private String nivel;
    /**
     * Atributos calculados por la aplicacion
     */
    private Tablero tablero;
    private int minas;
    private int banderas;
    private int maxJugadores = 10;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    
    public Partida(String nombre,String tipoPartida, Integer filas, Integer columnas, Integer numeroJugadores,String modalidad, double tiempo,String nivel){
       this.nombre=nombre;
       this.tipoPartida=tipoPartida;
       this.filas=filas;
       this.columnas=columnas;
       this.numeroJugadores=numeroJugadores;
       this.modalidad=modalidad;
       this.tiempo=tiempo;
       this.nivel=nivel;
    }
    
    /**
     * Se agrega un nuevo jugador a la partida si no ha excedido el numero maximo
     * @param jugador
     */
    public boolean setJugador(Jugador jugador){
        boolean agrega = false;
        if(numeroJugadores<maxJugadores){
            jugadores.add(jugador);
            agrega = true;
        }
        return agrega;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    /**
    *Se solicita tablero de una partida
     * @return 
    */
    public Tablero getTablero() {
        return tablero;
    }
    /**
    *Se agrega tablero a una partida
    * @param tablero
    */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    /**
    *Se solicita tablero de una partida
    * @return 
    */
    public int getMinas() {
        return minas;
    }

    /**
    *Se agregan minas a una partida
    *@param minas 
    */
    public void setMinas(int minas) {
        this.minas = minas;
    }

    /**
    *Se solicitan numero de banderas de una  partida 
     * @return 
    */
    public int getBanderas() {
        return banderas;
    }
    
    /**
    *Se agregan banderas a una partida
    *@param banderas
    */
    public void setBanderas(int banderas) {
        this.banderas = banderas;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public void setMaxJugadores(int maxJugadores) {
        this.maxJugadores = maxJugadores;
    }
    
    public String getId() {
        return nombre;
    }

    public void setId(String id) {
        this.nombre = id;
    }
}
