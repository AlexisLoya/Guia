<%-- 
    Document   : Registro
    Created on : Jun 8, 2020, 8:09:12 AM
    Author     : alexl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Registro - Guia</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
    </head>

    <body class="bg-gradient-primary">
        <div class="container">
            <div class="card shadow-lg o-hidden border-0 my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-flex">
                            <div class="flex-grow-1 bg-register-image" style="background-image: url(&quot;assets/img/dogs/register.svg&quot;) ;"></div>
                        </div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h4 class="text-dark mb-4">Crea una cuenta!</h4>
                                </div>
                                <form class="user" action="Registro">
                                    <div class="form-group"><input class="form-control form-control-user" type="text" id="nombre" placeholder="Nombre" name="nombre"></div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0"><input class="form-control form-control-user" type="text" id="paterno" placeholder="Apellido Paterno" name="paterno">
                                        </div>
                                        <div class="col-sm-6"><input class="form-control form-control-user" type="text" id="materno" placeholder="Apellido Materno" name="materno"></div>
                                    </div>
                                    <div class="form-group"><input class="form-control form-control-user" type="email" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Correo electronico" name="email"></div>
                                    <div class="form-group">
                                       <input class="form-control form-control-user" type="text" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Código" name="codigo">
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-6 mb-3 mb-sm-0 form-group-label">Sexo</label>
                                        <div class="form-check form-check">
                                            <input class="form-check-input ml-1" type="radio" name="sexoOption" id="masculino" value="H">
                                            <label class="form-check-label col-sm-6 mb-3 mb-sm-0 ml-2" for="inlineRadio1">Masculino</label>
                                        </div>
                                        <div class="form-check form-check">
                                            <input class="form-check-input ml-1" type="radio" name="sexoOption" id="femenino" value="M">
                                            <label class="form-check-label col-sm-6 ml-2" for="inlineRadio2">Femenino</label>
                                        </div>
                                        <div class="form-group mt-3">
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0"><input class="form-control form-control-user" type="password" id="password" placeholder="Contraseña" name="password">
                                                </div>
                                                <div class="col-sm-6"><input class="form-control form-control-user" type="password" id="password_repeat" placeholder="Contraseña" name="password_repeat"></div>
                                            </div><button class="btn btn-primary btn-block text-white btn-user" type="submit">Registrar Cuenta</button>
                                        </div>
                                    </div>
                                </form>
                                <div class="text-center"><a class="small" href="forgot-password.html">¿Olvidaste tu
                                        contraseña?</a>
                                </div>
                                <div class="text-center"><a class="small" href="login.html">¿Ya tienes una cuenta? ¡Iniciar
                                        sesión!</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
        <script src="assets/js/theme.js"></script>
    </body>

</html>