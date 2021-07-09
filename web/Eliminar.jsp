<%-- 
    Document   : Eliminar.jsp
    Created on : Jul 9, 2021, 5:38:37 PM
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
    if (session.getAttribute("error") != null)
        pg.setMsg(" ");
    session.setAttribute("error", null);
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
        <jsp:include page="NavBar.jsp"/>
        
        <div class="center">
            <div class="contenido">
                <x:form styleClass="formBuscar" action="CRUDControl" method="post">
                    <h2>Eliminar <%= rol %></h2>
                    <div class="datos">
                        <label for="codigo">Código:</label>
                        <x:text property="codigo" styleId="codigo"/>
                    </div>
                    
                    <% if (!pg.getMsg().equals("")) { %>
                        <div class="msg-eliminar">
                            <span><%= pg.getMsg() %></span>
                            <x:messages id="m" property="codigo">${m}</x:messages>
                        </div>
                    <% } pg.setMsg(""); %>
                    
                    <div class="botones">
                        <x:hidden property="form" value="<%= rol %>"/>
                        <x:submit property="acceso" styleClass="btn-buscar" value="Eliminar"/>
                        <x:button styleClass="btn-back" onclick="location.href='Menu.jsp'" value="Regresar" property=""/>
                    </div>
                </x:form>
            </div>
        </div>
    </body>
</html>
