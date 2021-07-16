package servicio;

import java.util.List;

public interface OrdenServicio {
    public Object[] nuevaOrden();
    public Object[] buscarProveedor(String cod);
    public List listarArticulos();
    public List agregarArticulo(String cod, String nom, double prec);
    public List actualizarArticulo(String cod, int cantidad);
    public List quitarArticulo(String cod);
    public String grabarOrden(String codEmp, String codPro, double total);
    public String grabarDetalle(String num, String cod, int can);
}
