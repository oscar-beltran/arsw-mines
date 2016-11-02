var stompClient = null;

function salirPartida() {
    //Implementar lógica de desconexión
    alert("Saliendo");
    window.location.replace("/index.html");
    
};

function dibujaBordeCasillas() {
    var canvas = $("#tablero");
    var ctx = canvas.getContext("2d");

    for(i=0;i<8;i++)
    {for(j=0;j<8;j++)
    {ctx.moveTo(0,70*j);
    ctx.lineTo(560,70*j);
    ctx.stroke();

    ctx.moveTo(70*i,0);
    ctx.lineTo(70*i,560);
    ctx.stroke();
    var left = 0;
    for(var a=0;a<8;a++) {
        for(var b=0; b<8;b+=2) {
          startX = b * 70;
          if(a%2===0) startX = (b+1) * 70;
          ctx.fillRect(startX + left,(a*70) ,70,70);
            }}
    }}
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


$(document).ready(
    function () {
        dibujaBordeCasillas();
    }
);


