//////////////////////Docente//////////////////////

//Validaciones para el registro de un alumno
function registroDocente(string){ 
    let nombre = document.getElementById("nombre").value;
    let paterno = document.getElementById("paterno").value;
    let materno = document.getElementById("materno").value;
    let email = document.getElementById("email").value;
    let codigo = document.getElementById("codigo").value;
    let contr = document.getElementById("password").value;
    let contrarepeat = document.getElementById("password_repeat").value;
    let hombre = document.getElementById("masculino").checked;
    let mujer = document.getElementById("femenino").checked;
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ_.@" ';//Caracteres validos

    email = email.substring((email.length-11),email.length);

    let out = '';
    for (var i=0; i<string.length; i++){
        if (filtro.indexOf(string.charAt(i)) != -1){
            out += string.charAt(i);
        }
    }
    if(nombre.length>0 && paterno.length>0 && materno.length>0 && email.length>0 && codigo.length>0 
        && contr.length>0 && contrarepeat.length>0 && (hombre == true || mujer == true) && (contr == contrarepeat)){

        //Eliminar espacios
        nombre = nombre.trim();
        paterno = paterno.trim();
        materno = materno.trim();
        email = email.trim();
        codigo = codigo.trim();
        contr = contr.trim();
        contrarepeat = contrarepeat.trim();
        email = email.toLowerCase();

        //Cambiar la primera a mayuscula
        nombre = nombre[0].toUpperCase() + nombre.slice(1);
        paterno = paterno[0].toUpperCase() + paterno.slice(1);
        materno = materno[0].toUpperCase() + materno.slice(1);
        
        document.getElementById("btnRegistrar").disabled = false;
    }else{
        document.getElementById("btnRegistrar").disabled = true;
    }
    return out;
}

function check(){//Valida la pagina cuando se seleccione el sexo
    let nombre = document.getElementById("nombre").value;
    let paterno = document.getElementById("paterno").value;
    let materno = document.getElementById("materno").value;
    let email = document.getElementById("email").value;
    let codigo = document.getElementById("codigo").value;
    let contr = document.getElementById("password").value;
    let contrarepeat = document.getElementById("password_repeat").value;
    let hombre = document.getElementById("masculino").checked;
    let mujer = document.getElementById("femenino").checked;

    email = email.substring((email.length-11),email.length);

    //Eliminar espacios
    nombre = nombre.trim();
    paterno = paterno.trim();
    materno = materno.trim();
    email = email.trim();
    codigo = codigo.trim();
    contr = contr.trim();
    contrarepeat = contrarepeat.trim();

    //Cambiar la primera a mayuscula
    nombre = nombre[0].toUpperCase() + nombre.slice(1);
    paterno = paterno[0].toUpperCase() + paterno.slice(1);
    materno = materno[0].toUpperCase() + materno.slice(1);

    //Cambiar todo a minuscula
    email = email.toLowerCase();

    if(nombre.length>0 && paterno.length>0 && materno.length>0 && email.length>0 && codigo.length>0 
        && contr.length>0 && contrarepeat.length>0 && (hombre == true || mujer == true)){
        document.getElementById("btn").disabled = false;
        
    }else{
        document.getElementById("btn").disabled = true;
    }
}

//Funcion que valida si el correo es institucional y contraseñas iguales
async function enviarRegistro(){
	console.log("Validando...");
	const result = await validar();
	if (result=='todoBien') {
		document.getElementById("formularioRegisttrar").submit();
	}else{
		if(result=='correoInvalido'){
            Swal.fire({
                icon: 'error',
                title: 'EL CORREO NO ES DE LA UTEZ',
                text: 'Favor de colocar un Email valido',
            })
        }else{
            if(result=='contrseniaInvalida'){
                Swal.fire({
                    icon: 'error',
                    title: 'LAS CONTRASEÑAS NO COINCIDEN',
                    text: 'Asegurate de ingresar las contraseñas correctas',
                })
            }
        }
	}
}
function validar(){
	return new Promise(resolve =>{

        let contr = document.getElementById("password").value;
        let contrarepeat = document.getElementById("password_repeat").value;

		let correo = document.getElementById('email').value;
        correo = correo.substring((correo.length-11),correo.length);

		if (correo == "utez.edu.mx" && contr == contrarepeat) {
			setTimeout(()=>{
				resolve('todoBien');
			},500);
		}else{
			if(correo != "utez.edu.mx"){
                setTimeout(()=>{
                    resolve('correoInvalido');
                },500);
            }else{
                if(contr != contrarepeat){
                    setTimeout(()=>{
                        resolve('contrseniaInvalida');
                    },500);
                }
            }
		}
	});
}

function aceptarAsesoria(){
    Swal.fire({
        icon: 'success',
        title: 'HECHO',
        text: 'Ha aceptado la asesoría',
    })
}

function Motivo(string){//Validar caracteres en ingresar un tema
    let out = '';
    //Se añaden las letras validas
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ ';//Caracteres validos
	
    for (var i=0; i<string.length; i++){
        if (filtro.indexOf(string.charAt(i)) != -1){
            out += string.charAt(i);
        }
    }
    return out;
}

function enviarRechazo(){
    let motivo = document.getElementById('message-text').value;
    if(motivo.length>0){
        Swal.fire({
            icon: 'success',
            title: 'MENSAJE ENVIADO',
            text: 'Ha rechazado la asesoría',
        })
    }
}

//Activar campos del perfil del docente
function activarInputDocente(){
    document.getElementById("emailDocente").disabled=false;
    document.getElementById("nombreDocente").disabled=false;
    document.getElementById("aPeternoDocente").disabled=false;
    document.getElementById("aMaternoDocente").disabled=false;
}

//Validar camopos del perfil de Docente
function ValidarDocentePerfil(string){
    let out = '';
    //Se añaden las letras validas
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ@. ';//Caracteres validos

    let mail = document.getElementById("email").value;
    let nombre = document.getElementById("nombre").value;
    let paterno = document.getElementById("aPaterno").value;
    let materno = document.getElementById("aMaterno").value;
    let sexo = document.getElementById("sexo").value;

    for (var i=0; i<string.length; i++){
        if (filtro.indexOf(string.charAt(i)) != -1){
            out += string.charAt(i);
        }
    }

    if(usuario.length>0 && mail.length>0 && nombre.length>0 && paterno.length>0 && materno.length>0 && sexo.length>0){
        
        nombre = nombre.trim();
        paterno = paterno.trim();
        materno = materno.trim();
        mail = mail.trim();

        nombre = nombre[0].toUpperCase() + nombre.slice(1);
        paterno = paterno[0].toUpperCase() + paterno.slice(1);
        materno = materno[0].toUpperCase() + materno.slice(1);
        sexo = sexo[0].toUpperCase() + sexo.slice(1);
        mail = mail.toLowerCase();

        document.getElementById("btn").disabled=false;

    }else{
        document.getElementById("btn").disabled=true;
    }

    return out;
}

function sexoValidar(){
    let mail = document.getElementById("email").value;
    let nombre = document.getElementById("nombre").value;
    let paterno = document.getElementById("aPaterno").value;
    let materno = document.getElementById("aMaterno").value;
    let sexo = document.getElementById("sexo").value;

    if(matricula.length>0 && mail.length>0 && nombre.length>0 && paterno.length>0 && materno.length>0 && sexo.length>0){
        
        document.getElementById("btn").disabled=false;

        nombre = nombre.trim();
        paterno = paterno.trim();
        materno = materno.trim();
        mail = mail.trim();

        nombre = nombre[0].toUpperCase() + nombre.slice(1);
        paterno = paterno[0].toUpperCase() + paterno.slice(1);
        materno = materno[0].toUpperCase() + materno.slice(1);
        sexo = sexo[0].toUpperCase() + sexo.slice(1);
        mail = mail.toLowerCase();

    }else{
        document.getElementById("btn").disabled=true;
    }
}

async function guardarPerfilDocente(){
	console.log("Validando...");
	const result = await validarCorreoPerfil();
	if (result=='correoValido') {

        Swal.fire({
            icon: 'success',
            title: 'CORRECTO',
            text: 'Datos guardados correctamente',
        })

        document.getElementById("formPerfil").submit();
        
	}else{
		Swal.fire({
            icon: 'error',
            title: 'DATOS ERRONEOS',
            text: 'El correo no es institucional',
        })
	}
}
function validarCorreoPerfil(){
	return new Promise(resolve =>{
		let correo = document.getElementById('email').value;
        correo = correo.substring((correo.length-11),correo.length);

		if (correo == "utez.edu.mx") {
			setTimeout(()=>{
				resolve('correoValido');
			},1500);
		}else{
			setTimeout(()=>{
				resolve('correoInvalido');
			},1500);
		}
	});
}

//////////////////////Recuperar contraseña//////////////////////

function recuperarContra(string){ //Recuperar contraseña validaciones

    let out = '';
    //Se añaden las letras validas
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ@. ';//Caracteres validos

    let correo = document.getElementById("correo").value;
    let clave = document.getElementById("clave").value;
    let pass = document.getElementById("password").value;
    let Rpass = document.getElementById("password_repeat").value;

    for (var i=0; i<string.length; i++){
        if (filtro.indexOf(string.charAt(i)) != -1){
            out += string.charAt(i);
        }
    }

    if(correo.length>0 && clave.length>0 && pass.length>0 && Rpass.length>0 && (pass == Rpass)){
        correo = correo.trim();
        clave = clave.trim();
        pass = pass.trim();
        Rpass = Rpass.trim();
        correo = correo.toLowerCase();

        document.getElementById("btn").disabled = false;
    }else{
        document.getElementById("btn").disabled = true;
    }
    return out;
}

async function guardarPerfilCorreoRecuperarContra(){
	console.log("Validando...");
	const result = await validarRecuperarContra();
	if (result=='todoBien') {
		document.getElementById("recuperarContra").submit();
	}else{
		if(result=='correoInvalido'){
            Swal.fire({
                icon: 'error',
                title: 'EL CORREO NO ES DE LA UTEZ',
                text: 'Favor de colocar un Email valido',
            })
        }else{
            if(result=='contrseniaInvalida'){
                Swal.fire({
                    icon: 'error',
                    title: 'LAS CONTRASEÑAS NO COINCIDEN',
                    text: 'Asegurate de ingresar las contraseñas correctas',
                })
            }
        }
	}
}
function validarRecuperarContra(){
	return new Promise(resolve =>{

        let contr = document.getElementById("password").value;
        let contrarepeat = document.getElementById("password_repeat").value;

        let correo = document.getElementById('correo').value;
        
        correo = correo.substring((correo.length-11),correo.length);

		if (correo == "utez.edu.mx" && contr == contrarepeat) {
			setTimeout(()=>{
				resolve('todoBien');
			},500);
		}else{
			if(correo != "utez.edu.mx"){
                setTimeout(()=>{
                    resolve('correoInvalido');
                },500);
            }else{
                if(contr != contrarepeat){
                    setTimeout(()=>{
                        resolve('contrseniaInvalida');
                    },500);
                }
            }
		}
	});
}

//<script src = "http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
//<script src = "http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
$(document).ready(function() {
    function disablePrev() { window.history.forward() }
    window.onload = disablePrev();
    window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
});
