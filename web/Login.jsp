<%-- 
    Document   : Acceso
    Created on : Apr 29, 2021, 8:02:41 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
    </head>
    <body>
        <p class="titulo-prin">Acceso al Sistema</p>
        <div class="center">
            <div class="contenido">
                <x:form styleClass="formLogin" action="AccesoControl" method="post">
                    <h2>Login</h2>
                    <div class="datos">
                        <label for="usuario">Usuario:</label>
                        <x:text property="usuario" styleId="usuario"/>
                        <x:messages id="m" property="usuario">${m}</x:messages>

                        <label for="password">Contraseña:</label>
                        <x:password property="password" styleId="password"/>
                        <x:messages id="m" property="password">${m}</x:messages>
                    </div>
                    <div class="botones">
                        <x:submit styleClass="btn-login" value="Iniciar Sesion"/>
                        <x:button property="back" styleClass="btn-back" onclick="location.href='Portal.jsp'" value="Regresar"/>
                    </div>
                </x:form>
            </div>
        </div>
    </body>
</html>