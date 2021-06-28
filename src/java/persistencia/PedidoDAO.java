package persistencia;

import java.util.List;
import negocio.Pedido;

public interface PedidoDao {
    public String grabar(Pedido ped);
    public List listarNum();
}
