stompClient = null;
canvas = null;
ctx = null;
canvasWidth = 560;
canvasHeight = 560;
// tamaño del bloque
size = null;
// número de celdas en el lienzo
width = null;
height = null;
p = 0;
nick = null;

//Funciones de redirección
function salirPartida() {
    //Implementar lógica de desconexión
    //alert("Saliendo...");
    disconnect();
    window.location.replace("/index.html");
};

// Funciones de conexión
function connect(partidaId) {
    var socket = new SockJS('/stomMines');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        crearPartida();
        stompClient.subscribe('/topic/patidaCreada'+partidaId, function (data) {
            var newPartida = JSON.parse(data.body);
            //alert(newPartida.nombre);
            //alert(newPartida.filas);
            //alert(newPartida.columnas);
            canvas = document.getElementById("tablero");
            ctx = canvas.getContext('2d');
            size = canvasWidth/newPartida.filas;
            width = ~~ (canvas.width / size);
            height = ~~ (canvas.height / size);
            
            manejoEventos();
            drawBoard();

        });
        
        stompClient.subscribe('/topic/descubrirCasilla'+partidaId, function (data) {
            var casilla = JSON.parse(data.body);
            alert("sdfdsf");
        });
        
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

//Pruebas de integridad parte logica
function crearPartida() {
    stompClient.send("/app/crearJuego", {}, JSON.stringify({nombre:"Prueba",tipoPartida:"Publica",filas:8,columnas:8,numeroJugadores:3,modalidad:"Sencillo",tiempo:10,nivel:"facil",jugador:"Deivan"}));
    
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

//Prueba de agregar movimiento a una partida
function descubrirCasilla(posX, posY) {
      stompClient.send("/app/descubrirCasilla", {}, JSON.stringify({nombre:"Prueba",jugador:"Deivan",posX:posX,posY:posY}));
}

// Dibuja las líneas de la grilla sobre el canvas
function drawBoard(){
    
    for (var x = 0; x <= canvasWidth; x += size) {
        ctx.moveTo(0.5 + x + p, p);
        ctx.lineTo(0.5 + x + p, canvasHeight + p);
    }
    for (var x = 0; x <= canvasHeight; x += size) {
        ctx.moveTo(p, 0.5 + x + p);
        ctx.lineTo(canvasWidth + p, 0.5 + x + p);
    }
    ctx.strokeStyle = "black";
    ctx.stroke();
}

// manejo de eventos de clic en el mouse
function manejoEventos(){
        $('#tablero').mousedown(function(event) {
        
        // posición del mouse
        var mouseX = event.offsetX;
        var mouseY = event.offsetY;
        // calcula la ubicación de la casilla
        var gx = ~~ (mouseX / size);
        var gy = ~~ (mouseY / size);
        //alert("x: "+gx+" gy: "+gy);
        
        switch (event.which) {
            case 1:
                descubrirCasilla(gx,gy)                
                fill('black', gx, gy);
                fillText('1','red', gx, gy);
                break;
            case 2:
                break;
            case 3:
                fill('#00ff00', gx, gy)
                break;
            default:
                alert('You have a strange Mouse!');
        }
    });
}

// Colorea casilla
function fill(s, gx, gy) {
    ctx.fillStyle = s;
    ctx.fillRect(gx * size, gy * size, size, size);
}
// Agrega texto a casilla
function fillText(numero, color, gx, gy){
    ctx.fillStyle = color;
    ctx.font = 0.5*size+"px Georgia";
    ctx.fillText(numero, gx*size + size/3, gy*size + 2*size/3);
}

$(document).ready(
    function () {
        connect("Prueba");
    }
);


