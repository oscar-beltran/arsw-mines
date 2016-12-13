

/* global Location */

function descrearCampo() {
    value = validarNickname();
    if(!value){
        window.location.replace("/Crearcampo.html"+"?"+nick);   
    }
}
function desCampoprivado() {
    value = validarNickname();
    if(!value){
        window.location.replace("/Campoprivado.html"+"?"+nick);
    }
}
function desCampopublico() {
    value = validarNickname();
    if(!value){
        window.location.replace("/Campopublico.html"+"?"+nick);
    }   
}
function prueba() {
    window.location.replace("/partida.html");
}

function regresar(){
    alert("Saliendo...");
    window.location.replace("/index.html");
}

function refIndex(){
    window.location.replace("index.html");
}

function validarNickname(){
    nick=document.getElementById("avatar").value;
    if(nick===""){
        alert("Ingresa Nick");
        return true;
    }
    else{
        return false;
    }
}
 
$(document).ready(function(){
	$("img[name=imagen]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara1.png";
            localStorage.setItem("Imagen", "/imagenes/cara1.png");
	});
        $("img[name=imagen1]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara2.png";
            localStorage.setItem("Imagen", "/imagenes/cara2.png");
	});
        $("img[name=imagen2]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara3.jpg";
            localStorage.setItem("Imagen", "/imagenes/cara3.jpg");
	});
        $("img[name=imagen3]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara4.png";
            localStorage.setItem("Imagen", "/imagenes/cara4.png");
	});
        $("img[name=imagen4]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara5.jpg";
            localStorage.setItem("Imagen", "/imagenes/cara5.jpg");
	});
        $("img[name=imagen5]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara6.jpg";
            localStorage.setItem("Imagen", "/imagenes/cara6.jpg");
	});
        
});

$(document).ready(iniciar)(
            function iniciar(){
                $("tabla tr td").click(clickTabla);
            }
        );
 

