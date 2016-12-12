/* global Stomp */

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
mouseX = null;
mouseY = null;
gX = null;
gY = null;

//Funciones de redirección
function salirPartida() {
    //Implementar lógica de desconexión
    //alert("Saliendo...");
    disconnect();
    window.location.replace("/index.html");
};

// Funciones de conexión
function connect() {
    var socket = new SockJS('/stomMines');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame); 
        //crearPartida();
        var paramstr = window.location.search.substr(1);
        var paramarr = paramstr.split ("&");
        partidaId=paramarr[0];
        nick=paramarr[1];
        stompClient.subscribe('/topic/patidaCreada'+partidaId, function (data) {
            alert("llegue Nuevo");
            var newPartida = JSON.parse(data.body);
            tipoPartida=newPartida.tipoPartida;
            document.getElementById("nUsuario").innerHTML =newPartida.jugador;
            if(tipoPartida==="Publica"){
                document.getElementById("nPartida").innerHTML ="Pública";
            }            
            else{
                document.getElementById("nPartida").innerHTML =partidaId;            
            }
            canvas = document.getElementById("tablero");
            ctx = canvas.getContext('2d');
            size = canvasWidth/newPartida.filas;
            width = ~~ (canvas.width / size);
            height = ~~ (canvas.height / size);
            manejoEventos();
            drawBoard();

        });
        
        stompClient.subscribe('/topic/casillaSeleccionada'+partidaId, function (data) {
            var casilla = JSON.parse(data.body);
            var estado = casilla.estado;
            var color = casilla.color;
            var posX = casilla.posX;
            var posY = casilla.posY;
            //alert(casilla.estado);
            
            cargaCasilla(estado, color, posX, posY);
            //fill('black', gx, gy);
            //fillText('1','red', gx, gy);
        });
        
        cargaPartida();
        
    });
}

function cargaCasilla(estado ,color, posX, posY){
    switch (estado){
        case 'V':
            fill(color, posX, posY);
            break;
        case 'B':
            fill('red', posX, posY);
            break;
        case '1':
            fill(color, posX, posY);
            fillText(estado,'blue', posX, posY);
            break;
        case '2':
            fill(color, posX, posY);
            fillText(estado,'green', posX, posY);
            break;
        case '3':
            fill(color, posX, posY);
            fillText(estado,'yellow', posX, posY);
            break;
        case '4':
            fill(color, posX, posY);
            fillText(estado,'purple', posX, posY);
            break;
        case '5':
            fill(color, posX, posY);
            fillText(estado,'red', posX, posY);
            break;
        case '6':
            fill(color, posX, posY);
            fillText(estado,'blue', posX, posY);
            break;
        case '7':
            fill(color, posX, posY);
            fillText(estado,'gray', posX, posY);
            break;
        case '8':
            fill(color, posX, posY);
            fillText(estado,'black', posX, posY);
            break;
        default:
            alert("Error increíblemente extraño, perdón las molestias orz");
            break;
    }      
}

function pruebaConexion(){
    var paramstr = window.location.search.substr(1);
    var paramarr = paramstr.split ("&");
    partidaId=paramarr[0];
    nick=paramarr[1];   
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

//Pruebas de integridad parte logica
function crearPartida() {
    partidaId="minas12345";
    stompClient.send("/app/Nuevo-Juego", {}, JSON.stringify({idPartida:"minas12345",nombre:"Prueba",tipoPartida:"Publica",filas:15,columnas:15,numeroJugadores:3,modalidad:"Sencillo",tiempo:10,nivel:"facil",jugador:"Deivan"}));
    
}

//Carga una partida creada
function cargaPartida(){
    stompClient.send("/app/cargarPartida", {}, JSON.stringify({idPartida:partidaId,jugador:nick}));
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
      stompClient.send("/app/Casilla", {}, JSON.stringify({idPartida:partidaId,jugador:nick,posX:posX,posY:posY}));
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
        mouseX = event.offsetX;
        mouseY = event.offsetY;
        // calcula la ubicación de la casilla
        gx = ~~ (mouseX / size);
        gy = ~~ (mouseY / size);
        //alert("x: "+gx+" gy: "+gy);
        
        switch (event.which) {
            case 1:
                descubrirCasilla(gx,gy)                
                //fill('black', gx, gy);
                //fillText('1','red', gx, gy);
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

function refIndex(){
    alert(llegue);
    window.location.replace("index.html");
}

function regresar() {
    window.location.replace("/index.html");
}

$(document).ready(
      
    function () {
        connect();
        
    }
);