package vista;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.PedidoServicio;

public class PedidoControl extends org.apache.struts.action.Action {
    private PedidoServicio pedSer;
    private PresentadorGeneral pg;

    public void setPedSer(PedidoServicio pedSer) {
        this.pedSer = pedSer;
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
            Object[] fila=pedSer.buscarCliente(f.getCodigo());
            if (fila!=null)
                pg.setCliente(fila);
            else {
                Object[] cliente={"","",""};
                pg.setCliente(cliente);
                pg.setMsg("El cliente no existe en la Base de Datos");
            }
            return mapping.findForward("Pedido");
        } else if (request.getParameter("acceso").equals("Actualizar Articulo")) {
            pg.setListaPed(pedSer.actualizarArticulo(f.getCodigo(), Integer.parseInt(f.getCantidad())));
            return mapping.findForward("Pedido");
        } else if (request.getParameter("acceso").equals("Quitar Articulo")) {
            pg.setListaPed(pedSer.quitarArticulo(f.getCodigo()));
            return mapping.findForward("Pedido");
        } else if (request.getParameter("acceso").equals("Listar Articulos")) {
            pg.setMsg("Articulos");
            pg.setLista(pedSer.listarArticulos());
            return mapping.findForward("Articulos");
        } else if (request.getParameter("acceso").equals("Grabar Pedido")) {
            pg.setMsg(pedSer.grabarPedido(f.getCodEmp(), f.getCodCli(), f.getTotal()));
            pg.setPedido(pedSer.nuevoPedido());
            return mapping.findForward("Pedido");
        }
        else {
            Object[] cliente={"","",""};
            pg.setCliente(cliente);
            pg.getListaPed().clear();
            return mapping.findForward("Pedido");
        }
    }
}
