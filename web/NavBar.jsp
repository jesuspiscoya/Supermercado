<%-- 
    Document   : NavBar
    Created on : May 20, 2021, 11:20:15 PM
    Author     : jesus
--%>

<header>
    <% Object[] fila=(Object[])session.getAttribute("fila"); %>
    
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
                                <a href="EmpleadoBuscar.jsp">Buscar</a>
                            </li>
                            <li>
                                <form action="EmpleadoControl" method="post"><input type="submit" name="acceso" value="Listar"></form>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>Cliente</span>
                        <ul>
                            <li>
                                <a href="ClienteGrabar.jsp">Registrar</a>
                            </li>
                            <li>
                                <a href="ClienteBuscar.jsp">Buscar</a>
                            </li>
                            <li>
                                <form action="ClienteControl" method="post"><input type="submit" name="acceso" value="Listar"></form>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>Proveedor</span>
                        <ul>
                            <li>
                                <a href="ProveedorGrabar.jsp">Registrar</a>
                            </li>
                            <li>
                                <a href="ProveedorBuscar.jsp">Buscar</a>
                            </li>
                            <li>
                                <form action="ProveedorControl" method="post"><input type="submit" name="acceso" value="Listar"></form>
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
            <li>
                <span>Consultas</span>
                <div class="border"></div>
            </li>
        </ul>
    </nav>
    <a class="btn-salir" href="Portal.jsp">Salir</a>
</header>