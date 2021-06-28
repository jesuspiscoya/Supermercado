package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Cliente;

public class ClienteDaoImp implements ClienteDao {
    private ClienteJpaController clijc;

    public void setClijc(ClienteJpaController clijc) {
        this.clijc = clijc;
    }

    @Override
    public Object[] buscar(String cod) {
        Cliente cli=clijc.findCliente(cod);
        if (cli!=null) {
            Object[] fila=new Object[3];
            fila[0]=cli.getCod();
            fila[1]=cli.getNom();
            fila[2]=cli.getDir();
            return fila;
        }
        return null;
    }
    
    @Override
    public String grabar(Cliente cli) {
        String msg;
        
        try {
            clijc.create(cli);
            msg="Cliente grabado con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String actualizar(Cliente cli) {
        String msg;
        
        try {
            clijc.edit(cli);
            msg="Cliente actualizado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String cod) {
        String msg;
        
        try {
            clijc.destroy(cod);
            msg="Cliente eliminado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listar() {
        List lis=clijc.findClienteEntities();
        List lista=new ArrayList();
        
        for (int i = 0; i < lis.size(); i++) {
            Cliente cli=(Cliente)lis.get(i);
            Object[] fila=new Object[3];
            fila[0]=cli.getCod();
            fila[1]=cli.getNom();
            fila[2]=cli.getDir();
            lista.add(fila);
        }
        return lista;
    }
}
