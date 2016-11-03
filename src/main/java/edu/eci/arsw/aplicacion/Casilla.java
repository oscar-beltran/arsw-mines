/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

/**
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
public class Casilla {

    private String estado;
    private String color;
    private int numero;
    private int posX;
    private int posY;
    private boolean activa;

    
    /**
     * Se agregan estados a la casilla
     * @param estado V= vacio, B = bomba  
     */
    public Casilla(String estado){
        this.estado=estado; 
        this.activa=false;
    }

    /**
     * Se agrega la posicion de las casillas
     * @param posX
     * @param posY 
     */
    public void setPosicion(Integer posX, Integer posY){
        this.posX=posX;
        this.posY=posY;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
