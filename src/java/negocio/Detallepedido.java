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
@Table(name = "DETALLEPEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d")
    , @NamedQuery(name = "Detallepedido.findByNum", query = "SELECT d FROM Detallepedido d WHERE d.detallepedidoPK.num = :num")
    , @NamedQuery(name = "Detallepedido.findByCod", query = "SELECT d FROM Detallepedido d WHERE d.detallepedidoPK.cod = :cod")
    , @NamedQuery(name = "Detallepedido.findByCan", query = "SELECT d FROM Detallepedido d WHERE d.can = :can")})
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallepedidoPK detallepedidoPK;
    @Basic(optional = false)
    @Column(name = "CAN")
    private int can;
    @JoinColumn(name = "COD", referencedColumnName = "COD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Articulo articulo;
    @JoinColumn(name = "NUM", referencedColumnName = "NUM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public Detallepedido() {
    }

    public Detallepedido(DetallepedidoPK detallepedidoPK) {
        this.detallepedidoPK = detallepedidoPK;
    }

    public Detallepedido(DetallepedidoPK detallepedidoPK, int can) {
        this.detallepedidoPK = detallepedidoPK;
        this.can = can;
    }

    public Detallepedido(String num, String cod) {
        this.detallepedidoPK = new DetallepedidoPK(num, cod);
    }

    public DetallepedidoPK getDetallepedidoPK() {
        return detallepedidoPK;
    }

    public void setDetallepedidoPK(DetallepedidoPK detallepedidoPK) {
        this.detallepedidoPK = detallepedidoPK;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallepedidoPK != null ? detallepedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepedido)) {
            return false;
        }
        Detallepedido other = (Detallepedido) object;
        if ((this.detallepedidoPK == null && other.detallepedidoPK != null) || (this.detallepedidoPK != null && !this.detallepedidoPK.equals(other.detallepedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Detallepedido[ detallepedidoPK=" + detallepedidoPK + " ]";
    }
    
}
