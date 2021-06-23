package persistencia;

import java.util.List;
import negocio.Articulo;

public interface ArticuloDAO {
    public List listar();
    public String grabar(Articulo art);
}
