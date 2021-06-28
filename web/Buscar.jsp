<%-- 
    Document   : Buscar
    Created on : Apr 29, 2021, 8:52:23 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<% String rol=request.getParameter("id"); %>
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
        <jsp:include page="NavBar.jsp"/>
        
        <div class="center">
            <div class="contenido">
                <x:form styleClass="formBuscar" action="BuscarControl" method="post">
                    <h2>Buscar <%= rol %></h2>
                    <div class="datos">
                        <label for="codigo">Código:</label>
                        <x:text property="codigo" styleId="codigo"/>
                    </div>
                    <div class="botones">
                        <x:hidden property="buscar" value="<%= rol %>"/>
                        <x:submit styleClass="btn-buscar" value="Buscar"/>
                        <x:button property="back" styleClass="btn-back" onclick="location.href='Menu.jsp'" value="Regresar"/>
                    </div>
                </x:form>
            </div>
        </div>
    </body>
</html>
