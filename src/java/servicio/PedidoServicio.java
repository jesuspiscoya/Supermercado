package servicio;

import java.util.List;

public interface PedidoServicio {
    public Object[] nuevoPedido(String cod);
    public List agregarArticulo(String cod, String nom, String prec, String cant);
    public List quitarPedido(String cod);
    public List listarArticulos();
    public Object[] buscarCliente(String cod);
    public String grabarPedido(String cod);
    public List getNum();
}
