<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Registro - Guia</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/package/dist/sweetalert2.min.css">
    </head>

    <body style="background-color: #094670">
        <div class="container">
            <div class="card shadow-lg o-hidden border-0 my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-flex">
                            <div class="flex-grow-1 bg-register-image" style="background-image: url(&quot;assets/img/background/forgot_password.svg&quot;) ;"></div>
                        </div>
                        <div class="col-lg-7">
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
                                    <h4 class="text-dark mb-4">¡Recupera tu contraseña!</h4>
                                </div>
                                <form class="user" action="Recuperacion" method="Post" id="recuperarContra">
                                    <div class="form-group">
                                        <input type="hidden" name="action" value="validarCorreo">
                                        <input class="form-control form-control-user" type="email" id="correo" placeholder="Correo electronico" name="correo" onkeyup="this.value = recuperarContra(this.value)">
                                    </div>
                                    <div class="container text-center">
                                        <span class="small text-center">Se enviará un correro con el código para restablecer tu contraseña</span>
                                    </div>
                                    <c:if test="${validacion == null}">
                                </div>
                                <div class="container">
                                    <button class="btn btn-primary btn-block text-white btn-user mb-4" type="submit" id="btn">Enviar</button>
                                </div>
                                </form>
                            </c:if>
                            <c:if test="${validacion != null}">
                                <div class="form-group"><input class="form-control form-control-user" type="text" id="clave" placeholder="Clave de seguridad" name="clave" onkeyup="this.value = recuperarContra(this.value)"></div>    
                                <div class="form-group mt-3">
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0"><input class="form-control form-control-user" type="password" id="password" placeholder="Contraseña" name="password" onkeyup="this.value = recuperarContra(this.value)">
                                        </div>
                                        <div class="col-sm-6"><input class="form-control form-control-user" type="password" id="password_repeat" placeholder="Repita su nueva contraseña" name="password_repeat" onkeyup="this.value = recuperarContra(this.value)"></div>
                                    </div>
                                </div>
                            </div>
                            </form>
                            <div class="container">
                                <button class="btn btn-primary btn-block text-white btn-user mb-4" type="submit" id="btn" disabled = true onclick="guardarPerfilCorreoRecuperarContra()">Enviar</button>
                            </div>
                        </c:if>

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
<script src="assets/js/docente_validaciones.js"></script>
<script src="assets/package/dist/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
<script src="assets/package//dist/sweetalert2.min.js"></script>
</html>