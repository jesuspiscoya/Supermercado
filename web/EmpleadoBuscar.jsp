<%-- 
    Document   : Buscar
    Created on : Apr 29, 2021, 8:52:23 PM
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
        <style>@import"css/nav.css";</style>
    </head>
    <body>
        <!-- NavBar -->
        <jsp:include page="NavBar.jsp"/>
        
        <div class="center">
            <div class="contenido">
                <form class="formBuscar" action="EmpleadoControl" method="post">
                <h2>Buscar Usuario</h2>
                    <div class="datos">
                        <label for="codigo">Código:</label>
                        <input type="text" name="codigo" placeholder="Escriba su código" id="codigo" required>
                    </div>
                    <div class="botones">
                        <input type="submit" name="acceso" class="btn-buscar" value="Buscar">
                        <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
