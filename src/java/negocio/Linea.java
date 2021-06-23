package negocio;

public class Linea {
    private Articulo art;
    private int cant;
    
    public double getImporte() {
        return art.getPrec()*cant;
    }

    public Linea() { }

    public Linea(Articulo art, int cant) {
        this.art = art;
        this.cant = cant;
    }

    public Articulo getArt() { return art; }

    public void setArt(Articulo art) { this.art = art; }

    public int getCant() { return cant; }

    public void setCant(int cant) { this.cant = cant; }
}
