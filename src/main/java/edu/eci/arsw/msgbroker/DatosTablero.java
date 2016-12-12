/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

/**
 *
 * @author USUARIO
 */
public class DatosTablero {
  
    private int vidas;
    private int  minas;
    
    public void DatosTablero(Integer vidas, Integer minas){
          this.minas=minas;
          this.vidas=vidas;
    }
    
    

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getMinas() {
        return minas;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }

        
    
    
    
}
