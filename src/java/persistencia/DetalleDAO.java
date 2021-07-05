package persistencia;

import negocio.Orden;

public interface DetalleDao {
    public String grabar(Orden ord);
}
