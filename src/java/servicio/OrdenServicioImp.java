package servicio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.Articulo;
import negocio.Empleado;
import negocio.Linea;
import negocio.Orden;
import negocio.Proveedor;
import persistencia.ArticuloDAOImp;
import persistencia.DetalleDAOImp;
import persistencia.OrdenDAOImp;
import persistencia.ProveedorDAOImp;

public class OrdenServicioImp implements OrdenServicio {
    private Orden ord;
    
    @Override
    public Object[] nuevaOrden(String cod) {
        List num=getNum();
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i)!="C"+String.format("%05d" , i)) {
                ord=new Orden();
                ord.setNum("C"+String.format("%05d" , i));
                ord.setFech(getFecha());
                Empleado emp=new Empleado();
                emp.setCod(cod);
                ord.setEmp(emp);
            }
        }
        return verOrden();
    }

    @Override
    public List agregarArticulo(String cod, String nom, String prec, String cant) {
        Articulo art=new Articulo(cod, nom, Double.parseDouble(prec));
        ord.agregarLinea(art, Integer.parseInt(cant));
        return verCesta();
    }

    @Override
    public List quitarOrden(String cod) {
        ord.quitarLinea(cod);
        return verCesta();
    }
    
    private Object[] verOrden() {
        Object[] fila=new Object[4];
        fila[0]=ord.getNum();
        fila[1]=ord.getFech();
        fila[2]=ord.getTot();
        fila[3]=ord.getEmp().getCod();
        return fila;
    }
    
    private List verCesta() {
        List lista=new ArrayList();
        for (int i = 0; i < ord.getCes().size(); i++) {
            Linea linea=(Linea)ord.getCes().get(i);
            Object[] fila=new Object[6];
            fila[0]=linea.getArt().getCod();
            fila[1]=linea.getArt().getNom();
            fila[2]=linea.getArt().getPrec();
            fila[3]=linea.getCant();
            fila[4]=linea.getImporte();
            fila[5]=ord.getTot();
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
    public Object[] buscarProveedor(String cod) {
        return new ProveedorDAOImp().buscar(cod);
    }

    @Override
    public String grabarOrden(String cod) {
        Proveedor prov=new Proveedor();
        prov.setCod(cod);
        ord.setProv(prov);
        String msg=new OrdenDAOImp().grabar(ord);
        
        for (int i = 0; i < ord.getCes().size(); i++) {
            Linea linea=(Linea)ord.getCes().get(i);
            msg=new DetalleDAOImp().grabar(ord, linea);
        }
        
        if (msg==null)
            msg="Orden Grabada";
        
        return msg;
    }

    @Override
    public List getNum() {
        return new OrdenDAOImp().listarNum();
    }
}
