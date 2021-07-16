package servicio;

import java.util.List;

public interface PedidoServicio {
    public Object[] nuevoPedido();
    public Object[] buscarCliente(String cod);
    public List listarArticulos();
    public List agregarArticulo(String cod, String nom, double prec);
    public List actualizarArticulo(String cod, int cantidad);
    public List quitarArticulo(String cod);
    public String grabarPedido(String codEmp, String codCli, double total);
    public String grabarDetalle(String num, String cod, int can);
}
