package servicio;

import java.util.List;

public interface ArticuloServicio {
    public Object[] buscar(String cod);
    public String grabar(String cod, String nom, double prec, int sto);
    public String actualizar(String cod, String nom, double prec, int sto);
    public String eliminar(String cod);
    public List listar();
}
