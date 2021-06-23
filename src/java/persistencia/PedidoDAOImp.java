package persistencia;

import java.util.List;
import negocio.Pedido;

public class PedidoDAOImp implements PedidoDAO {

    @Override
    public String grabar(Pedido ped) {
        String sql="insert into pedido values('"+ped.getNum()+"','"+ped.getFech()+"',"+ped.getTot()+",'"+ped.getCli().getCod()+"','"+ped.getEmp().getCod()+"')";
        return Operacion.ejecutar(sql);
    }

    @Override
    public List listarNum() {
        String sql="select num from pedido";
        List lista=Operacion.listar(sql);
        
        if (lista!=null) {
            return lista;
        } return null;
    }
}
