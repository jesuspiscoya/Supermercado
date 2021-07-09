package servicio;

import java.util.List;
import negocio.Cliente;
import persistencia.ClienteDao;

public class ClienteServicioImp implements ClienteServicio {
    private ClienteDao cliDao;
    private Cliente cli;

    public void setCliDao(ClienteDao cliDao) {
        this.cliDao = cliDao;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }
    
    @Override
    public Object[] buscar(String cod) {
        return cliDao.buscar(cod);
    }
    
    @Override
    public String grabar(String cod, String nom, String dir) {
        cli.setCod(cod);
        cli.setNom(nom);
        cli.setDir(dir);
        return cliDao.grabar(cli);
    }

    @Override
    public String actualizar(String cod, String nom, String dir) {
        cli.setCod(cod);
        cli.setNom(nom);
        cli.setDir(dir);
        return cliDao.actualizar(cli);
    }
    
    @Override
    public List listar() {
        return cliDao.listar();
    }

    @Override
    public String eliminar(String cod) {
        return cliDao.eliminar(cod);
    }
}