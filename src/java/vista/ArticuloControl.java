package vista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicio.ArticuloServicioImp;

@WebServlet(name = "ArticuloControl", urlPatterns = {"/ArticuloControl"})
public class ArticuloControl extends HttpServlet {
    PedidoPresentador pedPre;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso=request.getParameter("acceso");
        
        if (acceso.equals("Nuevo Articulo")) {
            pedPre=new PedidoPresentador();
            
            request.getSession().setAttribute("pedPre", pedPre);
            response.sendRedirect("ArticuloGrabar.jsp");
        }
        
        if (acceso.equals("Grabar")) {
            String cod=request.getParameter("codigo");
            String nom=request.getParameter("nombre");
            Double pre=Double.parseDouble(request.getParameter("precio"));
            Integer sto=Integer.parseInt(request.getParameter("stock"));
            String msg=new ArticuloServicioImp().grabar(cod, nom, pre, sto);
            pedPre.setMsg(msg);
            
            response.sendRedirect("ArticuloGrabar.jsp");
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
