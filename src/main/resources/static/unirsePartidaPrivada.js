stompClient = null;

function connect() {
    socket = new SockJS('/stomMines');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);  
        var paramstr = window.location.search.substr(1);
        var paramarr = paramstr.split ("&");
        jugador = paramarr[0];
            stompClient.subscribe('/topic/mensaje.' + jugador, function (data) {                
                alert(data.body);
            });
            stompClient.subscribe('/topic/respuesta.' + jugador, function (data) {                
                var obj = JSON.parse(data.body);                
                window.location.replace("/partida.html"+"?"+obj.idPartida+"&"+obj.jugador);
                //disconnect();
            });
    });    
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function validar(){
    var paramstr = window.location.search.substr(1);
    var paramarr = paramstr.split ("&");
    jugador = paramarr[0];
    clave = document.getElementById('codigoUnirse').value;
    alert("Ingresaste los datos: Jugador-> "+jugador+",Partida: "+clave);
    stompClient.send("/app/ValidarCodigo", {}, JSON.stringify({idPartida:clave, jugador:jugador}));
}

$(document).ready(      
    function () { 
        connect();
    }
);