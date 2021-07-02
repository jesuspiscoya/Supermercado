package servicio;

import negocio.Articulo;
import persistencia.ArticuloDao;

public class ArticuloServicioImp implements ArticuloServicio {
    private ArticuloDao artDao;
    private Articulo art;

    public void setArtDao(ArticuloDao artDao) {
        this.artDao = artDao;
    }

    public void setArt(Articulo art) {
        this.art = art;
    }

    @Override
    public String grabar(String cod, String nom, double prec, int sto) {
        art.setCod(cod);
        art.setNom(nom);
        art.setPre(prec);
        art.setSto(sto);
        return artDao.grabar(art);
    }
    
}
