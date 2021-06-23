package persistencia;

import java.util.List;
import negocio.Proveedor;

public interface ProveedorDAO {
    public String grabar(Proveedor prov);
    public String actualizar(Proveedor prov);
    public String eliminar(Proveedor prov);
    public Object[] buscar(String cod);
    public List listar();
}
