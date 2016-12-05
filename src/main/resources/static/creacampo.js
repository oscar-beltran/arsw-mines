function regresar() {
    window.location.replace("/index.html");
};

function guardar(){
    nombre=document.elegir.nombre.value;
    tamañotab=document.elegir.tamanotablero.value;
    numerojug=document.elegir.numberjuga.value;
    
    botones = document.elegir.tipodepartida;
    for (var i = 0; i < botones.length; i++) {
      booleano=botones[i].checked;
        if(booleano==true){
            opc1=botones[i].value;
        }  
    }
    
    botones1 = document.elegir.moddepartida;
    for (var i = 0; i < botones1.length; i++) {
      booleano=botones1[i].checked;
        if(booleano==true){
            opc2=botones1[i].value;
        }  
    }
    
    botones2 = document.elegir.nivdegame;
    for (var i = 0; i < botones2.length; i++) {
      booleano=botones2[i].checked;
        if(booleano==true){
            opc3=botones2[i].value;
        }  
    }
    
    alert("Los datos ingresados fueron: \n"+nombre+" "+tamañotab+" "+numerojug+" "+opc1+" "+opc2+" "+opc3);
}
window.onload = function() {
    document.elegir.ver.onclick = guardar;
}; 