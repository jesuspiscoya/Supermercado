package persistencia;

import java.util.List;
import negocio.Orden;

public interface OrdenDao {
    public String grabar(Orden ord);
    public List listarNum();
}
