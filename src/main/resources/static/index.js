

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
	alert("has hecho click en la imagen1");
	});
        $("img[name=imagen1]").click(function () {
	alert("has hecho click en la imagen2");
	});
        $("img[name=imagen2]").click(function () {
	alert("has hecho click en la imagen3");
	});
        $("img[name=imagen3]").click(function () {
	alert("has hecho click en la imagen4");
	});
        $("img[name=imagen4]").click(function () {
	alert("has hecho click en la imagen5");
	});
        $("img[name=imagen5]").click(function () {
	alert("has hecho click en la imagen6");
	});
        
});
 
  
