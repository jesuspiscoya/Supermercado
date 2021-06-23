package persistencia;

import java.util.List;
import negocio.Empleado;

public interface EmpleadoDAO {
    public String grabar(Empleado emp);
    public String actualizar(Empleado emp);
    public String eliminar(Empleado emp);
    public Object[] buscar(String cod);
    public List listar();
    public Object[] validar(String usu, String pass);
}
