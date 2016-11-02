/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;
import java.util.HashMap;

/**
 *El desarrollo del tableor que contendra jugadores y casillas,
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
public class Tablero {
    
    private final int filas;
    private final int columnas;
    private final Casilla [][] casillas;
    private final String nivel;
    private final int minas;
    
    public Tablero(Integer filas, Integer columnas,String nivel,Integer minas){  
        this.filas=filas;
        this.columnas=columnas;
        this.nivel=nivel;
        this.minas=minas;
        casillas = new Casilla [filas][columnas];
    }
    
    /**
     * Inicializa una matrix de casillas vacias
     */
    public void llenarTablero(){
        for(int i=0;i<columnas;i++){
            for(int j=0;j<filas;j++){
                casillas[i][j]=new Casilla(); 
            }
        }
    } 
    
    public void agregarMinas(){
        int i=0;
        int j=0;
        int cont =0;
        do{
            i=(int) (Math.random()*filas);
            j=(int) (Math.random()*columnas);
            //casillas[i][j]
        }
        while (cont <= minas);
            
        }
}
    
   
    

