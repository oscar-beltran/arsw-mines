stompClient = null;


function salirPartida() {
    //Implementar lógica de desconexión
    //alert("Saliendo");
    //window.location.replace("/index.html");
    drawBoard();
};

function connect() {
    var socket = new SockJS('/stomMines');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        console.log('Connected: ' + frame);
        
        /*stompClient.subscribe('/topic/XXXXTOPIC', function () {
            //subscriber action
            
        });*/
        
    });
}


//Pruebas de integridad parte logica
function crearPartida() {
    alert("Lllegada...");
    stompClient.send("/app/crearJuego", {}, JSON.stringify({nombre:"Prueba"}));
    
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
    
    // tamaño del bloque
    var size = 80;
    // número de celdas en el canvas
    var w = ~~ (canvas.width / size);
    var h = ~~ (canvas.height / size);

    // create empty state array
    var state = new Array(h);
    for (var y = 0; y < h; ++y) {
        state[y] = new Array(w);
    }

    // click event, using jQuery for cross-browser convenience
    $(canvas).click(function(e) {

        // quick fill function to save repeating myself later
        function fill(s, gx, gy) {
            ctx.fillStyle = s;
            ctx.fillRect(gx * size, gy * size, size, size);
        }

        // get mouse click position
        var mx = e.offsetX;
        var my = e.offsetY;

        // calculate grid square numbers
        var gx = ~~ (mx / size);
        var gy = ~~ (my / size);

        // make sure we're in bounds
        if (gx < 0 || gx >= w || gy < 0 || gy >= h) {
            return;
        }

        if (state[gy][gx]) {
            // if pressed before, flash red
            fill('red', gx, gy);
            setTimeout(function() {
                fill('black', gx, gy)
            }, 1000);
        } else {
            state[gy][gx] = true;
            fill('black', gx, gy);
        }
    });

    ctx.strokeStyle = "black";
    ctx.stroke();
}

$(document).ready(
    function () {
        connect();
        drawBoard();
    }
);