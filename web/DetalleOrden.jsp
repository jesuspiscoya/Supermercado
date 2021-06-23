<%-- 
    Document   : DetalleOrden
    Created on : May 27, 2021, 5:24:33 PM
    Author     : jesus
--%>

<%@page import="vista.PedidoPresentador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
    </head>
    <body>
        <% String msg=(String)session.getAttribute("msgDetalle"); %>
        <% PedidoPresentador pedPre=(PedidoPresentador)session.getAttribute("pedPre"); %>
        <% Object[] fila=pedPre.getFila(); %>
        <%! String total; %>
        
        <div class="center">
            <div class="contenedor">
                <p class="titulo">Detalle de Orden N° <%= fila[0] %></p>
                <div class="tabla-div">
                    <table class="tabla-lista">
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Cantidad</th>
                            <th class="tabla-titulo">Importe</th>
                        </tr>
                        <% for (int i = 0; i < pedPre.getLista().size(); i++) { %>
                        <% Object[] fila2=(Object[])pedPre.getLista().get(i); %>
                        <% total=fila2[5].toString(); %>
                        <tr>
                            <td><%= fila2[0] %></td>
                            <td><%= fila2[3] %></td>
                            <td>S/ <%= fila2[4] %></td>
                        </tr>
                        <% } %>
                        <tr>
                            <td colspan="2" class="tabla-total">Total</td>
                            <td class="tabla-total2"><div class="total">S/ <%= total %></div></td>
                        </tr>
                    </table>
                </div>
                <div class="msg-grabar">
                    <span><%= pedPre.getMsg() %></span>
                </div>
                <div class="botones">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                </div>
            </div>
        </div>
    </body>
</html>
