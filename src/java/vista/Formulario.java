package vista;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class Formulario extends org.apache.struts.action.ActionForm {
    
    private String codigo, nombre, tipo, direccion, usuario, password, precio, stock, cantidad, codEmp, cod;
    double total;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        if (request.getParameter("form") != null) {
            if (request.getParameter("form").equals("Empleado"))
                request.getSession().setAttribute("id", "Empleado");
            if (request.getParameter("form").equals("Cliente"))
                request.getSession().setAttribute("id", "Cliente");
            if (request.getParameter("form").equals("Proveedor"))
                request.getSession().setAttribute("id", "Proveedor");
            if (request.getParameter("form").equals("Articulo"))
                request.getSession().setAttribute("id", "Articulo");
        }
        ActionErrors errors = new ActionErrors();
        if (request.getParameter("codigo") != null)
            if (codigo == null || codigo.length() < 1)
                errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("nombre") != null)
            if (nombre == null || nombre.length() < 1)
                errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("tipo") != null)
            if (tipo == null || tipo.length() < 1)
                errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("direccion") != null)
            if (direccion == null || direccion.length() < 1)
                errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("usuario") != null)
            if (usuario == null || usuario.length() < 1)
               errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("password") != null)
            if (password == null || password.length() < 1)
               errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("precio") != null)
            if (precio == null || precio.length() < 1)
               errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("stock") != null)
            if (stock == null || stock.length() < 1)
               errors.add("name", new ActionMessage("error.name.required"));
        if (request.getParameter("cantidad") != null)
            if (cantidad == null || cantidad.length() < 1)
               errors.add("name", new ActionMessage("error.name.required"));
        return errors;
    }
}
