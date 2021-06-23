<%-- 
    Document   : EmpleadoGUI
    Created on : Apr 15, 2021, 7:31:48 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleado</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <div class="center">
            <div class="contenido">
                <form class="formGrabar" action="EmpleadoControl" method="post">
                    <h2>Nuevo Empleado</h2>
                    <div class="datos">
                        <label for="codigo">Código:</label>
                        <input type="text" name="codigo" placeholder="Escriba su código" id="codigo" required>

                        <label for="nombre">Nombre:</label>
                        <input type="text" name="nombre" placeholder="Escriba su nombre" id="nombre" required>

                        <label for="tipo">Tipo:</label>
                        <input type="text" name="tipo" placeholder="Escriba tipo de empleado" id="tipo" required>

                        <label for="usuario">Usuario:</label>
                        <input type="text" name="usuario" placeholder="Escriba su usuario" id="usuario" required>

                        <label for="password">Contraseña:</label>
                        <input type="password" name="password" placeholder="Escriba su contraseña" id="password" required>
                    </div>
                    <div class="botones">
                        <input type="submit" name="acceso" class="btn-grabar" value="Grabar">
                        <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Portal.jsp'">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
