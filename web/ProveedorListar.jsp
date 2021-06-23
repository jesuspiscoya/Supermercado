<%-- 
    Document   : ProveedorListar
    Created on : May 9, 2021, 9:03:53 PM
    Author     : jesus
--%>

<%@page import="java.util.List"%>
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
        
        <div class="center">
            <div class="contenedor">
                <p class="titulo">Lista de Proveedores</p>
                <% List lista=(List)session.getAttribute("lista"); %>
                <div class="tabla-div">
                    <table class="tabla-lista">
                        <tr>
                            <th class="tabla-titulo border-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Dirección</th>
                        </tr>

                        <% for (int i = 1; i < lista.size(); i++) { %>
                        <% Object[] fila=(Object[])lista.get(i); %>
                        <tr>
                            <td><%= fila[0] %></td>
                            <td><%= fila[1] %></td>
                            <td><%= fila[2] %></td>
                        </tr>
                        <% } %>
                    </table>
                </div>
                <div class="botones">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                </div>
            </div>
        </div>
    </body>
</html>
