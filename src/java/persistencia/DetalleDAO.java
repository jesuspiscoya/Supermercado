package persistencia;

import negocio.Linea;
import negocio.Orden;

public interface DetalleDAO {
    public String grabar(Orden ord, Linea lin);
}
