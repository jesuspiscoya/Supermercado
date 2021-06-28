<%-- 
    Document   : EmpleadoListar
    Created on : May 6, 2021, 7:41:55 PM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page import="servicio.EmpleadoServicio"%>
<%@page import="servicio.EmpleadoServicioImp"%>
<%@page import="java.util.List"%>
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
        <% PresentadorGeneral pg=(PresentadorGeneral)session.getAttribute("pg"); %>
        <% List lista=pg.getLista(); %>
        
        <div class="center">
            <div class="contenedor">
                <p class="titulo">Lista de <%= pg.getMsg() %></p>
                <div class="tabla-div">
                    <table class="tabla-lista">
                        <% if (pg.getMsg().equals("Empleados")) { %>
                        <tr>
                            <th class="tabla-titulo border-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Tipo</th>
                            <th class="tabla-titulo">Usuario</th>
                            <th class="tabla-titulo">Password</th>
                        </tr>
                        <% for (int i = 0; i < lista.size(); i++) {
                            Object[] fila=(Object[]) lista.get(i); %>
                        <tr>
                            <td><%= fila[0] %></td>
                            <td><%= fila[1] %></td>
                            <td><%= fila[2] %></td>
                            <td><%= fila[3] %></td>
                            <td><%= fila[4] %></td>
                        </tr>
                        <% } } else { %>
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Dirección</th>
                        </tr>
                        <% for (int i = 0; i < lista.size(); i++) {
                            Object[] fila=(Object[])lista.get(i); %>
                        <tr>
                            <td><%= fila[0] %></td>
                            <td><%= fila[1] %></td>
                            <td><%= fila[2] %></td>
                        </tr>
                        <% } } pg.setMsg(""); %>
                    </table>
                </div>
                <div class="botones">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                </div>
            </div>
        </div>
    </body>
</html>
