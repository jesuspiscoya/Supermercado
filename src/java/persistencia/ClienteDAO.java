package persistencia;

import java.util.List;
import negocio.Cliente;

public interface ClienteDao {
    public Object[] buscar(String cod);
    public String grabar(Cliente cli);
    public String actualizar(Cliente cli);
    public String eliminar(String cod);
    public List listar();
}
