/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

/**
 * Esta clase solo esta encargada de recibir los datos basicos para la de creacion de una partida
 * @author Deivan Oliva
 * @author Oscar Beltran
 * @author Sergio Aponte
 */
public class DatosCarga {
    private String idPartida;
    private String jugador;
    
    public void DatosCarga(String idPartida, String jugador){
        this.idPartida=idPartida;
        this.jugador=jugador;
    }
      

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
}
