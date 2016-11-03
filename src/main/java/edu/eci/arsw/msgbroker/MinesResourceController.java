/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.aplicacion.Juego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USUARIO
 */

@RestController
@RequestMapping("/juntosContraLasMinas")
public class MinesResourceController {
    
    @Autowired
    Juego juego;
    
    @RequestMapping(path = "/agregarJugador/{partidaId}/{nick}", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentWord(@PathVariable String partidaId,@PathVariable String nick){
        System.out.println("Partida:"+partidaId+", Jugador:"+nick);
        boolean estado;
        estado=juego.agregarJugador(nick,partidaId);
        return new ResponseEntity<>(estado,HttpStatus.ACCEPTED);
    }

    
}
