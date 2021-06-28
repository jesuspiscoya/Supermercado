package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Proveedor;

public class ProveedorDaoImp implements ProveedorDao {
    private ProveedorJpaController projc;

    public void setProjc(ProveedorJpaController projc) {
        this.projc = projc;
    }

    @Override
    public Object[] buscar(String cod) {
        Proveedor pro=projc.findProveedor(cod);
        if (pro!=null) {
            Object[] fila=new Object[5];
            fila[0]=pro.getCod();
            fila[1]=pro.getNom();
            fila[2]=pro.getDir();
            return fila;
        }
        return null;
    }
    
    @Override
    public String grabar(Proveedor pro) {
        String msg;
        
        try {
            projc.create(pro);
            msg="Proveedor grabado con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String actualizar(Proveedor pro) {
        String msg;
        
        try {
            projc.edit(pro);
            msg="Proveedor actualizado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String cod) {
        String msg;
        
        try {
            projc.destroy(cod);
            msg="Proveedor eliminado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listar() {
        List lis=projc.findProveedorEntities();
        List lista=new ArrayList();
        
        for (int i = 0; i < lis.size(); i++) {
            Proveedor pro=(Proveedor)lis.get(i);
            Object[] fila=new Object[5];
            fila[0]=pro.getCod();
            fila[1]=pro.getNom();
            fila[2]=pro.getDir();
            lista.add(fila);
        }
        return lista;
    }
}
