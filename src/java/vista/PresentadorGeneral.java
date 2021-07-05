package vista;

import java.util.ArrayList;
import java.util.List;

public class PresentadorGeneral {
    private String msg="";
    private Object[] validar={"",""};
    private Object[] empleado={"","","","",""};
    private Object[] cliente={"","",""};
    private Object[] proveedor={"","",""};
    private Object[] pedido={"",""};
    private Object[] orden={"",""};
    private List lista=new ArrayList();
    private List listaPed=new ArrayList();

    public PresentadorGeneral() {
        lista.add(empleado);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getValidar() {
        return validar;
    }

    public void setValidar(Object[] validar) {
        this.validar = validar;
    }

    public Object[] getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Object[] empleado) {
        this.empleado = empleado;
    }

    public Object[] getCliente() {
        return cliente;
    }

    public void setCliente(Object[] cliente) {
        this.cliente = cliente;
    }

    public Object[] getProveedor() {
        return proveedor;
    }

    public void setProveedor(Object[] proveedor) {
        this.proveedor = proveedor;
    }

    public Object[] getPedido() {
        return pedido;
    }

    public void setPedido(Object[] pedido) {
        this.pedido = pedido;
    }

    public Object[] getOrden() {
        return orden;
    }

    public void setOrden(Object[] orden) {
        this.orden = orden;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public List getListaPed() {
        return listaPed;
    }

    public void setListaPed(List listaPed) {
        this.listaPed = listaPed;
    }
}
