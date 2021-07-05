package vista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.ArticuloServicio;
import servicio.ClienteServicio;
import servicio.EmpleadoServicio;
import servicio.ProveedorServicio;

public class GrabarControl extends org.apache.struts.action.Action {
    private EmpleadoServicio empSer;
    private ClienteServicio cliSer;
    private ProveedorServicio proSer;
    private ArticuloServicio artSer;
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
            pg.setMsg(empSer.grabar(f.getCodigo(), f.getNombre(), f.getTipo(), f.getUsuario(), f.getPassword()));
            return mapping.findForward("GrabarE");
        } else if (request.getParameter("form").equals("Cliente")) {
            pg.setMsg(cliSer.grabar(f.getCodigo(), f.getNombre(), f.getDireccion()));
            return mapping.findForward("GrabarC");
        } else if (request.getParameter("form").equals("Proveedor")) {
            pg.setMsg(proSer.grabar(f.getCodigo(), f.getNombre(), f.getDireccion()));
            return mapping.findForward("GrabarP");
        } else {
            pg.setMsg(artSer.grabar(f.getCodigo(), f.getNombre(), Double.parseDouble(f.getPrecio()), Integer.parseInt(f.getStock())));
            return mapping.findForward("GrabarA");
        }
    }
}
