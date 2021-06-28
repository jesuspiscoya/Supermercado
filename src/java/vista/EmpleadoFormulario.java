package vista;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class EmpleadoFormulario extends org.apache.struts.action.ActionForm {
    
    private String codigo, nombre, tipo, direccion, usuario, password;

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
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
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
        return errors;
    }
}
