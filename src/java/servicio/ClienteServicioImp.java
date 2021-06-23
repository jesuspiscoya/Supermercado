package servicio;

import java.util.List;
import negocio.Cliente;
import persistencia.ClienteDAO;
import persistencia.ClienteDAOImp;

public class ClienteServicioImp implements ClienteServicio {
    
    @Override
    public String grabar(String cod, String nom, String dir) {
        Cliente cli=new Cliente(cod, nom, dir);
        ClienteDAO cliDao=new ClienteDAOImp();
        String msg=cliDao.grabar(cli);
        
        if (msg==null) {
            msg="Cliente grabado con éxito.";
        } return msg;
    }
    
    @Override
    public Object[] buscar(String cod) {
        return new ClienteDAOImp().buscar(cod);
    }
    
    @Override
    public List listar() {
        return new ClienteDAOImp().listar();
    }
}