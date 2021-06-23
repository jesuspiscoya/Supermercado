package persistencia;

import java.util.List;
import negocio.Articulo;

public class ArticuloDAOImp implements ArticuloDAO {

    @Override
    public List listar() {
        return Operacion.listar("select * from articulo");
    }

    @Override
    public String grabar(Articulo art) {
        String sql="insert into articulo values('"+art.getCod()+"','"+art.getNom()+"',"+art.getPrec()+","+art.getStock()+")";
        return Operacion.ejecutar(sql);
    }
}
