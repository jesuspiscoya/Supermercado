package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ArticuloServicio;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.OrdenServicio;
import servicio.PedidoServicio;
import servicio.ProveedorServicio;

public class CRUDControl extends org.apache.struts.action.Action {
    private EmpleadoServicio empSer;
    private ClienteServicio cliSer;
    private ProveedorServicio proSer;
    private ArticuloServicio artSer;
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

    public void setArtSer(ArticuloServicio artSer) {
        this.artSer = artSer;
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
        
        Formulario f=(Formulario) form;
        request.getSession().setAttribute("pg", pg);
        
        if (request.getParameter("acceso").equals("Buscar")) {
            if (request.getParameter("form").equals("Empleado")) {
                Object[] fila=empSer.buscar(f.getCodigo());
                if (fila!=null) {
                    pg.setEmpleado(fila);
                    request.getSession().setAttribute("id", "Empleado");
                } else {
                    pg.setMsg("Empleado no existe");
                    request.getSession().setAttribute("id", "Empleado");
                    return mapping.findForward("Mensaje");
                }
            } else if (request.getParameter("form").equals("Cliente")) {
                Object[] fila=cliSer.buscar(f.getCodigo());
                if (fila!=null) {
                    pg.setCliente(fila);
                    request.getSession().setAttribute("id", "Cliente");
                } else {
                    pg.setMsg("Cliente no existe");
                    request.getSession().setAttribute("id", "Cliente");
                    return mapping.findForward("Mensaje");
                }
            } else {
                Object[] fila=proSer.buscar(f.getCodigo());
                if (fila!=null) {
                    pg.setProveedor(fila);
                    request.getSession().setAttribute("id", "Proveedor");
                } else {
                    pg.setMsg("Proveedor no existe");
                    request.getSession().setAttribute("id", "Proveedor");
                    return mapping.findForward("Mensaje");
                }
            } return mapping.findForward("Buscar");
        } else if (request.getParameter("acceso").equals("Grabar")) {
            if (request.getParameter("form").equals("Empleado")) {
                pg.setMsg(empSer.grabar(f.getCodigo(), f.getNombre(), f.getTipo(), f.getUsuario(), f.getPassword()));
                request.getSession().setAttribute("id", "Empleado");
            } else if (request.getParameter("form").equals("Cliente")) {
                pg.setMsg(cliSer.grabar(f.getCodigo(), f.getNombre(), f.getDireccion()));
                request.getSession().setAttribute("id", "Cliente");
            } else if (request.getParameter("form").equals("Proveedor")) {
                pg.setMsg(proSer.grabar(f.getCodigo(), f.getNombre(), f.getDireccion()));
                request.getSession().setAttribute("id", "Proveedor");
            } else {
                pg.setMsg(artSer.grabar(f.getCodigo(), f.getNombre(), Double.parseDouble(f.getPrecio()), Integer.parseInt(f.getStock())));
                request.getSession().setAttribute("id", "Articulo");
            } return mapping.findForward("Grabar");
        } else if (request.getParameter("acceso").equals("Actualizar")) {
            if (request.getParameter("form").equals("Empleado")) {
                pg.setMsg(empSer.actualizar(f.getCodigo(), f.getNombre(), f.getTipo(), f.getUsuario(), f.getPassword()));
                pg.setEmpleado(empSer.buscar(f.getCodigo()));
                request.getSession().setAttribute("id", "Empleado");
            } else if (request.getParameter("form").equals("Cliente")) {
                pg.setMsg(cliSer.actualizar(f.getCodigo(), f.getNombre(), f.getDireccion()));
                pg.setCliente(cliSer.buscar(f.getCodigo()));
                request.getSession().setAttribute("id", "Cliente");
            } else {
                pg.setMsg(proSer.actualizar(f.getCodigo(), f.getNombre(), f.getDireccion()));
                pg.setProveedor(proSer.buscar(f.getCodigo()));
                request.getSession().setAttribute("id", "Proveedor");
            } return mapping.findForward("Actualizar");
        } else if (request.getParameter("acceso").equals("Eliminar")) {
            if (request.getParameter("form").equals("Empleado")) {
                pg.setMsg(empSer.eliminar(f.getCodigo()));
                request.getSession().setAttribute("id", "Empleado");
            } else if (request.getParameter("form").equals("Cliente")) {
                pg.setMsg(cliSer.eliminar(f.getCodigo()));
                request.getSession().setAttribute("id", "Cliente");
            } else {
                pg.setMsg(proSer.eliminar(f.getCodigo()));
                request.getSession().setAttribute("id", "Proveedor");
            } return mapping.findForward("Eliminar");
        } else {
            if (request.getParameter("listar").equals("Empleado")) {
                pg.setLista(empSer.listar());
                pg.setMsg("Empleados");
            } if (request.getParameter("listar").equals("Cliente")) {
                pg.setLista(cliSer.listar());
                pg.setMsg("Clientes");
            } if (request.getParameter("listar").equals("Proveedor")) {
                pg.setLista(proSer.listar());
                pg.setMsg("Proveedor");
            } return mapping.findForward("Listar");
        }
    }
}
