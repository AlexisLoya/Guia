<%-- 
    Document   : Iniciar Sesion
    Created on : Jun 15, 2020, 11:25:20 PM
    Author     : alexl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Login - Guia</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
    </head>

    <body class="bg-gradient-primary">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-9 col-lg-12 col-xl-10">
                    <div class="card shadow-lg o-hidden border-0 my-5">
                        <div class="card-body p-0">

                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-flex">
                                    <div class="flex-grow-1 bg-login-image" style="background-image: url(&quot;assets/img/background/enter.svg&quot;);"></div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">

                                            <c:if test="${message != null}">
                                                <div class="alert alert-warning mr-3 ml-3" role="alert">
                                                    ${message}
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                            </c:if>
                                            <a href=""><img src="<%=path%>/assets/img/logoGuiaV2.png" width="40" height="40" class="d-inline-block align-top mt-0 "></a>
                                            <h4 class="text-dark mb-4">Inicio de Sesión</h4>

                                        </div>
                                        <form class="user" action="Iniciar" method="post">
                                            <input type="hidden" name="action" value="iniciarSesion">
                                            <div class="form-group"><input class="form-control form-control-user" type="email" id="Email" aria-describedby="emailHelp" placeholder="Tu Correo electronico" name="email"></div>
                                            <div class="form-group"><input class="form-control form-control-user" type="password" id="Password" placeholder="Tu contraseña" name="password"></div>
                                            <div class="form-group">
                                     <button class="btn btn-primary btn-block text-white btn-user mb-3" type="submit">Iniciar Sesión</button>
                                            </div>

                                        </form>
                                        <div class="text-center mb-1"><a class="small" href="<%=path%>/Recuperacion">¿Olvidaste tu contraseña?</a></div>
                                        <div class="text-center mb-1"><a class="small" href="<%=path%>/Registro">Registrate como Alumno</a></div>
                                        <div class="text-center mb-1"><a class="small" href="<%=path%>/Registro-E">Registrate como Docente</a></div>
<!--                                         Button trigger modal 
                                        <div class="text-center"><a class="small"  data-toggle="modal" data-target="#staticBackdrop" class="text-decoration-none">Registrate en la Aplicación</a></div>

                                         Modal 
                                        <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header" style="background-color:#04a48d; color: white;">
                                                        <h5 class="modal-title" id="staticBackdropLabel">Elije tu registro</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-dialog modal-dialog-centered p-2">
                                                        <form action="Registro-E" method="post">
                                                            <button type="button" class="btn btn-primary fas fa-chalkboard-teacher"><br>Registro como Docente</button>
                                                        </form>
                                                    </div>
                                                    <div class="modal-dialog modal-dialog-centered p-2">
                                                        <form action="Registro" method="post">
                                                            <button type="button" class="btn btn-primary fas fa-user-graduate"><br>Registro como Alumno</button>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                        
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>-->
                                    </div>
                                </div>
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