<%-- 
    Document   : NavBar
    Created on : May 20, 2021, 11:20:15 PM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<header>
    <% Object[] fila=(Object[])session.getAttribute("validar"); %>
    
    <div class="inicio">
        <a href="Menu.jsp">Supermercados Piscoya</a>
        <div class="border"></div>
    </div>
    <nav>
        <ul>
            <li>
                <span>Archivos</span>
                <div class="border"></div>
                <ul>
                    <li>
                        <span>Empleado</span>
                        <ul>
                            <li>
                                <a href="Buscar.jsp?id=Empleado">Buscar</a>
                            </li>
                            <li>
                                <x:form action="ListarControl" method="post">
                                    <x:hidden property="listar" value="Empleado"/>
                                    <x:submit value="Listar"/>
                                </x:form>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>Cliente</span>
                        <ul>
                            <li>
                                <a href="Grabar.jsp?id=Cliente">Registrar</a>
                            </li>
                            <li>
                                <a href="Buscar.jsp?id=Cliente">Buscar</a>
                            </li>
                            <li>
                                <x:form action="ListarControl" method="post">
                                    <x:hidden property="listar" value="Cliente"/>
                                    <x:submit value="Listar"/>
                                </x:form>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>Proveedor</span>
                        <ul>
                            <li>
                                <a href="Grabar.jsp?id=Proveedor">Registrar</a>
                            </li>
                            <li>
                                <a href="Buscar.jsp?id=Proveedor">Buscar</a>
                            </li>
                            <li>
                                <x:form action="ListarControl" method="post">
                                    <x:hidden property="listar" value="Proveedor"/>
                                    <x:submit value="Listar"/>
                                </x:form>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <form action="ArticuloControl" method="post">
                            <input type="submit" name="acceso" value="Nuevo Articulo">
                        </form>
                    </li>
                </ul>
            </li>
            <!--
            <li>
                <span>Procesos</span>
                <div class="border"></div>
                <ul>
                    <li>
                        <form action="PedidoControl" method="post">
                            <input type="hidden" name="codigo" value='<%= fila[0] %>'>
                            <input type="submit" name="acceso" value="Nuevo Pedido">
                        </form>
                    </li>
                    <li>
                        <form action="OrdenControl" method="post">
                            <input type="hidden" name="codigo" value='<%= fila[0] %>'>
                            <input type="submit" name="acceso" value="Nueva Orden">
                        </form>
                    </li>
                </ul>
            </li>
            -->
            <li>
                <span>Consultas</span>
                <div class="border"></div>
            </li>
        </ul>
    </nav>
    <a class="btn-salir" href="Portal.jsp">Salir</a>
</header>