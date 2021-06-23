package vista;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicio.PedidoServicio;
import servicio.PedidoServicioImp;

@WebServlet(name = "PedidoControl", urlPatterns = {"/PedidoControl"})
public class PedidoControl extends HttpServlet {
    private PedidoPresentador pedPre;
    private PedidoServicio pedSer;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acceso=request.getParameter("acceso");
        
        if (acceso.equals("Nuevo Pedido")) {
            pedPre=new PedidoPresentador();
            pedSer=new PedidoServicioImp();
            String cod=request.getParameter("codigo");
            Object[] fila=pedSer.nuevoPedido(cod);
            pedPre.setFila(fila);
            
            request.getSession().setAttribute("pedPre", pedPre);
            response.sendRedirect("PedidoGUI.jsp");
        }
        
        if (acceso.equals("Listar Articulos")) {
            List lista=pedSer.listarArticulos();
            pedPre.setLista(lista);
            
            request.getSession().setAttribute("control", "pedido");
            response.sendRedirect("ArticuloListar.jsp");
        }
        
        if (acceso.equals("Agregar Articulo")) {
            String cod=request.getParameter("codigo");
            String nom=request.getParameter("nombre");
            String pre=request.getParameter("precio");
            String cant=request.getParameter("cantidad");
            List lista=pedSer.agregarArticulo(cod, nom, pre, cant);
            pedPre.setLista(lista);
            
            response.sendRedirect("PedidoGUI.jsp");
        }
        
        if (acceso.equals("Quitar Articulo")) {
            String cod=request.getParameter("codigo");
            List lista=pedSer.quitarPedido(cod);
            pedPre.setLista(lista);
            
            response.sendRedirect("PedidoGUI.jsp");
        }
        
        if (acceso.equals("Buscar")) {
            String cod=request.getParameter("codCli");
            Object[] fila=pedSer.buscarCliente(cod);
            if (fila!=null)
                pedPre.setFila2(fila);
            else
                pedPre.setMsg("El cliente no existe en la Base de Datos.");
            
            response.sendRedirect("PedidoGUI.jsp");
        }
        
        if (acceso.equals("Grabar Pedido")) {
            String cod=request.getParameter("codCli");
            String msg=pedSer.grabarPedido(cod);
            pedPre=new PedidoPresentador();
            pedSer=new PedidoServicioImp();
            Object[] fila=pedSer.nuevoPedido(cod);
            pedPre.setFila(fila);
            pedPre.setMsg(msg);
            
            response.sendRedirect("PedidoGUI.jsp");
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
