

/* global Location */

function descrearCampo() {
    window.location.replace("/Crearcampo.html");   
};
function desCampoprivado() {
    window.location.replace("/Campoprivado.html");

};
function desCampopublico() {
    window.location.replace("/Campopublico.html");
   
};
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
    var x =document.forms["validacion"]["nick"].value;
    alert(x);
    localStorage.setItem("usuario",x);
    document.getElementById("usuario").innerHTML = localStorage.getItem("usuario");
}


