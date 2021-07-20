package persistencia;

import java.util.List;
import negocio.Articulo;

public interface ArticuloDao {
    public Object[] buscar(String cod);
    public String grabar(Articulo art);
    public String actualizar(Articulo art);
    public String eliminar(String cod);
    public List listar();
}
