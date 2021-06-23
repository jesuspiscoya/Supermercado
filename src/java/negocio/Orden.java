package negocio;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private String num, fech;
    private Proveedor prov;
    private Empleado emp;
    private List ces=new ArrayList();
    
    public void agregarLinea(Articulo art, int cant) {
        Linea linea=new Linea(art, cant);
        ces.add(linea);
    }
    
    public void quitarLinea(String cod) {
        for (int i = 0; i < ces.size(); i++) {
            Linea linea=(Linea)ces.get(i);
            if (linea.getArt().getCod().equals(cod))
                ces.remove(linea);
        }
    }
    
    public double getTot() {
        double total=0;
        for (int i = 0; i < ces.size(); i++) {
            Linea linea=(Linea)ces.get(i);
            total+=linea.getImporte();
        }
        return total;
    }
    
    public String getNum() { return num; }

    public void setNum(String num) { this.num = num; }

    public String getFech() { return fech; }

    public void setFech(String fech) { this.fech = fech; }

    public Proveedor getProv() { return prov; }

    public void setProv(Proveedor prov) { this.prov = prov; }
    
    public Empleado getEmp() { return emp; }

    public void setEmp(Empleado emp) { this.emp = emp; }

    public List getCes() { return ces; }

    public void setCes(List ces) { this.ces = ces; }
}
