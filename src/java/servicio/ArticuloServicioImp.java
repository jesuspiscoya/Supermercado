package servicio;

import java.util.List;
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
    public Object[] buscar(String cod) {
        return artDao.buscar(cod);
    }

    @Override
    public String grabar(String cod, String nom, double prec, int sto) {
        art.setCod(cod);
        art.setNom(nom);
        art.setPre(prec);
        art.setSto(sto);
        return artDao.grabar(art);
    }

    @Override
    public String actualizar(String cod, String nom, double prec, int sto) {
         art.setCod(cod);
        art.setNom(nom);
        art.setPre(prec);
        art.setSto(sto);
        return artDao.actualizar(art);
    }
    
    @Override
    public List listar() {
        return artDao.listar();
    }

    @Override
    public String eliminar(String cod) {
        return artDao.eliminar(cod);
    }
}
