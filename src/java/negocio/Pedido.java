/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jesus
 */
@Entity
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByNum", query = "SELECT p FROM Pedido p WHERE p.num = :num")
    , @NamedQuery(name = "Pedido.findByFec", query = "SELECT p FROM Pedido p WHERE p.fec = :fec")
    , @NamedQuery(name = "Pedido.findByTot", query = "SELECT p FROM Pedido p WHERE p.tot = :tot")})
public class Pedido implements Serializable {

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
    @JoinColumn(name = "CODCLI", referencedColumnName = "COD")
    @ManyToOne(optional = false)
    private Cliente codcli;
    @JoinColumn(name = "CODEMP", referencedColumnName = "COD")
    @ManyToOne(optional = false)
    private Empleado codemp;

    public Pedido() {
    }

    public Pedido(String num) {
        this.num = num;
    }

    public Pedido(String num, String fec, double tot) {
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

    public Cliente getCodcli() {
        return codcli;
    }

    public void setCodcli(Cliente codcli) {
        this.codcli = codcli;
    }

    public Empleado getCodemp() {
        return codemp;
    }

    public void setCodemp(Empleado codemp) {
        this.codemp = codemp;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Pedido[ num=" + num + " ]";
    }
    
}
