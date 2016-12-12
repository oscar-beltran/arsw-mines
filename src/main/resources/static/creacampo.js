/* global Stomp, canvasWidth */

// Funciones de conexión

stompClient = null;

function connect(partidaId) {
    socket = new SockJS('/stomMines');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);       
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
    if (nombre==""){
        alert("Ingresa nombre partida");
        estado=false;
    }
    var radios = document.getElementsByName("tipodepartida");    
    for(var i = 0; i < radios.length; i++) {
        if(radios[i].checked) tipoPartida = radios[i].value;   
    }
    filas=document.elegir.filas.value;
    columnas=document.elegir.columnas.value;
       
    jugadores=document.elegir.numberjuga.value;
    
    var radios = document.getElementsByName("moddepartida");    
    for(var i = 0; i < radios.length; i++) {
        if(radios[i].checked) modalidadJuego = radios[i].value;   
    }
    
    var radios = document.getElementsByName("nivdegame");    
    for(var i = 0; i < radios.length; i++) {
        if(radios[i].checked) nivelJuego = radios[i].value;   
    } 
    
    usuario=localStorage.getItem("usuario");
    if(usuario==""){
        usuario="Incognito";
    }
    alert(idPartida);
    if(estado){
        alert("creando partida");
        stompClient.send("/app/crearJuego", {}, JSON.stringify({nombre:nombre,tipoPartida:tipoPartida,filas:filas,columnas:columnas,numeroJugadores:jugadores,modalidad:modalidadJuego,tiempo:10000,nivel:nivelJuego,jugador:usuario}));
    }
}

$(document).ready(
      
    function () {
        idPartida= "minas"+Math.floor((Math.random() * 9534) + 1000);   
        connect(idPartida);
    }
);

