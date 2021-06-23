package persistencia;

import negocio.Linea;
import negocio.Orden;

public class DetalleDAOImp implements DetalleDAO {

    @Override
    public String grabar(Orden ord, Linea lin) {
        String sql="insert into detalleOrden values('"+ord.getNum()+"','"+lin.getArt().getCod()+"',"+lin.getCant()+")";
        return Operacion.ejecutar(sql);
    }
}
