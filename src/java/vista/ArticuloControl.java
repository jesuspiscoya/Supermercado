package vista;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servicio.PedidoServicio;

public class ArticuloControl extends org.apache.struts.action.Action {
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
        List lista=pedSer.agregarArticulo(f.getCodigo(), f.getNombre(), Double.parseDouble(f.getPrecio()));
        pg.setLista(lista);
        return mapping.findForward("PedOrd");
    }
}
