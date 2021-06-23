package negocio;

public class Articulo {
    private String cod, nom;
    private double prec;
    private int stock;

    public Articulo() { }

    public Articulo(String cod, String nom, double prec) {
        this.cod = cod;
        this.nom = nom;
        this.prec = prec;
    }

    public Articulo(String cod, String nom, double prec, int stock) {
        this.cod = cod;
        this.nom = nom;
        this.prec = prec;
        this.stock = stock;
    }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

    public String getCod() { return cod; }

    public void setCod(String cod) { this.cod = cod; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public double getPrec() { return prec; }

    public void setPrec(double prec) { this.prec = prec; }
}
