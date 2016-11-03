stompClient = null;


function salirPartida() {
    //Implementar lógica de desconexión
    alert("Saliendo...");
    window.location.replace("/index.html");
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
    stompClient.send("/app/crearJuego", {}, JSON.stringify({nombre:"Prueba",tipoPartida:"Publica",filas:7,columnas:7,numeroJugadores:3,modalidad:"Sencillo",tiempo:10,nivel:"facil",jugador:"Deivan"}));
    
}

//Prueba de agregar jugadores, "Prueba" es el identificador de la partida
function agregarJugador() {
    console.log("/juntosContraLasMinas/agregarJugador/"+"Prueba"); 
    $.get( "/juntosContraLasMinas/agregarJugador/"+"Prueba2"+"/"+"Anderson", 
        function( data ) {      
                alert(data);
        }    
    ).fail(
        function(data){
            alert("Problema");
        }
            
    );
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
    var canvasWidth = 560;
    var canvasHeight = 560;
    var p = 0;
    
    for (var x = 0; x <= canvasWidth; x += 80) {
        ctx.moveTo(0.5 + x + p, p);
        ctx.lineTo(0.5 + x + p, canvasHeight + p);
    }

    for (var x = 0; x <= canvasHeight; x += 80) {
        ctx.moveTo(p, 0.5 + x + p);
        ctx.lineTo(canvasWidth + p, 0.5 + x + p);
    }
    
    // tamaño del bloque
    var size = 80;
    // número de celdas en el lienzo
    var width = ~~ (canvas.width / size);
    var height = ~~ (canvas.height / size);

    // crear arreglo vacío de estados
    var state = new Array(height);
    for (var y = 0; y < height; ++y) {
        state[y] = new Array(width);
    }

    // manejo de eventos de clic en el mouse
    $(canvas).click(function(e) {

        // quick fill function to save repeating myself later
        function fill(s, gx, gy) {
            ctx.fillStyle = s;
            ctx.fillRect(gx * size, gy * size, size, size);
        }

        // posición del mouse
        var mouseX = e.offsetX;
        var mouseY = e.offsetY;
        
        //alert("X:"+mouseX+" Y:"+mouseY);
           
        // calcula la ubicación de la casilla
        var gx = ~~ (mouseX / size);
        var gy = ~~ (mouseY / size);
        // alert("X:"+gx+" Y:"+gy);

        // verifica que se esté dentro de los bordes
        if (gx < 0 || gx >= width || gy < 0 || gy >= height) {
            return;
        }

        if (state[gy][gx]) {
            // de presionarse previamente, se torna teporalmente rojo
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






//Aqui empiece a realizar conexiones..
