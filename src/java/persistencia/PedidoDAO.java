package persistencia;

import java.util.List;
import negocio.Pedido;

public interface PedidoDAO {
    public String grabar(Pedido ped);
    public List listarNum();
}
