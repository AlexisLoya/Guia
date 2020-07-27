function showDatos() {
    document.getElementById('editarInformacion').style.display = "block";
}


function ocultarDatos() {
    alert("llega");
    document.getElementById('editarInformacion').style.display = "none";
}


function guardarDatos() {
    alert("Datos modificados");
    document.cambiarDatos.submit();
}