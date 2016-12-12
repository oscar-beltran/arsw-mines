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
    private int minas;
    private int banderas;
    private boolean isVivo;
    
    public void DatosTablero(Integer vidas, Integer minas, Integer banderas,boolean isVivo){
          this.minas=minas;
          this.vidas=vidas;
          this.banderas=banderas;
          this.isVivo=isVivo;
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
    
    

    public int getBanderas() {
        return banderas;
    }

    public void setBanderas(int banderas) {
        this.banderas = banderas;
    }
    
    

    public boolean isIsVivo() {
        return isVivo;
    }

    public void setIsVivo(boolean isVivo) {
        this.isVivo = isVivo;
    }
    
        
    
    
    
}
