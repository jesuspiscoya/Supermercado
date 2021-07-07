package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Orden;

public class OrdenDaoImp implements OrdenDao {
    private OrdenJpaController ordjc;

    public void setOrdjc(OrdenJpaController ordjc) {
        this.ordjc = ordjc;
    }
    
    @Override
    public String grabar(Orden ord) {
        String msg;
        
        try {
            ordjc.create(ord);
            msg="Orden grabada con éxito";
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
