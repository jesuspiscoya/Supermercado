<%-- 
    Document   : EmpleadoListar
    Created on : May 6, 2021, 7:41:55 PM
    Author     : jesus
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="vista.PresentadorGeneral"%>
<%@page import="servicio.EmpleadoServicio"%>
<%@page import="servicio.EmpleadoServicioImp"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<% PresentadorGeneral pg=(PresentadorGeneral)session.getAttribute("pg"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= pg.getMsg() %></title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
        <script src="https://kit.fontawesome.com/f271c886fb.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <!-- NavBar -->
        <jsp:include page="NavBar.jsp"/>
        
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
                        <% for (int i = 0; i < pg.getLista().size(); i++) { %>
                        <% Object[] fila=(Object[]) pg.getLista().get(i); %>
                        <tr>
                            <td><%= fila[0] %></td>
                            <td><%= fila[1] %></td>
                            <td><%= fila[2] %></td>
                            <td><%= fila[3] %></td>
                            <td><%= fila[4] %></td>
                        </tr>
                        <% } } else if (pg.getMsg().equals("Articulos")) { %>
                        <tr>
                            <th class="tabla-titulo"></th>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Precio</th>
                            <th class="tabla-titulo">Añadir</th>
                            <th class="tabla-titulo"></th>
                        </tr>
                        <% for (int i = 0; i < pg.getLista().size(); i++) { %>
                        <% Object[] fila=(Object[])pg.getLista().get(i); %>
                        <tr>
                            <x:form styleClass="formListar" action="PedOrdControl" method="post">
                                <td></td>
                                <td><x:hidden property="codigo" value="<%= fila[0].toString() %>"/><%= fila[0] %></td>
                                <td><x:hidden property="nombre" value="<%= fila[1].toString() %>"/><%= fila[1] %></td>
                                <td><x:hidden property="precio" value="<%= fila[2].toString() %>"/>S/ <%= fila[2] %></td>
                                <x:hidden property="acceso" value="Agregar Articulo"/>
                                <td><button type="submit" class="btn-agregar"><i class="fas fa-plus"></i></button></td>
                                <td></td>
                            </x:form>
                        </tr>
                        <% } } else { %>
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Dirección</th>
                        </tr>
                        <% for (int i = 0; i < pg.getLista().size(); i++) { %>
                        <% Object[] fila=(Object[])pg.getLista().get(i); %>
                        <tr>
                            <td><%= fila[0] %></td>
                            <td><%= fila[1] %></td>
                            <td><%= fila[2] %></td>
                        </tr>
                        <% } } %>
                    </table>
                </div>
                <div class="botones">
                <% if (pg.getMsg().equals("Articulos")) { %>
                    <x:button styleClass="btn-back" value="Regresar" onclick="javascript:history.back()" property=""/>
                <% } else { %>
                    <x:button styleClass="btn-back" value="Regresar" onclick="location.href='Menu.jsp'" property=""/>
                <% } pg.setMsg(""); %>
                </div>
            </div>
        </div>
    </body>
</html>
