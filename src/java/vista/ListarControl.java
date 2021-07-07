package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.OrdenServicio;
import servicio.PedidoServicio;
import servicio.ProveedorServicio;

public class ListarControl extends org.apache.struts.action.Action {
    private EmpleadoServicio empSer;
    private ClienteServicio cliSer;
    private ProveedorServicio proSer;
    private PedidoServicio pedSer;
    private OrdenServicio ordSer;
    private PresentadorGeneral pg;

    public void setEmpSer(EmpleadoServicio empSer) {
        this.empSer = empSer;
    }

    public void setCliSer(ClienteServicio cliSer) {
        this.cliSer = cliSer;
    }

    public void setProSer(ProveedorServicio proSer) {
        this.proSer = proSer;
    }

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
        
        request.getSession().setAttribute("pg", pg);
        
        if (request.getParameter("acceso").equals("Listar")) {
            if (request.getParameter("listar").equals("Empleado")) {
                pg.setLista(empSer.listar());
                pg.setMsg("Empleados");
            } if (request.getParameter("listar").equals("Cliente")) {
                pg.setLista(cliSer.listar());
                pg.setMsg("Clientes");
            } if (request.getParameter("listar").equals("Proveedor")) {
                pg.setLista(proSer.listar());
                pg.setMsg("Proveedor");
            }
            return mapping.findForward("Listar");
        } else if (request.getParameter("acceso") != null) {
            if (request.getParameter("acceso").equals("Nuevo Pedido")) {
                Object[] cliente={"","",""};
                pg.setCliente(cliente);
                pg.getLista().clear();
                pg.setPedOrd(pedSer.nuevoPedido());
                request.getSession().setAttribute("rol", "Pedido");
            } else {
                Object[] proveedor={"","",""};
                pg.setProveedor(proveedor);
                pg.getLista().clear();
                pg.setPedOrd(ordSer.nuevaOrden());
                request.getSession().setAttribute("rol", "Orden");
            } return mapping.findForward("PedOrd");
        } else {
            request.getSession().invalidate();
            return mapping.findForward("Salir");
        }
    }
}
