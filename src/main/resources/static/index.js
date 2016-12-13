


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

function cargaImagenUser(){
    document.getElementById('caraUser').src = localStorage.getItem("Imagen");
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
            document.getElementById('caraUser').src = "/imagenes/cara3.png";
            localStorage.setItem("Imagen", "/imagenes/cara3.png");
	});
        $("img[name=imagen3]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara4.png";
            localStorage.setItem("Imagen", "/imagenes/cara4.png");
	});
        $("img[name=imagen4]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara5.png";
            localStorage.setItem("Imagen", "/imagenes/cara5.png");
	});
        $("img[name=imagen5]").click(function () {
            document.getElementById('caraUser').src = "/imagenes/cara6.png";
            localStorage.setItem("Imagen", "/imagenes/cara6.png");
	});
        
});

function myFunction(x)
{
if (document.getElementsByClassName("trselected").length > 0) {
	var element = document.getElementsByClassName("trselected");
	if (parseInt(element[0].id)%2 !== 0) element[0].className = "impar";
	else element[0].className = "";
}
x.className="trselected";
}

function myFunction2(evnt)
{
var ev = (evnt) ? evnt : event;
   var code=(ev.which) ? ev.which : event.keyCode;
   if (code == 40) {
		if (document.getElementsByClassName("trselected").length > 0) {
			var element = document.getElementsByClassName("trselected");
			var num = (parseInt(element[0].id) + 1).toString();
			myFunction(document.getElementById(num));
		}
		else myFunction(document.getElementById(1))
   }
   else if (code == 38) {
		if (document.getElementsByClassName("trselected").length > 0) {
			var element = document.getElementsByClassName("trselected");
			var num = (parseInt(element[0].id) - 1).toString();
			myFunction(document.getElementById(num))
		}
		else myFunction(document.getElementById(document.getElementById("tbody").rows.length.toString()))
    }
    else if (code == 13 ) {
        if (document.getElementsByClassName("trselected").length > 0) {
	    var element = document.getElementsByClassName("trselected");
	    var info = element[0].cells[0].innerText;
	    info += " "+element[0].cells[1].innerText;
	    info += " "+element[0].cells[2].innerText;
	    info += " "+element[0].cells[3].innerText;
	    info += " "+element[0].cells[4].innerText;
	    alert(info);
	}
 
}
}
//Escucha y reacciona el evento cuando se pulsa una tecla
if (window.document.addEventListener) {
   window.document.addEventListener("keydown", myFunction2, false);
} else {
   window.document.attachEvent("onkeydown", myFunction2);
}


