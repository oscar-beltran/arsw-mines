/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

/**
 *El desarrollo del tableor que contendra jugadores y casillas,
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
public class Tablero {
    
    private final int filas;
    private final int columnas;
    private final Casilla[][] casillas;
    private final String dificultad;
    
    public Tablero(Integer filas, Integer Columnas,String dificultad){
        this.filas=filas;
        this.columnas=Columnas;
        this.casillas=new Casilla[filas][columnas];
        this.dificultad=dificultad;
}
    
}
