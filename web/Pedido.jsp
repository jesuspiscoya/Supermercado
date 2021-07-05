<%-- 
    Document   : PedidoGUI
    Created on : May 13, 2021, 8:10:07 PM
    Author     : jesus
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<%
    Object[] empleado=(Object[])session.getAttribute("acceso");
    PresentadorGeneral pg=(PresentadorGeneral)session.getAttribute("pg");
    Object[] pedido=pg.getPedido();
    Object[] cliente=pg.getCliente();
%>
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
                <div class="tabla-div">
                    <table class="tabla-lista subtabla">
                        <x:form action="PedidoControl" method="post">
                            <tr>
                                <th class="tabla-titulo tabla-border">Número</th><td class="tabla-border"><%= pedido[0] %></td>
                                <th class="tabla-titulo tabla-border">Código</th><td class="tabla-border"><x:text property="codigo" styleClass="input-buscar" maxlength="4" value="<%= cliente[0].toString() %>"/></td>
                                <td class="buscar tabla-border"><x:hidden property="acceso" value="Buscar"/><button type="submit" class="btn-buscarCli"><i class="fas fa-search"></i></button></td>
                            </tr>
                        </x:form>
                        <tr>
                            <th class="tabla-titulo tabla-border">Fecha</th><td class="tabla-border"><%= pedido[1] %></td>
                            <th class="tabla-titulo tabla-border">Nombre</th><td colspan="2" class="tabla-border"><%= cliente[1] %></td>
                        </tr>
                        <tr>
                            <th class="tabla-titulo">Empleado</th><td><%= empleado[0] %></td>
                            <th class="tabla-titulo">Dirección</th><td colspan="2"><%= cliente[2] %></td>
                        </tr>
                    </table>
                    <% double total=0; %>
                    <% if (pg.getListaPed().size() > 0) { %>
                    <table class="tabla-lista subtabla">
                        <tr>
                            <th class="tabla-titulo">Código</th>
                            <th class="tabla-titulo">Nombre</th>
                            <th class="tabla-titulo">Precio</th>
                            <th class="tabla-titulo">Cantidad</th>
                            <th class="tabla-titulo">Importe</th>
                            <th class="tabla-titulo">Eliminar</th>
                        </tr>
                        <% for (int i = 0; i < pg.getListaPed().size(); i++) { %>
                        <% Object[] carrito=(Object[])pg.getListaPed().get(i); %>
                        <% total+=(double)carrito[4]; %>
                        <% String s; if (carrito[2]=="") s=""; else s="S/ "; %>
                        <tr>
                            <x:form action="PedidoControl" method="post">
                                <td><%= carrito[0] %></td>
                                <td><%= carrito[1] %></td>
                                <td><%= s+carrito[2] %></td>
                                <td><x:text property="cantidad" styleClass="input-cantidad" value="<%= carrito[3].toString() %>"/></td>
                                <td><%= s+carrito[4] %></td>
                                <td class="acciones">
                                <x:hidden property="codigo" value="<%= carrito[0].toString() %>"/>
                                <x:hidden property="acceso" value="Actualizar Articulo"/>
                                <button type="submit" class="btn-actualizar"><i class="fas fa-check"></i></button>
                            </x:form>
                            <x:form action="PedidoControl" method="post">
                                <x:hidden property="codigo" value="<%= carrito[0].toString() %>"/>
                                <x:hidden property="acceso" value="Quitar Articulo"/>
                                <button type="submit" class="btn-eliminar"><i class="far fa-trash-alt"></i></button>
                            </x:form>
                            </td>
                        </tr>
                        <% } %>
                        <tr>
                            <td colspan="4" class="tabla-total">Total</td>
                            <td class="tabla-total2"><div class="total">S/ <%= total %></div></td>
                            <td class="tabla-total"></td>
                        </tr>
                    </table>
                    <% } %>
                    <table class="tabla-lista subtabla final">
                        <tr>
                            <x:form action="PedidoControl" method="post">
                                <th class="tabla-titulo"><x:submit property="acceso" styleClass="btn-nuevo" value="Nuevo Pedido"/></th>
                            </x:form>
                            <x:form action="PedidoControl" method="post">
                                <th class="tabla-titulo"><x:submit property="acceso" styleClass="btn-listar" value="Listar Articulos"/></th>
                            </x:form>
                            <% if (pg.getListaPed().size() > 0) { %>
                            <x:form action="PedidoControl" method="post">
                                <th><x:hidden property="codEmp" value="<%= empleado[0].toString() %>"/></th>
                                <th><x:hidden property="codCli" value="<%= cliente[0].toString() %>"/></th>
                                <th><x:hidden property="total" value="<%= String.valueOf(total) %>"/></th>
                                <th class="tabla-titulo"><x:submit property="acceso" styleClass="btn-guardar" value="Grabar Pedido"/></th>
                            </x:form>
                            <% } %>
                        </tr>
                    </table>
                </div>
                <% if (!pg.getMsg().equals("")) { %>
                    <div class="msg-noFind">
                        <span><%= pg.getMsg() %></span>
                    </div>
                <% } pg.setMsg(""); %>
                <div class="botones">
                    <input type="button" class="btn-back" value="Regresar" onclick="location.href='Menu.jsp'">
                </div>
            </div>
        </div>
    </body>
</html>
