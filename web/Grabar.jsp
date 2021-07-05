<%-- 
    Document   : ClienteGUI
    Created on : Apr 21, 2021, 1:22:59 AM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<%
    PresentadorGeneral pg=(PresentadorGeneral) session.getAttribute("pg");
    String rol=request.getParameter("id");
    if (session.getAttribute("id") != null)
        rol=(String)session.getAttribute("id");
    session.setAttribute("id", null);
    if (pg == null)
        pg=new PresentadorGeneral();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= rol %></title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
    </head>
    <body>
        <!-- NavBar -->
        <% if (!rol.equals("Empleado")) { %>
        <jsp:include page="NavBar.jsp"/>
        <% } %>
        
        <div class="center">
            <div class="contenido">
                <x:form styleClass="formGrabar" action="GrabarControl" method="post">
                    <h2>Nuevo <%= rol %></h2>
                    <div class="datos">
                        <% if (rol.equals("Empleado")) { %>
                        <label for="codigo">Código:</label>
                        <x:text property="codigo" styleId="codigo"/>

                        <label for="nombre">Nombre:</label>
                        <x:text property="nombre" styleId="nombre"/>

                        <label for="tipo">Tipo:</label>
                        <x:text property="tipo" styleId="tipo"/>

                        <label for="usuario">Usuario:</label>
                        <x:text property="usuario" styleId="usuario"/>

                        <label for="password">Contraseña:</label>
                        <x:password property="password" styleId="password"/>
                        <% } else if (rol.equals("Articulo")) { %>
                        <label for="codigo">Código:</label>
                        <x:text property="codigo" styleId="codigo"/>

                        <label for="nombre">Nombre:</label>
                        <x:text property="nombre" styleId="nombre"/>

                        <label for="precio">Precio:</label>
                        <x:text property="precio" styleId="precio"/>
                        
                        <label for="stock">Stock:</label>
                        <x:text property="stock" styleId="stock"/>
                        <% } else { %>
                        <label for="codigo">Código:</label>
                        <x:text property="codigo" styleId="codigo"/>

                        <label for="nombre">Nombre:</label>
                        <x:text property="nombre" styleId="nombre"/>

                        <label for="direccion">Dirección:</label>
                        <x:text property="direccion" styleId="direccion"/>
                        <% } %>
                    </div>
                    
                    <% if (!pg.getMsg().equals("")) { %>
                    <div class="msg-grabar">
                        <span><%= pg.getMsg() %></span>
                    </div>
                    <% pg.setMsg(""); %>
                    <% } %>
                    
                    <div class="botones">
                        <x:hidden property="form" value="<%= rol %>"/>
                        <x:submit styleClass="btn-grabar" value="Grabar"/>
                        <% if (rol.equals("Empleado")) { %>
                        <x:button property="back" styleClass="btn-back" value="Regresar" onclick="location.href='Portal.jsp'"/>
                        <% } else { %>
                        <x:button property="back" styleClass="btn-back" value="Regresar" onclick="location.href='Menu.jsp'"/>
                        <% } %>
                    </div>
                </x:form>
            </div>
        </div>
    </body>
</html>
