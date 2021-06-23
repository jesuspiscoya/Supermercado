<%-- 
    Document   : ProveedorGUI
    Created on : Apr 21, 2021, 1:42:29 AM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedor</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
    </head>
    <body>
        <!-- NavBar -->
        <jsp:include page="NavBar.jsp"/>
        
        <% String msgP=(String)session.getAttribute("msgP"); %>
        <% if (msgP==null) msgP=""; %>
        
        <div class="center">
            <div class="contenido">
                <form class="formGrabar" action="ProveedorControl" method="post">
                    <h2>Nuevo Proveedor</h2>
                    <div class="datos">
                        <label for="codigo">Código:</label>
                        <input type="text" name="codigo" placeholder="Escriba su código" id="codigo" required>

                        <label for="nombre">Nombre:</label>
                        <input type="text" name="nombre" placeholder="Escriba su nombre" id="nombre" required>

                        <label for="direccion">Dirección:</label>
                        <input type="text" name="direccion" placeholder="Escriba su dirección" id="direccion" required>
                    </div>
                    <div class="msg-grabar">
                        <span><%= msgP %></span>
                    </div>
                    <div class="botones">
                        <input type="submit" name="acceso" class="btn-grabar" value="Grabar">
                        <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
