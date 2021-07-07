package servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import negocio.*;
import persistencia.ArticuloDao;
import persistencia.OrdenDao;
import persistencia.ProveedorDao;

public class OrdenServicioImp implements OrdenServicio {
    private OrdenDao ordDao;
    private ProveedorDao proDao;
    private ArticuloDao artDao;
    private Orden ord;
    private Empleado emp;
    private Proveedor pro;
    private Object[] lis;
    private List cesta=new ArrayList();

    public void setOrdDao(OrdenDao ordDao) {
        this.ordDao = ordDao;
    }

    public void setProDao(ProveedorDao proDao) {
        this.proDao = proDao;
    }

    public void setArtDao(ArticuloDao artDao) {
        this.artDao = artDao;
    }

    public void setOrd(Orden ord) {
        this.ord = ord;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public void setPro(Proveedor pro) {
        this.pro = pro;
    }
    
    @Override
    public Object[] nuevaOrden() {
        List num=ordDao.listarNum();
        Object[] fila=new Object[2];
        
        for (int i = 0; i < num.size(); i++)
            if (!num.get(i).equals("C"+String.format("%05d" , i)))
                fila[0]="C"+String.format("%05d" , i+1);
        
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyy");
        fila[1]=fecha.format(LocalDate.now());
        return fila;
    }

    @Override
    public Object[] buscarProveedor(String cod) {
        return proDao.buscar(cod);
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
    public String grabarOrden(String codEmp, String codPro, double total) {
        Object[] pedido=nuevaOrden();
        ord.setNum(pedido[0].toString());
        ord.setFec(pedido[1].toString());
        emp.setCod(codEmp);
        ord.setCodemp(emp);
        pro.setCod(codPro);
        ord.setCodprov(pro);
        ord.setTot(total);
        return ordDao.grabar(ord);
    }
}
