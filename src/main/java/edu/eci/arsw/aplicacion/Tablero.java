/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.aplicacion;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;
import java.util.ArrayList;
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
    private final Casilla[][] casillas;
    private final String nivel;
    private final int minas;
    
    /**
     * Constructor de tableros
     * @param filas
     * @param columnas
     * @param nivel
     * @param minas 
     */
    public Tablero(Integer filas, Integer columnas,String nivel,Integer minas){  
        this.filas=filas;
        this.columnas=columnas;
        this.nivel=nivel;
        this.minas=minas;
        casillas = new Casilla[filas][columnas];
    }
    
    /**
     * Inicializa una matrix de casillas vacias
     */
    public void llenarTablero(){
        for(int i=0;i<columnas;i++){
            for(int j=0;j<filas;j++){
                casillas[i][j]=new Casilla("V");
                casillas[i][j].setPosicion(i, j);
            }
        }
    } 
    
    /**
     * Imprime casillas para verificacion
     */
    public void imprimirCasillas(){
        for(int i=0;i<columnas;i++){
            for(int j=0;j<filas;j++){
                System.out.print(casillas[i][j].getEstado()+" "); 
            }
            System.out.println();
        }    
    }
    
    /**
     * Agrega minas a un nuevo tablero
     */
    public void agregarMinas(){
        int i=0;
        int j=0;
        int cont =0;
        do{
            i=(int) (Math.random()*filas);
            j=(int) (Math.random()*columnas);
            if(i>=0 && j>=0 && i< filas && j < columnas){
                if(casillas[i][j].getEstado().equals("V")){
                    casillas[i][j].setEstado("B");
                    cont++;
                }
            }                   
        }
        while (cont < minas);
        }
    /**
     * Retorna numero de minas
     * @return 
     */
    public int getMinas(){
        return minas;
    }
    
    
    /**
     * Asigna algoritmo de numeros al tablero
     */
    public void asignarNumeros() {
        int cont;
        for(int i=0;i<columnas;i++){
            for(int j=0;j<filas;j++){
                cont =0;
                if(casillas[i][j].getEstado().equals("V")){
                    
                    if(verifica(i-1,j)){
                        if(casillas[i-1][j].getEstado().equals("B"))cont++;
                    }
                    if(verifica(i+1,j)){
                        if(casillas[i+1][j].getEstado().equals("B"))cont++;
                    }
                    if(verifica(i,j-1)){
                        if(casillas[i][j-1].getEstado().equals("B"))cont++;
                    }
                    if(verifica(i,j+1)){
                        if(casillas[i][j+1].getEstado().equals("B"))cont++;
                    }
                    if(verifica(i-1,j-1)){
                        if(casillas[i-1][j-1].getEstado().equals("B"))cont++;
                    }
                    if(verifica(i-1,j+1)){
                        if(casillas[i-1][j+1].getEstado().equals("B"))cont++;
                    }
                    if(verifica(i+1,j-1)){
                        if(casillas[i+1][j-1].getEstado().equals("B"))cont++;
                    }                    
                    if(verifica(i+1,j+1)){
                        if(casillas[i+1][j+1].getEstado().equals("B"))cont++;
                    }
                    if(cont != 0)casillas[i][j].setEstado(""+cont);
                }
            }
        }  
        imprimirCasillas();
    }
    /**
     * Verifica el estado de casillas para no salir de los limites
     * @param i
     * @param j
     * @return 
     */
    private boolean verifica(Integer i, Integer j){
            return (i>=0 && j>=0 && i<filas && j < columnas);
    }
       
    
    public Casilla getCasilla(String color, Integer posX, Integer posY){
        Casilla casilla = null;
        if(!casillas[posX][posY].isActiva()){
           casillas[posX][posY].setActiva(true);
           casillas[posX][posY].setColor(color);
        }  
        casilla = casillas[posX][posY];
        return casilla;
    }
    
    public boolean isCasilla(String color, Integer posX, Integer posY){
        boolean casilla = true;
        if(!casillas[posX][posY].isActiva()){
           casilla=casillas[posX][posY].isActiva();
        }  
        return casilla;
    }
}
    
   
    

