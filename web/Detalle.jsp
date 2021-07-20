<%-- 
    Document   : DetalleOrden
    Created on : May 27, 2021, 5:24:33 PM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    PresentadorGeneral pg=(PresentadorGeneral)session.getAttribute("pg");
    String rol=(String)session.getAttribute("rol");
    String titulo="";
    Object[] pedidoOrden=pg.getPedOrd();
    if (rol.equals("Pedido")) {
        titulo="Detalle de Pedido";
    } else {
        titulo="Detalle de Compra";
    }
%>
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
        <div class="center">
            <div class="contenedor">
                <p class="titulo"><%= titulo %> N° <%= pedidoOrden[0] %></p>
                <div class="tabla-div">
                    <table class="tabla-lista">
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Cantidad</th>
                            <th class="tabla-titulo">Importe</th>
                        </tr>
                        <% double total=0; %>
                        <% for (int i = 0; i < pg.getLista().size(); i++) { %>
                        <% Object[] carrito=(Object[])pg.getLista().get(i); %>
                        <% total+=(double)carrito[4]; %>
                        <tr>
                            <td><%= carrito[0] %></td>
                            <td><%= carrito[3] %></td>
                            <td>S/ <%= carrito[4] %></td>
                        </tr>
                        <% } pg.getLista().clear(); %>
                        <tr>
                            <td colspan="2" class="tabla-total">Total</td>
                            <td class="tabla-total2"><div class="total">S/ <%= total %></div></td>
                        </tr>
                    </table>
                </div>
                <div class="msg-grabar">
                    <span><%= pg.getMsg() %></span>
                    <% pg.setMsg(""); %>
                </div>
                <div class="botones">
                    <input type="button" name="back" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                </div>
            </div>
        </div>
    </body>
</html>
