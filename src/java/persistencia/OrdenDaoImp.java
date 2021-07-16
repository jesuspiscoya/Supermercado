package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Detalleorden;
import negocio.Orden;

public class OrdenDaoImp implements OrdenDao {
    private OrdenJpaController ordjc;
    private DetalleordenJpaController detordjc;

    public void setOrdjc(OrdenJpaController ordjc) {
        this.ordjc = ordjc;
    }

    public void setDetordjc(DetalleordenJpaController detordjc) {
        this.detordjc = detordjc;
    }
    
    @Override
    public String grabar(Orden ord) {
        String msg;
        
        try {
            ordjc.create(ord);
            msg="Órden grabada con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String grabarDetalle(Detalleorden detOrd) {
        String msg;
        
        try {
            detordjc.create(detOrd);
            msg="Órden grabada con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listarNum() {
        List lis=ordjc.findOrdenEntities();
        List lista=new ArrayList();
        
        for (int i = 0; i < lis.size(); i++) {
            Orden ped=(Orden)lis.get(i);
            Object[] fila=new Object[1];
            fila[0]=ped.getNum();
            lista.add(fila);
        }
        return lista;
    }
}
