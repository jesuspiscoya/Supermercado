<%-- 
    Document   : Acceso
    Created on : Apr 29, 2021, 8:02:41 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <p class="titulo-prin">Acceso al Sistema</p>
        <div class="center">
            <div class="contenido">
                <form class="formLogin" action="EmpleadoControl" method="post">
                    <h2>Login</h2>
                    <div class="datos">
                        <label for="usuario">Usuario:</label>
                        <input type="text" name="usuario" placeholder="Escriba su usuario" id="usuario" required>

                        <label for="password">Contraseña:</label>
                        <input type="password" name="password" placeholder="Escriba su contraseña" id="password" required>
                    </div>
                    <div class="botones">
                        <input type="submit" name="acceso" class="btn-login" value="Iniciar Sesion">
                        <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Portal.jsp'">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>