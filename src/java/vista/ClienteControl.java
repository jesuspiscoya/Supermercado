package vista;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicio.ClienteServicioImp;

@WebServlet(name = "ClienteControl", urlPatterns = {"/ClienteControl"})
public class ClienteControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso=request.getParameter("acceso");
        
        if (acceso.equals("Grabar")) {
            String cod=request.getParameter("codigo");
            String nom=request.getParameter("nombre");
            String dir=request.getParameter("direccion");
            String msg=new ClienteServicioImp().grabar(cod, nom, dir);
            
            request.getSession().setAttribute("msgC", msg);
            response.sendRedirect("ClienteGrabar.jsp");
        }
        
        if (acceso.equals("Buscar")) {
            String cod=request.getParameter("codigo");
            Object[] fila=new ClienteServicioImp().buscar(cod);
            
            if (fila!=null) {
                request.getSession().setAttribute("fila", fila);
                response.sendRedirect("ClienteEditar.jsp");
            } else {
                request.getSession().setAttribute("msg", "Usuario no existe.");
                response.sendRedirect("Mensaje.jsp");
            }
        }
        
        if (acceso.equals("Listar")) {
            List lista=new ClienteServicioImp().listar();
            request.getSession().setAttribute("lista", lista);
            response.sendRedirect("ClienteListar.jsp");
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
