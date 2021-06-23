package servicio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.*;
import persistencia.ArticuloDAOImp;
import persistencia.ClienteDAOImp;
import persistencia.PedidoDAOImp;

public class PedidoServicioImp implements PedidoServicio {
    private Pedido ped;
    
    @Override
    public Object[] nuevoPedido(String cod) {
        List num=getNum();
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i)!="P"+String.format("%05d" , i)) {
                ped=new Pedido();
                ped.setNum("P"+String.format("%05d" , i));
                ped.setFech(getFecha());
                Empleado emp=new Empleado();
                emp.setCod(cod);
                ped.setEmp(emp);
            }
        }
        return verPedido();
    }

    @Override
    public List agregarArticulo(String cod, String nom, String prec, String cant) {
        Articulo art=new Articulo(cod, nom, Double.parseDouble(prec));
        ped.agregarLinea(art, Integer.parseInt(cant));
        return verCesta();
    }

    @Override
    public List quitarPedido(String cod) {
        ped.quitarLinea(cod);
        return verCesta();
    }
    
    private Object[] verPedido() {
        Object[] fila=new Object[4];
        fila[0]=ped.getNum();
        fila[1]=ped.getFech();
        fila[2]=ped.getTot();
        fila[3]=ped.getEmp().getCod();
        return fila;
    }
    
    private List verCesta() {
        List lista=new ArrayList();
        for (int i = 0; i < ped.getCes().size(); i++) {
            Linea linea=(Linea)ped.getCes().get(i);
            Object[] fila=new Object[6];
            fila[0]=linea.getArt().getCod();
            fila[1]=linea.getArt().getNom();
            fila[2]=linea.getArt().getPrec();
            fila[3]=linea.getCant();
            fila[4]=linea.getImporte();
            fila[5]=ped.getTot();
            lista.add(fila);
        }
        return lista;
    }
    
    private String getFecha() {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(d);
    }

    @Override
    public List listarArticulos() {
        return new ArticuloDAOImp().listar();
    }

    @Override
    public Object[] buscarCliente(String cod) {
        return new ClienteDAOImp().buscar(cod);
    }

    @Override
    public String grabarPedido(String cod) {
        Cliente cli=new Cliente();
        cli.setCod(cod);
        ped.setCli(cli);
        String msg=new PedidoDAOImp().grabar(ped);
        
        if (msg==null)
            msg="Pedido Grabado";
        
        return msg;
    }

    @Override
    public List getNum() {
        return new PedidoDAOImp().listarNum();
    }
}
