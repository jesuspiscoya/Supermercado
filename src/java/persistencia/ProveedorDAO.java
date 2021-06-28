package persistencia;

import java.util.List;
import negocio.Proveedor;

public interface ProveedorDao {
    public Object[] buscar(String cod);
    public String grabar(Proveedor pro);
    public String actualizar(Proveedor pro);
    public String eliminar(String cod);
    public List listar();
}
