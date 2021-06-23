package persistencia;

import java.util.List;
import negocio.Proveedor;

public class ProveedorDAOImp implements ProveedorDAO {
    
    @Override
    public String grabar(Proveedor prov) {
        String sql="insert into proveedor values('"+prov.getCod()+"','"+prov.getNom()+"','"+prov.getDir()+"')";
        return Operacion.ejecutar(sql);
    }

    @Override
    public String actualizar(Proveedor prov) {
        String sql="update proveedor set dir='"+prov.getDir()+"' where cod='"+prov.getCod()+"'";
        return Operacion.ejecutar(sql);
    }

    @Override
    public String eliminar(Proveedor prov) {
        String sql="delete from proveedor where cod='"+prov.getCod()+"'";
        return Operacion.ejecutar(sql);
    }

    @Override
    public Object[] buscar(String cod) {
        return Operacion.buscar("select * from proveedor where cod='"+cod+"'");
    }

    @Override
    public List listar() {
        String sql="select * from proveedor";
        List lista=Operacion.listar(sql);
        if (lista!=null) {
            return lista;
        } return null;
    }
}
