package vista;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicio.EmpleadoServicioImp;

@WebServlet(name = "EmpleadoControl", urlPatterns = {"/EmpleadoControl"})

public class EmpleadoControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso=request.getParameter("acceso");
        
        if (acceso.equals("Grabar")) {
            String cod=request.getParameter("codigo");
            String nom=request.getParameter("nombre");
            String tip=request.getParameter("tipo");
            String usu=request.getParameter("usuario");
            String pass=request.getParameter("password");
            String msg=new EmpleadoServicioImp().grabar(cod, nom, tip, usu, pass);
            
            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("Mensaje.jsp");
        }
        
        if (acceso.equals("Iniciar Sesion")) {
            String usu=request.getParameter("usuario");
            String pass=request.getParameter("password");
            Object[] fila=new EmpleadoServicioImp().validar(usu, pass);
            
            if (fila!=null) {
                request.getSession().setAttribute("fila", fila);
                response.sendRedirect("Menu.jsp");
            } else {
                request.getSession().setAttribute("msg", "Acceso no permitido.");
                response.sendRedirect("Mensaje.jsp");
            }
        }
        
        if (acceso.equals("Buscar")) {
            String cod=request.getParameter("codigo");
            Object[] fila=new EmpleadoServicioImp().buscar(cod);
            
            if (fila!=null) {
                request.getSession().setAttribute("fila", fila);
                response.sendRedirect("EmpleadoEditar.jsp");
            } else {
                request.getSession().setAttribute("msg", "Usuario no existe.");
                response.sendRedirect("Mensaje.jsp");
            }
        }
        
        if (acceso.equals("Listar")) {
            List lista=new EmpleadoServicioImp().listar();
            request.getSession().setAttribute("lista", lista);
            response.sendRedirect("EmpleadoListar.jsp");
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
