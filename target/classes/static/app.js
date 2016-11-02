stompClient = null;


function salirPartida() {
    //Implementar lógica de desconexión
    //alert("Saliendo");
    //window.location.replace("/index.html");
    drawBoard();
};

function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        
        /*stompClient.subscribe('/topic/XXXXTOPIC', function () {
            //subscriber action
            
        });*/
        
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function drawBoard(){
    
    var canvas = document.getElementById("tablero");
    var ctx = canvas.getContext('2d');
    var bw = 560;
    var bh = 560;
    var p = 0;
    
    
    for (var x = 0; x <= bw; x += 80) {
        ctx.moveTo(0.5 + x + p, p);
        ctx.lineTo(0.5 + x + p, bh + p);
    }

    for (var x = 0; x <= bh; x += 80) {
        ctx.moveTo(p, 0.5 + x + p);
        ctx.lineTo(bw + p, 0.5 + x + p);
    }

    ctx.strokeStyle = "black";
    ctx.stroke();
}

$(document).ready(
    function () {
        drawBoard();
    }
);