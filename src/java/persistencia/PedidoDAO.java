package persistencia;

import java.util.List;
import negocio.Detallepedido;
import negocio.Pedido;

public interface PedidoDao {
    public String grabar(Pedido ped);
    public String grabarDetalle(Detallepedido detPed);
    public List listarNum();
}
