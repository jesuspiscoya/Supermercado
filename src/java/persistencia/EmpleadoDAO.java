package persistencia;

import negocio.Empleado;
import java.util.List;

public interface EmpleadoDao {
    public Object[] validar(String usu, String pass);
    public Object[] buscar(String cod);
    public String grabar(Empleado emp);
    public String actualizar(Empleado emp);
    public String eliminar(String cod);
    public List listar();
}
