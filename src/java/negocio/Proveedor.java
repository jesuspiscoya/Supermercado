package negocio;

public class Proveedor {
    private String cod, nom, dir;

    public Proveedor() {}
    
    public Proveedor(String cod, String nom, String dir) {
        this.cod = cod;
        this.nom = nom;
        this.dir = dir;
    }

    public String getCod() { return cod; }

    public void setCod(String cod) { this.cod = cod; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getDir() { return dir; }

    public void setDir(String dir) { this.dir = dir; }
}
