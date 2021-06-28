package servicio;

import java.util.List;

public interface ProveedorServicio {
    public Object[] buscar(String cod);
    public String grabar(String cod, String nom, String dir);
    public List listar();
}
