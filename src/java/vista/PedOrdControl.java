package vista;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.OrdenServicio;
import servicio.PedidoServicio;

public class PedOrdControl extends org.apache.struts.action.Action {
    private PedidoServicio pedSer;
    private OrdenServicio ordSer;
    private PresentadorGeneral pg;

    public void setPedSer(PedidoServicio pedSer) {
        this.pedSer = pedSer;
    }

    public void setOrdSer(OrdenServicio ordSer) {
        this.ordSer = ordSer;
    }

    public void setPg(PresentadorGeneral pg) {
        this.pg = pg;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Formulario f=(Formulario) form;
        request.getSession().setAttribute("pg", pg);
        
        if (request.getParameter("acceso").equals("Buscar")) {
            if (request.getSession().getAttribute("rol").equals("Pedido")) {
                Object[] fila=pedSer.buscarCliente(f.getCodigo());
                if (fila!=null)
                    pg.setCliente(fila);
                else {
                    Object[] cliente={"","",""};
                    pg.setCliente(cliente);
                    pg.setMsg("El cliente no existe en la Base de Datos");
                }
            } else {
                Object[] fila=ordSer.buscarProveedor(f.getCodigo());
                if (fila!=null)
                    pg.setProveedor(fila);
                else {
                    Object[] proveedor={"","",""};
                    pg.setProveedor(proveedor);
                    pg.setMsg("El Proveedor no existe en la Base de Datos");
                }
            }
        } else if (request.getParameter("acceso").equals("Nuevo Pedido")) {
            Object[] cliente={"","",""};
            pg.setCliente(cliente);
            pg.getLista().clear();
            pg.setPedOrd(pedSer.nuevoPedido());
            request.getSession().setAttribute("rol", "Pedido");
        } else if (request.getParameter("acceso").equals("Nueva Orden")) {
            Object[] proveedor={"","",""};
            pg.setProveedor(proveedor);
            pg.getLista().clear();
            pg.setPedOrd(ordSer.nuevaOrden());
            request.getSession().setAttribute("rol", "Orden");
        } else if (request.getParameter("acceso").equals("Agregar Articulo")) {
            List lista=pedSer.agregarArticulo(f.getCodigo(), f.getNombre(), Double.parseDouble(f.getPrecio()));
            pg.setLista(lista);
            pg.setMsg("Articulo añadido");
        } else if (request.getParameter("acceso").equals("Actualizar Articulo")) {
            pg.setLista(pedSer.actualizarArticulo(f.getCodigo(), Integer.parseInt(f.getCantidad())));
            pg.setMsg("Cesta actualizada");
        } else if (request.getParameter("acceso").equals("Quitar Articulo")) {
            pg.setLista(pedSer.quitarArticulo(f.getCodigo()));
            pg.setMsg("Articulo removido");
        } else if (request.getParameter("acceso").equals("Grabar Pedido")) {
            pg.setMsg(pedSer.grabarPedido(f.getCodEmp(), f.getCod(), f.getTotal()));
            pg.setPedOrd(pedSer.nuevoPedido());
            Object[] cliente={"","",""};
            pg.setCliente(cliente);
            pg.getLista().clear();
        } else if (request.getParameter("acceso").equals("Grabar Orden")) {
            pg.setMsg(ordSer.grabarOrden(f.getCodEmp(), f.getCod(), f.getTotal()));
            pg.setPedOrd(ordSer.nuevaOrden());
            Object[] proveedor={"","",""};
            pg.setProveedor(proveedor);
            pg.getLista().clear();
        } else {
            pg.setMsg("Articulos");
            pg.setLista(pedSer.listarArticulos());
            return mapping.findForward("Articulos");
        } return mapping.findForward("PedOrd");
    }
}
