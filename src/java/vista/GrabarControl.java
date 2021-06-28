package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.ProveedorServicio;

public class GrabarControl extends org.apache.struts.action.Action {
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
        
        EmpleadoFormulario ef=(EmpleadoFormulario) form;
        pg=new PresentadorGeneral();
        request.getSession().setAttribute("pg", pg);
        
        if (request.getParameter("grabar").equals("Empleado")) {
            pg.setMsg(empSer.grabar(ef.getCodigo(), ef.getNombre(), ef.getTipo(), ef.getUsuario(), ef.getPassword()));
            request.getSession().setAttribute("id", "Empleado");
            return mapping.findForward("Grabar");
        } else if (request.getParameter("grabar").equals("Cliente")) {
            pg.setMsg(cliSer.grabar(ef.getCodigo(), ef.getNombre(), ef.getDireccion()));
            request.getSession().setAttribute("id", "Cliente");
            return mapping.findForward("Grabar");
        } else {
            pg.setMsg(proSer.grabar(ef.getCodigo(), ef.getNombre(), ef.getDireccion()));
            request.getSession().setAttribute("id", "Proveedor");
            return mapping.findForward("Grabar");
        }
    }
}
