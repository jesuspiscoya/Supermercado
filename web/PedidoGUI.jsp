<%-- 
    Document   : PedidoGUI
    Created on : May 13, 2021, 8:10:07 PM
    Author     : jesus
--%>

<%@page import="java.util.List"%>
<%@page import="vista.PedidoPresentador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <style>@import"css/style.css";</style>
        <style>@import"css/nav.css";</style>
        <script src="https://kit.fontawesome.com/f271c886fb.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <!-- NavBar -->
        <jsp:include page="NavBar.jsp"/>
        
        <div class="center pedido">
            <div class="contenedor">
                <p class="titulo">Nota de Pedido</p>
                <% PedidoPresentador pedPre=(PedidoPresentador)session.getAttribute("pedPre"); %>
                <%! String total; %>
                <div class="tabla-div">
                    <table class="tabla-lista subtabla">
                        <% Object[] fila=pedPre.getFila(); %>
                        <% Object[] fila3=pedPre.getFila2(); %>
                        <form action="PedidoControl" method="post">
                            <tr>
                                <th class="tabla-titulo tabla-border">Número</th><td class="tabla-border"><%= fila[0] %></td>
                                <th class="tabla-titulo tabla-border">Código</th><td class="tabla-border"><input class="input-buscar" type="text" name="codCli" size="5" value="<%= fila3[0] %>" required></td>
                                <td class="buscar"><input type="hidden" name="acceso" value="Buscar"><button type="submit" name="acceso" class="btn-buscarCli"><i class="fas fa-search"></i></button></td>
                            </tr>
                            <tr>
                                <th class="tabla-titulo tabla-border">Fecha</th><td class="tabla-border"><%= fila[1] %></td>
                                <th class="tabla-titulo tabla-border">Nombre</th><td colspan="2" class="tabla-border"><%= fila3[1] %></td>
                            </tr>
                            <tr>
                                <th class="tabla-titulo">Empleado</th><td><%= fila[3] %></td>
                                <th class="tabla-titulo">Dirección</th><td colspan="2"><%= fila3[2] %></td>
                            </tr>
                        </form>
                    </table>
                    <table class="tabla-lista subtabla">
                        <% List lista=pedPre.getLista(); %>
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Precio</th>
                            <th class="tabla-titulo">Cantidad</th>
                            <th class="tabla-titulo">Importe</th>
                            <th class="tabla-titulo"></th>
                        </tr>
                        <% for (int i = 0; i < lista.size(); i++) { %>
                        <% Object[] fila2=(Object[])lista.get(i); %>
                        <% total=fila2[5].toString(); %>
                        <% String s; if (fila2[2]=="") s=""; else s="S/ ";%>
                        <tr>
                            <form action="PedidoControl" method="post">
                                <td><input type="hidden" name="codigo" value="<%= fila2[0] %>"><%= fila2[0] %></td>
                                <td><%= fila2[1] %></td>
                                <td><%= s+fila2[2] %></td>
                                <td><%= fila2[3] %></td>
                                <td><%= s+fila2[4] %></td>
                                <td><input type="hidden" name="acceso" value="Quitar Articulo"><button type="submit" name="acceso" class="btn-eliminar"><i class="far fa-trash-alt"></i></button></td>
                            </form>
                        </tr>
                        <% } %>
                        <tr>
                            <td colspan="4" class="tabla-total">Total</td>
                            <td class="tabla-total2"><div class="total">S/ <%= total %></div></td>
                            <td class="tabla-total"></td>
                        </tr>
                    </table>
                    <table class="tabla-lista subtabla">
                        <tr>
                            <form action="PedidoControl" method="post">
                                <th><input type="hidden" name="codigo" value="<%= fila[3] %>"></th>
                                <th><input type="hidden" name="codCli" value="<%= fila3[0] %>"></th>
                                <th class="tabla-titulo"><input type="submit" name="acceso" class="btn-nuevo" value="Nuevo Pedido"></th>
                                <th class="tabla-titulo"><input type="submit" name="acceso" class="btn-listar" value="Listar Articulos"></th>
                                <th class="tabla-titulo"><input type="submit" name="acceso" class="btn-guardar" value="Grabar Pedido"></th>
                            </form>
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
