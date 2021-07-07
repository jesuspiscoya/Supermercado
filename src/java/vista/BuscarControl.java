package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.ProveedorServicio;

public class BuscarControl extends org.apache.struts.action.Action {
    private EmpleadoServicio empSer;
    private ClienteServicio cliSer;
    private ProveedorServicio proSer;
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

    public void setPg(PresentadorGeneral pg) {
        this.pg = pg;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Formulario f=(Formulario) form;
        request.getSession().setAttribute("pg", pg);
        
        if (request.getParameter("form").equals("Empleado")) {
            Object[] fila=empSer.buscar(f.getCodigo());
            if (fila!=null) {
                pg.setEmpleado(fila);
                request.getSession().setAttribute("id", "Empleado");
                return mapping.findForward("Buscar");
            } else {
                pg.setMsg("Empleado no existe");
                request.getSession().setAttribute("id", "Empleado");
                return mapping.findForward("Mensaje");
            }
        } else if (request.getParameter("form").equals("Cliente")) {
            Object[] fila=cliSer.buscar(f.getCodigo());
            if (fila!=null) {
                pg.setCliente(fila);
                request.getSession().setAttribute("id", "Empleado");
                return mapping.findForward("Buscar");
            } else {
                pg.setMsg("Cliente no existe");
                request.getSession().setAttribute("id", "Cliente");
                return mapping.findForward("Mensaje");
            }
        } else {
            Object[] fila=proSer.buscar(f.getCodigo());
            if (fila!=null) {
                pg.setProveedor(fila);
                request.getSession().setAttribute("id", "Empleado");
                return mapping.findForward("Buscar");
            } else {
                pg.setMsg("Proveedor no existe");
                request.getSession().setAttribute("id", "Proveedor");
                return mapping.findForward("Mensaje");
            }
        }
    }
}
