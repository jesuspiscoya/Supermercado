package persistencia;

import java.util.ArrayList;
import negocio.Empleado;
import java.util.List;

public class EmpleadoDaoImp implements EmpleadoDao {
    private EmpleadoJpaController empjc;

    public void setEmpjc(EmpleadoJpaController empjc) {
        this.empjc = empjc;
    }

    @Override
    public Object[] validar(String usu, String pass) {
        List lis=empjc.findEmpleadoEntities();
        Object[] fila=new Object[2];
        
        for (int i = 0; i < lis.size(); i++) {
            Empleado emp=(Empleado) lis.get(i);
            if (emp.getUsu().equals(usu) && emp.getPass().equals(pass)) {
                fila[0]=emp.getCod();
                fila[1]=emp.getNom();
                return fila;
            }
        }
        return null;
    }

    @Override
    public Object[] buscar(String cod) {
        Empleado emp=empjc.findEmpleado(cod);
        if (emp!=null) {
            Object[] fila=new Object[5];
            fila[0]=emp.getCod();
            fila[1]=emp.getNom();
            fila[2]=emp.getTip();
            fila[3]=emp.getUsu();
            fila[4]=emp.getPass();
            return fila;
        }
        return null;
    }

    @Override
    public String grabar(Empleado emp) {
        String msg;
        
        try {
            empjc.create(emp);
            msg="Empleado grabado con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String actualizar(Empleado emp) {
        String msg;
        
        try {
            empjc.edit(emp);
            msg="Empleado actualizado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public String eliminar(String cod) {
        String msg;
        
        try {
            empjc.destroy(cod);
            msg="Empleado eliminado";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listar() {
        List lis=empjc.findEmpleadoEntities();
        List lista=new ArrayList();
        
        for (int i = 0; i < lis.size(); i++) {
            Empleado emp=(Empleado)lis.get(i);
            Object[] fila=new Object[5];
            fila[0]=emp.getCod();
            fila[1]=emp.getNom();
            fila[2]=emp.getTip();
            fila[3]=emp.getUsu();
            fila[4]=emp.getPass();
            lista.add(fila);
        }
        return lista;
    }
    
}
