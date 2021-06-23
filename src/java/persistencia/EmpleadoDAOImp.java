package persistencia;

import java.util.List;
import negocio.Empleado;

public class EmpleadoDAOImp implements EmpleadoDAO {

    @Override
    public String grabar(Empleado emp) {
        String sql="insert into empleado values('"+emp.getCod()+"','"+emp.getNom()+"','"+emp.getTip()+"','"+emp.getUsu()+"','"+emp.getPass()+"')";
        return Operacion.ejecutar(sql);
    }

    @Override
    public String actualizar(Empleado emp) {
        String sql="update empleado set tip='"+emp.getTip()+"', pass='"+emp.getPass()+"' where cod='"+emp.getCod()+"'";
        return Operacion.ejecutar(sql);
    }

    @Override
    public String eliminar(Empleado emp) {
        String sql="delete from empleado where cod='"+emp.getCod()+"'";
        return Operacion.ejecutar(sql);
    }

    @Override
    public Object[] buscar(String cod) {
        return Operacion.buscar("select * from empleado where cod='"+cod+"'");
    }

    @Override
    public List listar() {
        String sql="select * from empleado";
        List lista=Operacion.listar(sql);
        if (lista!=null) {
            return lista;
        } return null;
    }

    @Override
    public Object[] validar(String usu, String pass) {
        return Operacion.buscar("select * from empleado where usu like '"+usu+"' and pass like '"+pass+"'");
    }
}