<%-- 
    Document   : ProveedorEditar
    Created on : May 9, 2021, 9:02:22 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <p class="titulo-prin">Editar Proveedor</p>
        <% Object[] fila=(Object[])session.getAttribute("fila"); %>
        <div class="center">
            <form class="formEditar" action="ProveedorControl" method="post">
                <h2>Editar Usuario</h2>
                <div class="datos">
                    <label for="codigo">Código:</label>
                    <input type="text" name="codigo" placeholder="Escriba su código" value="<%= fila[0] %>" id="codigo" required>

                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" placeholder="Escriba su nombre" value="<%= fila[1] %>" id="nombre" required>

                    <label for="direccion">Dirección:</label>
                    <input type="text" name="direccion" placeholder="Escriba su dirección" value="<%= fila[2] %>" id="direccion" required>
                </div>
                <div class="botones">
                    <input type="submit" name="acceso" class="btn-buscar" value="Buscar">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='ProveedorBuscar.jsp'">
                </div>
            </form>
        </div>
    </body>
</html>
