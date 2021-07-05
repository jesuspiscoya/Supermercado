package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.EmpleadoServicio;

public class AccesoControl extends org.apache.struts.action.Action {
    private EmpleadoServicio empSer;
    private PresentadorGeneral pg;

    public void setEmpSer(EmpleadoServicio empSer) {
        this.empSer = empSer;
    }

    public void setPg(PresentadorGeneral pg) {
        this.pg = pg;
    }
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Formulario f=(Formulario) form;
        Object[] fila=empSer.validar(f.getUsuario(), f.getPassword());
        request.getSession().setAttribute("pg", pg);
            
        if (fila!=null) {
            request.getSession().setAttribute("acceso", fila);
            return mapping.findForward("Menu");
        } else {
            pg.setMsg("Acceso no permitido");
            return mapping.findForward("Mensaje");
        }
    }
}
