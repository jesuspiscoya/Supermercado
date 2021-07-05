package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Pedido;

public class PedidoDaoImp implements PedidoDao {
    private PedidoJpaController pedjc;

    public void setPedjc(PedidoJpaController pedjc) {
        this.pedjc = pedjc;
    }

    @Override
    public String grabar(Pedido ped) {
        String msg;
        
        try {
            pedjc.create(ped);
            msg="Pedido grabado con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listarNum() {
        List lis=pedjc.findPedidoEntities();
        List lista=new ArrayList();
        
        for (int i = 0; i < lis.size(); i++) {
            Pedido ped=(Pedido)lis.get(i);
            Object[] fila=new Object[1];
            fila[0]=ped.getNum();
            lista.add(fila);
        }
        return lista;
    }
}
