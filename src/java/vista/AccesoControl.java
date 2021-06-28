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
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        EmpleadoFormulario ef=(EmpleadoFormulario) form;
        Object[] fila=empSer.validar(ef.getUsuario(), ef.getPassword());
            
        if (fila!=null) {
            request.getSession().setAttribute("validar", fila);
            return mapping.findForward("Menu");
        } else {
            pg=new PresentadorGeneral();
            pg.setMsg("Acceso no permitido");
            request.getSession().setAttribute("pg", pg);
            return mapping.findForward("Mensaje");
        }
    }
}
