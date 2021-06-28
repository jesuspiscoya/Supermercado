/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jesus
 */
@Entity
@Table(name = "ORDEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o")
    , @NamedQuery(name = "Orden.findByNum", query = "SELECT o FROM Orden o WHERE o.num = :num")
    , @NamedQuery(name = "Orden.findByFec", query = "SELECT o FROM Orden o WHERE o.fec = :fec")
    , @NamedQuery(name = "Orden.findByTot", query = "SELECT o FROM Orden o WHERE o.tot = :tot")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUM")
    private String num;
    @Basic(optional = false)
    @Column(name = "FEC")
    private String fec;
    @Basic(optional = false)
    @Column(name = "TOT")
    private double tot;
    @JoinColumn(name = "CODEMP", referencedColumnName = "COD")
    @ManyToOne(optional = false)
    private Empleado codemp;
    @JoinColumn(name = "CODPROV", referencedColumnName = "COD")
    @ManyToOne(optional = false)
    private Proveedor codprov;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private List<Detalleorden> detalleordenList;

    public Orden() {
    }

    public Orden(String num) {
        this.num = num;
    }

    public Orden(String num, String fec, double tot) {
        this.num = num;
        this.fec = fec;
        this.tot = tot;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFec() {
        return fec;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public Empleado getCodemp() {
        return codemp;
    }

    public void setCodemp(Empleado codemp) {
        this.codemp = codemp;
    }

    public Proveedor getCodprov() {
        return codprov;
    }

    public void setCodprov(Proveedor codprov) {
        this.codprov = codprov;
    }

    @XmlTransient
    public List<Detalleorden> getDetalleordenList() {
        return detalleordenList;
    }

    public void setDetalleordenList(List<Detalleorden> detalleordenList) {
        this.detalleordenList = detalleordenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Orden[ num=" + num + " ]";
    }
    
}
