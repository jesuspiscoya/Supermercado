package servicio;

import java.util.List;

public interface EmpleadoServicio {
    public Object[] validar(String usu, String pass);
    public Object[] buscar(String cod);
    public String grabar(String cod, String nom, String tip, String usu, String pass);
    public String actualizar(String cod, String nom, String tip, String usu, String pass);
    public String eliminar(String cod);
    public List listar();
}
