/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import edu.eci.arsw.msgbroker.Datos;
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
    private String idPartida;
    private String nombre;
    private String tipoPartida;
    private int filas;
    private int columnas;
    private int numeroJugadores=0;
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
    private ArrayList<String> colores = new ArrayList<String>();
    
    public Partida(String idPartida, String nombre,String tipoPartida, Integer filas, Integer columnas, Integer numeroJugadores,String modalidad, double tiempo,String nivel){
       this.idPartida=idPartida;
       this.nombre=nombre;
       this.tipoPartida=tipoPartida;
       this.filas=filas;
       this.columnas=columnas;
       this.maxJugadores=numeroJugadores;
       this.modalidad=modalidad;
       this.tiempo=tiempo;
       this.nivel=nivel;
    }
    
    /**
     * Inicializa los otros valores
     */
    public void inicializar(){
        int numCasillas = filas * columnas;
        minas = (int) numCasillas/calculaNivel();
        System.out.println(minas);
        banderas = minas;
        llenarColores();
        poblartablero();
    }
    
    private int calculaNivel(){
        int porcentajeMinas = 1;
        System.out.println("dificultad:"+nivel);
        if(nivel.equals("Dificil")){
            System.out.println("minas dificiles");
            porcentajeMinas=3;
        }
        if(nivel.equals("Medio")){
            porcentajeMinas=6;
        }
        if(nivel.equals("Facil")){
            porcentajeMinas=10;
        }
        return porcentajeMinas;
    }
    
    /**
     * Se agregan colores para cada uno de los nuevos jugadores
     */
    private void llenarColores(){
        colores.add("#00FF00");
        colores.add("#FF00FF");
        colores.add("#800000");
        colores.add("#0000CD");
        colores.add("#9370DB");
        colores.add("#3CB371");
        colores.add("#FFE4B5");
        colores.add("#808000");
        colores.add("#FFA500");
        colores.add("#FFFF00");
    }
    /**
     * Se agrega un nuevo jugador a la partida si no ha excedido el numero maximo
     * @param jugador
     */
    public boolean setJugador(String nick){
        boolean agrega = false;
        if(numeroJugadores<maxJugadores){
            Jugador jugador = new Jugador(nick,colores.get(jugadores.size()));
            jugadores.add(jugador);
            agrega = true;
            System.out.println("Jugador :"+jugador.getNick()+" Agregado, Color:"+jugador.getColor());
        }
        return agrega;
    }
    
    /**
     * Se realiza el poblado de un nuevo tablero
     */
    public void poblartablero(){
        tablero = new Tablero(filas, columnas, nivel, minas);
        tablero.llenarTablero();
        tablero.agregarMinas();
        tablero.asignarNumeros();
    }
    
    
    
    public Casilla mover(String jugador,Integer posX, Integer posY){
            String color="";
            Casilla casilla = null;
            for(int i=0;i<jugadores.size();i++){
                if(jugadores.get(i).getNick().equals(jugador)){
                    color=jugadores.get(i).getColor();
                    casilla = tablero.getCasilla(color,posX,posY);
                    if(casilla.getEstado().equals("B")){

                        jugadores.get(i).setVidas(jugadores.get(i).getVidas()-1);
                        minas=minas-1;
                    }
                }
            } 
            return casilla;
    }
    
    
    /**
     * Se envia los jugadores 
     * @return jugadores
     */
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
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPartida() {
        return tipoPartida;
    }

    public void setTipoPartida(String tipoPartida) {
        this.tipoPartida = tipoPartida;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }
}
