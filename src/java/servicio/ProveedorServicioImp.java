package servicio;

import java.util.List;
import negocio.Proveedor;
import persistencia.ProveedorDAO;
import persistencia.ProveedorDAOImp;

public class ProveedorServicioImp implements ProveedorServicio {
    
    @Override
    public String grabar(String cod, String nom, String dir) {
        Proveedor prov=new Proveedor(cod, nom, dir);
        ProveedorDAO provDao=new ProveedorDAOImp();
        String msg=provDao.grabar(prov);
        
        if (msg==null) {
            msg="Proveedor grabado con éxito.";
        } return msg;
    }
    
    @Override
    public Object[] buscar(String cod) {
        return new ProveedorDAOImp().buscar(cod);
    }
    
    @Override
    public List listar() {
        return new ProveedorDAOImp().listar();
    }
}