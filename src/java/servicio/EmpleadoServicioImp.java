package servicio;

import java.util.List;
import negocio.Empleado;
import persistencia.EmpleadoDao;

public class EmpleadoServicioImp implements EmpleadoServicio {
    private EmpleadoDao empDao;
    private Empleado emp;

    public void setEmpDao(EmpleadoDao empDao) {
        this.empDao = empDao;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    @Override
    public Object[] validar(String usu, String pass) {
        return empDao.validar(usu, pass);
    }

    @Override
    public Object[] buscar(String cod) {
        return empDao.buscar(cod);
    }

    @Override
    public String grabar(String cod, String nom, String tip, String usu, String pass) {
        emp.setCod(cod);
        emp.setNom(nom);
        emp.setTip(tip);
        emp.setUsu(usu);
        emp.setPass(pass);
        return empDao.grabar(emp);
    }

    @Override
    public String actualizar(String cod, String nom, String tip, String usu, String pass) {
        emp.setCod(cod);
        emp.setNom(nom);
        emp.setTip(tip);
        emp.setUsu(usu);
        emp.setPass(pass);
        return empDao.actualizar(emp);
    }

    @Override
    public List listar() {
        return empDao.listar();
    }
}
