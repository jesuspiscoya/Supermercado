package servicio;

import java.util.List;

public interface ClienteServicio {
    public String grabar(String cod, String nom, String dir);
    public Object[] buscar(String cod);
    public List listar();
}
