package servicio;

import negocio.Articulo;
import persistencia.ArticuloDAOImp;

public class ArticuloServicioImp implements ArticuloServicio {

    @Override
    public String grabar(String cod, String nom, Double prec, Integer sto) {
        Articulo art=new Articulo(cod, nom, prec, sto);
        String msg=new ArticuloDAOImp().grabar(art);
        
        if (msg==null) {
            msg="Artículo grabado con éxito.";
        } return msg;
    }
    
}
