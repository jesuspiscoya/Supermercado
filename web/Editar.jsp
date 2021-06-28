<%-- 
    Document   : ClienteEditar
    Created on : May 9, 2021, 7:42:28 PM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<%
    PresentadorGeneral pg=(PresentadorGeneral)session.getAttribute("pg");
    Object[] fila=new Object[5];
    if (pg.getMsg().equals("Cliente"))
        fila=pg.getCliente();
    else
        fila=pg.getProveedor();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <p class="titulo-prin">Editar <%= pg.getMsg() %></p>
        <div class="center">
            <form class="formEditar" action="" method="post">
                <h2>Editar Usuario</h2>
                <div class="datos">
                    <% if (pg.getMsg().equals("Empleado")) { %>
                    <% fila=pg.getEmpleado(); %>
                    <label for="codigo">Código:</label>
                    <input type="text" name="codigo" placeholder="Escriba su código" value="<%= fila[0] %>" id="codigo" required>

                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" placeholder="Escriba su nombre" value="<%= fila[1] %>" id="nombre" required>

                    <label for="tipo">Tipo:</label>
                    <input type="text" name="tipo" placeholder="Escriba tipo de empleado" value="<%= fila[2] %>" id="tipo" required>

                    <label for="usuario">Usuario:</label>
                    <input type="text" name="usuario" placeholder="Escriba su usuario" value="<%= fila[3] %>" id="usuario" required>

                    <label for="password">Contraseña:</label>
                    <input type="password" name="password" placeholder="Escriba su contraseña" value="<%= fila[4] %>" id="password" required>
                    <% } else { %>
                    <label for="codigo">Código:</label>
                    <input type="text" name="codigo" placeholder="Escriba su código" value="<%= fila[0] %>" id="codigo" required>

                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" placeholder="Escriba su nombre" value="<%= fila[1] %>" id="nombre" required>

                    <label for="direccion">Dirección:</label>
                    <input type="text" name="direccion" placeholder="Escriba su dirección" value="<%= fila[2] %>" id="direccion" required>
                    <% } pg.setMsg(""); %>
                </div>
                <div class="botones">
                    <input type="submit" name="acceso" class="btn-buscar" value="Buscar">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Buscar.jsp?id=<%= pg.getMsg() %>'">
                </div>
            </form>
        </div>
    </body>
</html>
