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
    
    @RequestMapping(path = "/crearJuego/{patidaId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCrearPartida(@PathVariable String partidaId){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
