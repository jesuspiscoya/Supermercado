package servicio;

import java.util.List;

public interface OrdenServicio {
    public Object[] nuevaOrden(String cod);
    public List agregarArticulo(String cod, String nom, String prec, String cant);
    public List quitarOrden(String cod);
    public List listarArticulos();
    public Object[] buscarProveedor(String cod);
    public String grabarOrden(String cod);
    public List getNum();
}
