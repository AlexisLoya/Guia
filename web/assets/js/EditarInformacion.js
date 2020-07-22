function cambiarDatos() {
    var count = 0;
    if (count == 0) {
        count++;
        document.getElementById('editarInformacion').style.display = "block";
        console.log(count);
        console.log("llega");
    } else {
        document.getElementById('editarInformacion').style.display = "none";
        count == 0;
        console.log("oculta");

    }
}