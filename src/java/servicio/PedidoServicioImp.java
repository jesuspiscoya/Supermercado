package servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import negocio.*;
import persistencia.ArticuloDao;
import persistencia.ClienteDao;
import persistencia.PedidoDao;

public class PedidoServicioImp implements PedidoServicio {
    private PedidoDao pedDao;
    private ClienteDao cliDao;
    private ArticuloDao artDao;
    private Pedido ped;
    private Empleado emp;
    private Cliente cli;
    private Articulo art;
    private Detallepedido detPed;
    private Object[] lis;
    private List cesta=new ArrayList();

    public void setPedDao(PedidoDao pedDao) {
        this.pedDao = pedDao;
    }

    public void setCliDao(ClienteDao cliDao) {
        this.cliDao = cliDao;
    }

    public void setArtDao(ArticuloDao artDao) {
        this.artDao = artDao;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public void setArt(Articulo art) {
        this.art = art;
    }

    public void setDetPed(Detallepedido detPed) {
        this.detPed = detPed;
    }
    
    @Override
    public Object[] nuevoPedido() {
        List num=pedDao.listarNum();
        Object[] fila=new Object[2];
        
        for (int i = 0; i < num.size(); i++)
            if (!num.get(i).equals("P"+String.format("%05d" , i)))
                fila[0]="P"+String.format("%05d" , i+1);
        
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyy");
        fila[1]=fecha.format(LocalDate.now());
        return fila;
    }

    @Override
    public Object[] buscarCliente(String cod) {
        return cliDao.buscar(cod);
    }

    @Override
    public List listarArticulos() {
        return artDao.listar();
    }

    @Override
    public List agregarArticulo(String cod, String nom, double prec) {
        boolean bool=true;
        lis=new Object[5];
        int cantidad=1, index=0;

        if (bool) {
            lis[0]=cod;
            lis[1]=nom;
            lis[2]=prec;
            lis[3]=1;
            lis[4]=prec;
            for (int i = 0; i < cesta.size(); i++) {
                Object[] fila=(Object[]) cesta.get(i);
                if (fila[0].equals(cod)) {
                    bool=false;
                    index=i;
                    cantidad=(int)fila[3]+1;
                }
            }
            cesta.add(lis);
        } if (!bool) {
            lis[0]=cod;
            lis[1]=nom;
            lis[2]=prec;
            lis[3]=cantidad;
            lis[4]=prec*cantidad;
            cesta.set(index, lis);
            cesta.remove(cesta.size()-1);
            bool=true;
        } return cesta;
    }

    @Override
    public List actualizarArticulo(String cod, int cantidad) {
        lis=new Object[5];
        for (int i = 0; i < cesta.size(); i++) {
            Object[] fi=(Object[]) cesta.get(i);
            if (fi[0].equals(cod)) {
                lis[0]=cod;
                lis[1]=fi[1];
                lis[2]=fi[2];
                lis[3]=cantidad;
                lis[4]=(double)fi[2]*cantidad;
                cesta.set(i, lis);
            }
        } return cesta;
    }
    
    @Override
    public List quitarArticulo(String cod) {
        for (int i = 0; i < cesta.size(); i++) {
            Object[] fila=(Object[]) cesta.get(i);
            if (fila[0].equals(cod))
                cesta.remove(fila);
        } return cesta;
    }

    @Override
    public String grabarPedido(String codEmp, String codCli, double total) {
        Object[] pedido=nuevoPedido();
        ped.setNum(pedido[0].toString());
        ped.setFec(pedido[1].toString());
        emp.setCod(codEmp);
        ped.setCodemp(emp);
        cli.setCod(codCli);
        ped.setCodcli(cli);
        ped.setTot(total);
        return pedDao.grabar(ped);
    }

    @Override
    public String grabarDetalle(String num, String cod, int can) {
        ped.setNum(num);
        art.setCod(cod);
        detPed.setPedido(ped);
        detPed.setArticulo(art);
        detPed.setCan(can);
        return pedDao.grabarDetalle(detPed);
    }
}
