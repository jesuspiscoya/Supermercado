package persistencia;

import java.util.List;
import negocio.Orden;

public class OrdenDAOImp implements OrdenDAO {
    
    @Override
    public String grabar(Orden ord) {
        String sql="insert into orden values('"+ord.getNum()+"','"+ord.getFech()+"',"+ord.getTot()+",'"+ord.getProv().getCod()+"','"+ord.getEmp().getCod()+"')";
        return Operacion.ejecutar(sql);
    }

    @Override
    public List listarNum() {
        String sql="select num from orden";
        List lista=Operacion.listar(sql);
        
        if (lista!=null) {
            return lista;
        } return null;
    }
}
