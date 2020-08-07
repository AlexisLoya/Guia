
//////////////////////Alumno//////////////////////

//Validaciones para el registro de un alumno
function registroAlumno(string){ 
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

//Validar caracteres en ingresar un tema cuando se registra una asesoria
function Tema(string){
    let tema = document.getElementById("tema").value;
    let horario = document.getElementById("horario").value;
    let materias = document.getElementById("materias").value;
    let profesores = document.getElementById("profesores").value;
    let out = '';

    //Se añaden las letras validas
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ_" ';//Caracteres validos
    
    //Se recorre el y guardan los caracteres validos
    for (var i=0; i<string.length; i++){
        if (filtro.indexOf(string.charAt(i)) != -1){
            out += string.charAt(i);
        }
    }

    if(tema.length>0 && horario!='1' && materias!='1' && profesores!='1'){
        document.getElementById("enviar").disabled=false;
    }else{
        document.getElementById("enviar").disabled=true;
    }
    //Devuelve los caracteres que son validos
    return out;
}
//Valida los select de la seleccion de asesorias
function validarSelect(){
    let tema = document.getElementById("tema").value;
    let horario = document.getElementById("horario").value;
    let materias = document.getElementById("materias").value;
    let profesores = document.getElementById("profesores").value;

    if(tema.length>0 && horario!='1' && materias!='1' && profesores!='1'){
        document.getElementById("enviar").disabled=false;
    }else{
        document.getElementById("enviar").disabled=true;
    }
}

//Validar caracteres al ingresar matricula de más alumnos
function alumnosExtra(string){
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

//Validar que al agregar más alumnos a una asesoria el campo de matricula este lleno 
async function validarAgregar(){
	console.log("Validando...");
	const result = await validarAlumno();
	if (result=='alumno') {
        Swal.fire({
            icon: 'success',
            title: 'ALUMNO AGREGADO',
            text: 'Se agrego con exito el alumno',
        })
		document.getElementById("formularioRegisttrar").submit();
	}else{
		Swal.fire({
            icon: 'error',
            title: 'DATOS INCOMPLETOS',
            text: 'Ingrese la contraseña de un alumno que desea agregar',
        })
	}
}
function validarAlumno(){
	return new Promise(resolve =>{
        let alumno = document.getElementById("otroAlumno").value;
		if (alumno == "") {
			setTimeout(()=>{
				resolve('alumno');
			},500);
		}else{
            setTimeout(()=>{
				resolve('Noalumno');
			},500);
        }
	});
}

//Validar camopos del perfil de alumno
function Validar(string){
    let out = '';
    //Se añaden las letras validas
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ@. ';//Caracteres validos
    
    let usuario = document.getElementById("username").value;
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

    return out;
}

//Activa los inputs del perfil del alumno para modificarlos
function activarInput(){
    document.getElementById("username").disabled=false;
    document.getElementById("email").disabled=false;
    document.getElementById("nombre").disabled=false;
    document.getElementById("aPaterno").disabled=false;
    document.getElementById("aMaterno").disabled=false;
    document.getElementById("aMaterno").disabled=false;
    document.getElementById("sexo").disabled=false;
}

async function guardarPerfil(){
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

//Validar la encuesta del alumno (valida comentario y respuestas)
function EncuestaValidar(string){ 

    let p1=document.getElementById("respuestaP1").selectedIndex;
    let p2=document.getElementById("respuestaP2").selectedIndex;
    let p3=document.getElementById("respuestaP3").selectedIndex;
    let p4=document.getElementById("respuestaP4").selectedIndex;

    let comentario = document.getElementById("comentario").value;
    let out = '';
    let filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890áÁéÉíÍóÓúÚ ';//Caracteres validos

    comentario = comentario.trim();

    if(comentario.length>0 && (p1!=0 && p2!=0 && p3!=0 && p4!=0)){

        document.getElementById("enviar").disabled = false;

        for (var i=0; i<string.length; i++){
            if (filtro.indexOf(string.charAt(i)) != -1){
                out += string.charAt(i);
            }
        }
    }else{
        document.getElementById("enviar").disabled = true;
    }
    return out;
}

function pregunta(){//Validar las respuestas
    let p1=document.getElementById("respuestaP1").selectedIndex;
    let p2=document.getElementById("respuestaP2").selectedIndex;
    let p3=document.getElementById("respuestaP3").selectedIndex;
    let p4=document.getElementById("respuestaP4").selectedIndex;
    let comentario = document.getElementById("comentario").value;

    if(comentario.length>0 && (p1!=0 && p2!=0 && p3!=0 && p4!=0)){

        document.getElementById("enviar").disabled = false;
    }else{
        document.getElementById("enviar").disabled = true;
    }
}



