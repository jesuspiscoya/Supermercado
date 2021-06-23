package vista;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicio.OrdenServicio;
import servicio.OrdenServicioImp;

@WebServlet(name = "OrdenControl", urlPatterns = {"/OrdenControl"})
public class OrdenControl extends HttpServlet {
    private PedidoPresentador pedPre;
    private OrdenServicio ordSer;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso=request.getParameter("acceso");
        
        if (acceso.equals("Nueva Orden")) {
            pedPre=new PedidoPresentador();
            ordSer=new OrdenServicioImp();
            String cod=request.getParameter("codigo");
            Object[] fila=ordSer.nuevaOrden(cod);
            pedPre.setFila(fila);
            
            request.getSession().setAttribute("pedPre", pedPre);
            response.sendRedirect("OrdenGUI.jsp");
        }
        
        if (acceso.equals("Listar Articulos")) {
            List lista=ordSer.listarArticulos();
            pedPre.setLista(lista);
            
            request.getSession().setAttribute("control", "orden");
            response.sendRedirect("ArticuloListar.jsp");
        }
        
        if (acceso.equals("Agregar Articulo")) {
            String cod=request.getParameter("codigo");
            String nom=request.getParameter("nombre");
            String pre=request.getParameter("precio");
            String cant=request.getParameter("cantidad");
            List lista=ordSer.agregarArticulo(cod, nom, pre, cant);
            pedPre.setLista(lista);
            
            response.sendRedirect("OrdenGUI.jsp");
        }
        
        if (acceso.equals("Quitar Articulo")) {
            String cod=request.getParameter("codigo");
            List lista=ordSer.quitarOrden(cod);
            pedPre.setLista(lista);
            
            response.sendRedirect("OrdenGUI.jsp");
        }
        
        if (acceso.equals("Buscar")) {
            String cod=request.getParameter("codProv");
            Object[] fila=ordSer.buscarProveedor(cod);
            if (fila!=null)
                pedPre.setFila2(fila);
            else
                pedPre.setMsg("El proveedor no existe en la Base de Datos.");
            
            response.sendRedirect("OrdenGUI.jsp");
        }
        
        if (acceso.equals("Grabar Orden")) {
            String cod=request.getParameter("codProv");
            String msg=ordSer.grabarOrden(cod);
            /*
            pedPre=new PedidoPresentador();
            ordSer=new OrdenServicioImp();
            Object[] fila=ordSer.nuevaOrden(cod);
            pedPre.setFila(fila);
            */
            pedPre.setMsg(msg);
            
            request.getSession().setAttribute("msgDetalle", msg);
            request.getSession().setAttribute("pedPre", pedPre);
            response.sendRedirect("DetalleOrden.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
