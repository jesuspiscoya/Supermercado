package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.Articulo;

public class ArticuloDaoImp implements ArticuloDao {
    private ArticuloJpaController artjc;

    public void setArtjc(ArticuloJpaController artjc) {
        this.artjc = artjc;
    }

    @Override
    public String grabar(Articulo art) {
        String msg;
        
        try {
            artjc.create(art);
            msg="Artículo grabado con éxito";
        } catch (Exception e) {
            msg=e.getMessage();
        }
        return msg;
    }

    @Override
    public List listar() {
        List lis=artjc.findArticuloEntities();
        List lista=new ArrayList();
        
        for (int i = 0; i < lis.size(); i++) {
            Articulo art=(Articulo)lis.get(i);
            Object[] fila=new Object[3];
            fila[0]=art.getCod();
            fila[1]=art.getNom();
            fila[2]=art.getPre();
            fila[3]=art.getSto();
            lista.add(fila);
        }
        return lista;
    }
}
