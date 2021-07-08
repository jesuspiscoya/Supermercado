package servicio;

import java.util.List;
import negocio.Proveedor;
import persistencia.ProveedorDao;

public class ProveedorServicioImp implements ProveedorServicio {
    private ProveedorDao proDao;
    private Proveedor pro;

    public void setProDao(ProveedorDao proDao) {
        this.proDao = proDao;
    }

    public void setPro(Proveedor pro) {
        this.pro = pro;
    }
    
    @Override
    public Object[] buscar(String cod) {
        return proDao.buscar(cod);
    }
    
    @Override
    public String grabar(String cod, String nom, String dir) {
        pro.setCod(cod);
        pro.setNom(nom);
        pro.setDir(dir);
        return proDao.grabar(pro);
    }

    @Override
    public String actualizar(String cod, String nom, String dir) {
        pro.setCod(cod);
        pro.setNom(nom);
        pro.setDir(dir);
        return proDao.actualizar(pro);
    }
    
    @Override
    public List listar() {
        return proDao.listar();
    }
}