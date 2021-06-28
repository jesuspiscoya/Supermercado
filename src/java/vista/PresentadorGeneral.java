package vista;

import java.util.ArrayList;
import java.util.List;

public class PresentadorGeneral {
    private String msg="";
    private Object[] validar={"",""};
    private Object[] empleado={"","","","",""};
    private Object[] cliente={"","",""};
    private Object[] proveedor={"","",""};
    private Object[] fila={"","","","","","0.0"};
    private Object[] fila2={"","",""};
    private List lista=new ArrayList();

    public PresentadorGeneral() {
        lista.add(fila);
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

    public Object[] getFila() {
        return fila;
    }

    public void setFila(Object[] fila) {
        this.fila = fila;
    }

    public Object[] getFila2() {
        return fila2;
    }

    public void setFila2(Object[] fila2) {
        this.fila2 = fila2;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }
}
