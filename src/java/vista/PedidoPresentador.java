package vista;

import java.util.ArrayList;
import java.util.List;

public class PedidoPresentador {
    private String msg="";
    private Object[] fila={"","","","","","0.0"};
    private Object[] fila2={"","",""};
    private List lista=new ArrayList();

    public PedidoPresentador() {
        lista.add(fila);
    }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public Object[] getFila() { return fila; }

    public void setFila(Object[] fila) { this.fila = fila; }

    public List getLista() { return lista; }

    public void setLista(List lista) { this.lista = lista; }

    public Object[] getFila2() { return fila2; }

    public void setFila2(Object[] fila2) { this.fila2 = fila2; }
}
