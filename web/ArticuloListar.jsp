<%-- 
    Document   : ArticuloListar
    Created on : May 13, 2021, 8:24:27 PM
    Author     : jesus
--%>

<%@page import="vista.PedidoPresentador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Articulos</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <div class="center">
            <div class="contenedor">
                <p class="titulo">Listar Artículos</p>
                <% PedidoPresentador pedPre=(PedidoPresentador)session.getAttribute("pedPre"); %>
                <% String control=session.getAttribute("control").toString(); %>
                <% if (control=="orden") control="OrdenControl"; else control="PedidoControl"; %>
                <div class="tabla-div">
                    <table class="tabla-lista">
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Precio</th>
                            <th class="tabla-titulo">Cantidad</th>
                            <th class="tabla-titulo"></th>
                        </tr>
                        <% for (int i = 1; i < pedPre.getLista().size(); i++) { %>
                        <% Object[] fila=(Object[])pedPre.getLista().get(i); %>
                        <tr>
                            <form class="formListar" action="<%= control %>" method="post">
                                <td><input type="hidden" name="codigo" size="5" value="<%= fila[0] %>" required><%= fila[0] %></td>
                                <td><input type="hidden" name="nombre" size="25" value="<%= fila[1] %>" required><%= fila[1] %></td>
                                <td><input type="hidden" name="precio" size="5" value="<%= fila[2] %>"required>S/ <%= fila[2] %></td>
                                <td><input class="input-cantidad" type="number" name="cantidad" value="" required></td>
                                <td><input type="submit" name="acceso" class="btn-agregar" value="Agregar Articulo"></td>
                            </form>
                        </tr>
                        <% } %>
                    </table>
                </div>
                <div class="botones">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="javascript:history.back()">
                </div>
            </div>
        </div>
    </body>
</html>
