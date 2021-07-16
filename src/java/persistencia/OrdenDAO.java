package persistencia;

import java.util.List;
import negocio.Detalleorden;
import negocio.Orden;

public interface OrdenDao {
    public String grabar(Orden ord);
    public String grabarDetalle(Detalleorden detOrd);
    public List listarNum();
}
