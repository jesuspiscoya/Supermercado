package persistencia;

import java.util.List;
import negocio.Articulo;

public interface ArticuloDao {
    public String grabar(Articulo art);
    public List listar();
}
