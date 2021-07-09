package servicio;

import java.util.List;

public interface ClienteServicio {
    public Object[] buscar(String cod);
    public String grabar(String cod, String nom, String dir);
    public String actualizar(String cod, String nom, String dir);
    public String eliminar(String cod);
    public List listar();
}
