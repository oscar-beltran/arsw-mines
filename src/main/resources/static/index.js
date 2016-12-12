

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
 


