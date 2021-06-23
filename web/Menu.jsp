<%-- 
    Document   : Menu
    Created on : Apr 29, 2021, 8:02:28 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
    </head>
    <body>
        <!-- Scriptlets -->
        <% Object[] fila=(Object[])session.getAttribute("fila"); %>
        
        <!-- NavBar -->
        <jsp:include page="NavBar.jsp"/>
        
        <div class="center">
            <div class="contenido">
                <div class="contenedor">
                    <p class="titulo">Bienvenido</p>
                    <div class="tabla-div">
                        <table class="tabla-inicio">
                            <tr>
                                <th class="tabla-titulo">Código</th>
                                <th class="tabla-titulo">Nombre</th>
                            </tr>
                            <tr>
                                <td class="border-data"><%= fila[0] %></td>
                                <td><%= fila[1] %></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
