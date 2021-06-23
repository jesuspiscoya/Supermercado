package persistencia;

import java.util.List;
import negocio.Cliente;

public interface ClienteDAO {
    public String grabar(Cliente cli);
    public String actualizar(Cliente cli);
    public String eliminar(Cliente cli);
    public Object[] buscar(String cod);
    public List listar();
}
