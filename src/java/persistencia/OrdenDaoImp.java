package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Orden;

public class OrdenDaoImp implements OrdenDao {
    
    @Override
    public String grabar(Orden ord) {
        String sql="insert into orden values('')";
        return sql;
    }

    @Override
    public List listarNum() {
        List lista=new ArrayList();
        return lista;
    }
}
