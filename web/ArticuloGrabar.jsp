<%-- 
    Document   : ArticuloGrabar
    Created on : May 25, 2021, 12:29:48 AM
    Author     : jesus
--%>

<%@page import="vista.PedidoPresentador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Articulo</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
    </head>
    <body>
        <!-- NavBar -->
        <jsp:include page="NavBar.jsp"/>
        
        <% PedidoPresentador pedPre=(PedidoPresentador)session.getAttribute("pedPre"); %>
        
        <div class="center">
            <div class="contenido">
                <div class="contenido">
                <form class="formGrabar" action="ArticuloControl" method="post">
                    <h2>Nuevo Artículo</h2>
                    <div class="datos">
                        <label for="codigo">Código:</label>
                        <input type="text" name="codigo" placeholder="Ingrese el código" id="codigo" required>

                        <label for="nombre">Nombre:</label>
                        <input type="text" name="nombre" placeholder="Ingrese el nombre" id="nombre" required>

                        <label for="precio">Precio:</label>
                        <input type="text" name="precio" placeholder="Ingrese el precio" id="precio" required>
                        
                        <label for="stock">Stock:</label>
                        <input type="text" name="stock" placeholder="Ingrese el stock" id="stock" required>
                    </div>
                    <div class="msg-grabar">
                        <span><%= pedPre.getMsg() %></span>
                    </div>
                    <div class="botones">
                        <input type="submit" name="acceso" class="btn-grabar" value="Grabar">
                        <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                    </div>
                </form>
                </div>
            </div>
        </div>
    </body>
</html>
