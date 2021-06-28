<%-- 
    Document   : Mensaje
    Created on : Apr 15, 2021, 7:32:06 PM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/mensaje.css";</style>
    </head>
    <body>
        <div class="center">
            <div class="contenedor">
                <p class="titulo">Mensaje</p>
                <!-- Scriptlets -->
                <% PresentadorGeneral pg=(PresentadorGeneral)session.getAttribute("pg"); %>
                <div class="contenido">
                    <div>
                        <!-- Expresion -->
                        <p class="mensaje"><%= pg.getMsg() %></p>
                    </div>
                    <div class="volver">
                        <a href="Portal.jsp">Volver al Portal</a>
                    </div>
                </div>
                <% pg.setMsg(null); %>
            </div>
        </div>
    </body>
</html>
