package servicio;

import java.util.List;
import negocio.Empleado;
import persistencia.EmpleadoDAO;
import persistencia.EmpleadoDAOImp;

public class EmpleadoServicioImp implements EmpleadoServicio {
    
    @Override
    public String grabar(String cod, String nom, String tip, String usu, String pass) {
        Empleado emp=new Empleado(cod, nom, tip, usu, pass);
        EmpleadoDAO empDao=new EmpleadoDAOImp();
        String msg=empDao.grabar(emp);
        
        if (msg==null) {
            msg="Empleado grabado con éxito.";
        } return msg;
    }
    
    @Override
    public Object[] validar(String usu, String pass) {
        return new EmpleadoDAOImp().validar(usu, pass);
    }
    
    @Override
    public Object[] buscar(String cod) {
        return new EmpleadoDAOImp().buscar(cod);
    }
    
    @Override
    public List listar() {
        return new EmpleadoDAOImp().listar();
    }
}
