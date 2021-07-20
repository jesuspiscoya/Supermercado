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
    String rol=(String)session.getAttribute("id");
    session.setAttribute("id", null);
    Object[] fila=new Object[5];
    if (rol.equals("Empleado"))
        fila=pg.getEmpleado();
    else if (rol.equals("Cliente"))
        fila=pg.getCliente();
    else if (rol.equals("Proveedor"))
        fila=pg.getProveedor();
    else
        fila=pg.getArticulo();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= rol %></title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <p class="titulo-prin"><%= rol+" "+fila[0] %></p>
        <div class="center">
            <x:form styleClass="formEditar" action="CRUDControl" method="post">
                <h2>Editar Usuario</h2>
                <div class="datos">
                    <% if (rol.equals("Empleado")) { %>
                    <label for="nombre">Nombre:</label>
                    <x:text property="nombre" value="<%= fila[1].toString() %>" styleId="nombre"/>

                    <label for="tipo">Tipo:</label>
                    <x:text property="tipo" value="<%= fila[2].toString() %>" styleId="tipo"/>

                    <label for="usuario">Usuario:</label>
                    <x:text property="usuario" value="<%= fila[3].toString() %>" styleId="usuario"/>

                    <label for="password">Contraseña:</label>
                    <x:text property="password" value="<%= fila[4].toString() %>" styleId="password"/>
                    <% } else if (rol.equals("Articulo")) { %>
                    <label for="nombre">Nombre:</label>
                    <x:text property="nombre" value="<%= fila[1].toString() %>" styleId="nombre"/>
                    
                    <label for="precio">Precio:</label>
                    <x:text property="precio" value="<%= fila[2].toString() %>" styleId="precio"/>
                    
                    <label for="stock">Stock:</label>
                    <x:text property="stock" value="<%= fila[3].toString() %>" styleId="stock"/>
                    <% } else { %>
                    <label for="nombre">Nombre:</label>
                    <x:text property="nombre" value="<%= fila[1].toString() %>" styleId="nombre"/>

                    <label for="direccion">Dirección:</label>
                    <x:text property="direccion" value="<%= fila[2].toString() %>" styleId="direccion"/>
                    <% } %>
                </div>
                
                <% if (!pg.getMsg().equals("")) { %>
                    <div class="msg-noFind">
                        <span><%= pg.getMsg() %></span>
                        <x:messages id="m" property="codigo">${m}</x:messages>
                    </div>
                <% } pg.setMsg(""); %>
                
                <div class="botones">
                    <x:hidden property="form" value="<%= rol %>"/>
                    <x:hidden property="codigo" value="<%= fila[0].toString() %>"/>
                    <x:submit styleClass="btn-buscar" property="acceso"  value="Actualizar"/>
                    <% if (rol.equals("Empleado")) { %>
                    <x:button styleClass="btn-back" value="Regresar" onclick="location.href='Buscar.jsp?id=Empleado'" property=""/>
                    <% } else if (rol.equals("Cliente")) { %>
                    <x:button styleClass="btn-back" value="Regresar" onclick="location.href='Buscar.jsp?id=Cliente'" property=""/>
                    <% } else { %>
                    <x:button styleClass="btn-back" value="Regresar" onclick="location.href='Buscar.jsp?id=Proveedor'" property=""/>
                    <% } %>
                </div>
            </x:form>
        </div>
    </body>
</html>
