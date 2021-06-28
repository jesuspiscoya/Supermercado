package negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByCod", query = "SELECT e FROM Empleado e WHERE e.cod = :cod")
    , @NamedQuery(name = "Empleado.findByNom", query = "SELECT e FROM Empleado e WHERE e.nom = :nom")
    , @NamedQuery(name = "Empleado.findByTip", query = "SELECT e FROM Empleado e WHERE e.tip = :tip")
    , @NamedQuery(name = "Empleado.findByUsu", query = "SELECT e FROM Empleado e WHERE e.usu = :usu")
    , @NamedQuery(name = "Empleado.findByPass", query = "SELECT e FROM Empleado e WHERE e.pass = :pass")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD")
    private String cod;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @Column(name = "TIP")
    private String tip;
    @Basic(optional = false)
    @Column(name = "USU")
    private String usu;
    @Basic(optional = false)
    @Column(name = "PASS")
    private String pass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codemp")
    private List<Orden> ordenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codemp")
    private List<Pedido> pedidoList;

    public Empleado() {
    }

    public Empleado(String cod) {
        this.cod = cod;
    }

    public Empleado(String cod, String nom, String tip, String usu, String pass) {
        this.cod = cod;
        this.nom = nom;
        this.tip = tip;
        this.usu = usu;
        this.pass = pass;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @XmlTransient
    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Empleado[ cod=" + cod + " ]";
    }
    
}
