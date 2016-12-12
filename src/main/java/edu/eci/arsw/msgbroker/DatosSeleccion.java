/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

/**
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte93715
 */
public class DatosSeleccion {
    private String idPartida;
    private String nombre;
    private String jugador;
    private int posX;
    private int posY;
    
    public void DatosSeleccion(String idPartida,String nombre, String jugador, int posX, int posY) {
        this.idPartida=idPartida;
        this.nombre = nombre;
        this.jugador = jugador;
        this.posX = posX;
        this.posY = posY;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }
    
}
