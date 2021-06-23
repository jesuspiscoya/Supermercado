package persistencia;

import java.util.List;
import negocio.Cliente;

public class ClienteDAOImp implements ClienteDAO {
    
    @Override
    public String grabar(Cliente cli) {
        String sql="insert into cliente values('"+cli.getCod()+"','"+cli.getNom()+"','"+cli.getDir()+"')";
        return Operacion.ejecutar(sql);
    }

    @Override
    public String actualizar(Cliente cli) {
        String sql="update cliente set dir='"+cli.getDir()+"' where cod='"+cli.getCod()+"'";
        return Operacion.ejecutar(sql);
    }

    @Override
    public String eliminar(Cliente cli) {
        String sql="delete from cliente where cod='"+cli.getCod()+"'";
        return Operacion.ejecutar(sql);
    }

    @Override
    public Object[] buscar(String cod) {
        return Operacion.buscar("select * from cliente where cod='"+cod+"'");
    }

    @Override
    public List listar() {
        String sql="select * from cliente";
        List lista=Operacion.listar(sql);
        if (lista!=null) {
            return lista;
        } return null;
    }
}
