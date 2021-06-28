/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "DETALLEORDEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleorden.findAll", query = "SELECT d FROM Detalleorden d")
    , @NamedQuery(name = "Detalleorden.findByNum", query = "SELECT d FROM Detalleorden d WHERE d.detalleordenPK.num = :num")
    , @NamedQuery(name = "Detalleorden.findByCod", query = "SELECT d FROM Detalleorden d WHERE d.detalleordenPK.cod = :cod")
    , @NamedQuery(name = "Detalleorden.findByCan", query = "SELECT d FROM Detalleorden d WHERE d.can = :can")})
public class Detalleorden implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleordenPK detalleordenPK;
    @Basic(optional = false)
    @Column(name = "CAN")
    private int can;
    @JoinColumn(name = "COD", referencedColumnName = "COD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "NUM", referencedColumnName = "NUM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orden orden;

    public Detalleorden() {
    }

    public Detalleorden(DetalleordenPK detalleordenPK) {
        this.detalleordenPK = detalleordenPK;
    }

    public Detalleorden(DetalleordenPK detalleordenPK, int can) {
        this.detalleordenPK = detalleordenPK;
        this.can = can;
    }

    public Detalleorden(String num, String cod) {
        this.detalleordenPK = new DetalleordenPK(num, cod);
    }

    public DetalleordenPK getDetalleordenPK() {
        return detalleordenPK;
    }

    public void setDetalleordenPK(DetalleordenPK detalleordenPK) {
        this.detalleordenPK = detalleordenPK;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleordenPK != null ? detalleordenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleorden)) {
            return false;
        }
        Detalleorden other = (Detalleorden) object;
        if ((this.detalleordenPK == null && other.detalleordenPK != null) || (this.detalleordenPK != null && !this.detalleordenPK.equals(other.detalleordenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Detalleorden[ detalleordenPK=" + detalleordenPK + " ]";
    }
    
}
