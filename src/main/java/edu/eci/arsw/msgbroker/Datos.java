/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.aplicacion.Partida;

/**
 * Esta clase solo esta encargada de recibir los datos de creacion de una partida
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
public class Datos {
    private String idPartida;
    private String nombre;
    private String tipoPartida;
    private int filas;
    private int columnas;
    private int numeroJugadores;
    private String modalidad;
    private double tiempo;
    private String nivel;
    private String jugador;
    private boolean estado;
    
    public void Datos(String idPartida,String nombre,String tipoPartida,Integer filas, Integer columnas,Integer numeroJugadores,String modalidad, double tiempo,String nivel,String jugador){
       this.idPartida=idPartida;
       this.nombre=nombre;
       this.tipoPartida=tipoPartida;
       this.filas=filas;
       this.columnas=columnas;
       this.numeroJugadores=numeroJugadores;
       this.modalidad=modalidad;
       this.tiempo=tiempo;
       this.nivel=nivel;
       this.jugador=jugador;               
    }
    
       
    public Partida getNuevaPartida(){
        Partida partida = new Partida(idPartida,nombre, tipoPartida, filas, columnas, numeroJugadores, modalidad, tiempo, nivel);
        return partida;
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
    

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
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
    
    
    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
    
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }
}
