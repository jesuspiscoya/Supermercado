<%-- 
    Document   : NavBar
    Created on : May 20, 2021, 11:20:15 PM
    Author     : jesus
--%>

<%@page import="vista.PresentadorGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<header>
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
                                <a href="Eliminar.jsp?id=Empleado">Eliminar</a>
                            </li>
                            <li>
                                <x:form action="CRUDControl" method="post">
                                    <x:hidden property="listar" value="Empleado"/>
                                    <x:submit property="acceso" value="Listar"/>
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
                                <a href="Eliminar.jsp?id=Cliente">Eliminar</a>
                            </li>
                            <li>
                                <x:form action="CRUDControl" method="post">
                                    <x:hidden property="listar" value="Cliente"/>
                                    <x:submit property="acceso" value="Listar"/>
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
                                <a href="Eliminar.jsp?id=Proveedor">Eliminar</a>
                            </li>
                            <li>
                                <x:form action="CRUDControl" method="post">
                                    <x:hidden property="listar" value="Proveedor"/>
                                    <x:submit property="acceso" value="Listar"/>
                                </x:form>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="Grabar.jsp?id=Articulo">Nuevo Articulo</a>
                    </li>
                </ul>
            </li>
            <li>
                <span>Procesos</span>
                <div class="border"></div>
                <ul>
                    <li>
                        <x:form action="PedOrdControl" method="post">
                            <x:submit property="acceso" value="Nuevo Pedido"/>
                        </x:form>
                    </li>
                    <li>
                        <x:form action="PedOrdControl" method="post">
                            <x:submit property="acceso" value="Nueva Orden"/>
                        </x:form>
                    </li>
                </ul>
            </li>
            <li>
                <span>Consultas</span>
                <div class="border"></div>
            </li>
        </ul>
    </nav>
    <x:form action="AccesoControl" method="post">
        <x:submit property="acceso" styleClass="btn-salir" value="Salir"/>
    </x:form>
</header>