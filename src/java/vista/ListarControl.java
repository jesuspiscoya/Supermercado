package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.ProveedorServicio;

public class ListarControl extends org.apache.struts.action.Action {
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
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        pg=new PresentadorGeneral();
        request.getSession().setAttribute("pg", pg);
        
        if (request.getParameter("listar").equals("Empleado")) {
            pg.setLista(empSer.listar());
            pg.setMsg("Empleados");
            return mapping.findForward("Listar");
        } else if (request.getParameter("listar").equals("Cliente")) {
            pg.setLista(cliSer.listar());
            pg.setMsg("Clientes");
            return mapping.findForward("Listar");
        } else if (request.getParameter("listar").equals("Proveedor")) {
            pg.setLista(proSer.listar());
            pg.setMsg("Proveedor");
            return mapping.findForward("Listar");
        } else {
            request.getSession().invalidate();
            return mapping.findForward("Salir");
        }
    }
}
