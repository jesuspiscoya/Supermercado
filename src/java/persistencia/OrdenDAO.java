package persistencia;

import java.util.List;
import negocio.Orden;

public interface OrdenDAO {
    public String grabar(Orden ord);
    public List listarNum();
}
