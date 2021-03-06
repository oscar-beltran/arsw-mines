/* global Stomp, canvasWidth */

// Funciones de conexión

stompClient = null;

function connect(partidaId) {
    socket = new SockJS('/stomMines');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);    
        nick=window.location.search.substr(1);
            stompClient.subscribe('/topic/patidaCreada'+partidaId+nick, function (data) {
                window.location.replace("/partida.html"+"?"+idPartida+"&"+nick);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function regresar() {
    window.location.replace("/index.html");
}

function crear(){
    nombre=document.elegir.nombre.value;
    estado=true;
    if (nombre===""){
        alert("Ingresa nombre partida");
        estado=false;
    }
    var radios = document.getElementsByName("tipodepartida");    
    for(var i = 0; i < radios.length; i++) {
        if(radios[i].checked) tipoPartida = radios[i].value;   
    }
    filas=document.elegir.filas.value;
    columnas=document.elegir.filas.value;
       
    jugadores=document.elegir.numberjuga.value;
    
    var radios = document.getElementsByName("moddepartida");    
    for(var i = 0; i < radios.length; i++) {
        if(radios[i].checked) modalidadJuego = radios[i].value;   
    }
    
    var radios = document.getElementsByName("nivdegame");    
    for(var i = 0; i < radios.length; i++) {
        if(radios[i].checked) nivelJuego = radios[i].value;   
    } 
    nuevoUsuario=window.location.search.substr(1); 
    if(nuevoUsuario===""){
        nuevoUsuario="Incognito";
    }
    if(estado){
        stompClient.send("/app/crearJuego", {}, JSON.stringify({idPartida:idPartida,nombre:nombre,tipoPartida:tipoPartida,filas:filas,columnas:columnas,numeroJugadores:jugadores,modalidad:modalidadJuego,tiempo:10000,nivel:nivelJuego,jugador:nuevoUsuario}));
        //disconnect();
    }
}

$(document).ready(
      
    function () {
        idPartida= "minas"+Math.floor((Math.random() * 9534) + 1000);   
        connect(idPartida);
    }
);

