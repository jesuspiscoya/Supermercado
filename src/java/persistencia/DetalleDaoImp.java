package persistencia;

import negocio.Orden;

public class DetalleDaoImp implements DetalleDao {

    @Override
    public String grabar(Orden ord) {
        String sql="insert into detalleOrden values('"+ord.getNum()+"'";
        return sql;
    }
}
