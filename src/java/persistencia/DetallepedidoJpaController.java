package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.Articulo;
import negocio.Detallepedido;
import negocio.DetallepedidoPK;
import negocio.Pedido;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

public class DetallepedidoJpaController implements Serializable {

    public DetallepedidoJpaController() {
        emf=Persistence.createEntityManagerFactory("SupermercadoPiscoyaPU");
    }

    public DetallepedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepedido detallepedido) throws PreexistingEntityException, Exception {
        if (detallepedido.getDetallepedidoPK() == null) {
            detallepedido.setDetallepedidoPK(new DetallepedidoPK());
        }
        detallepedido.getDetallepedidoPK().setNum(detallepedido.getPedido().getNum());
        detallepedido.getDetallepedidoPK().setCod(detallepedido.getArticulo().getCod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo = detallepedido.getArticulo();
            if (articulo != null) {
                articulo = em.getReference(articulo.getClass(), articulo.getCod());
                detallepedido.setArticulo(articulo);
            }
            Pedido pedido = detallepedido.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getNum());
                detallepedido.setPedido(pedido);
            }
            em.persist(detallepedido);
            if (articulo != null) {
                articulo.getDetallepedidoList().add(detallepedido);
                articulo = em.merge(articulo);
            }
            if (pedido != null) {
                pedido.getDetallepedidoList().add(detallepedido);
                pedido = em.merge(pedido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepedido(detallepedido.getDetallepedidoPK()) != null) {
                throw new PreexistingEntityException("Detallepedido " + detallepedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepedido detallepedido) throws NonexistentEntityException, Exception {
        detallepedido.getDetallepedidoPK().setNum(detallepedido.getPedido().getNum());
        detallepedido.getDetallepedidoPK().setCod(detallepedido.getArticulo().getCod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido persistentDetallepedido = em.find(Detallepedido.class, detallepedido.getDetallepedidoPK());
            Articulo articuloOld = persistentDetallepedido.getArticulo();
            Articulo articuloNew = detallepedido.getArticulo();
            Pedido pedidoOld = persistentDetallepedido.getPedido();
            Pedido pedidoNew = detallepedido.getPedido();
            if (articuloNew != null) {
                articuloNew = em.getReference(articuloNew.getClass(), articuloNew.getCod());
                detallepedido.setArticulo(articuloNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getNum());
                detallepedido.setPedido(pedidoNew);
            }
            detallepedido = em.merge(detallepedido);
            if (articuloOld != null && !articuloOld.equals(articuloNew)) {
                articuloOld.getDetallepedidoList().remove(detallepedido);
                articuloOld = em.merge(articuloOld);
            }
            if (articuloNew != null && !articuloNew.equals(articuloOld)) {
                articuloNew.getDetallepedidoList().add(detallepedido);
                articuloNew = em.merge(articuloNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getDetallepedidoList().remove(detallepedido);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getDetallepedidoList().add(detallepedido);
                pedidoNew = em.merge(pedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallepedidoPK id = detallepedido.getDetallepedidoPK();
                if (findDetallepedido(id) == null) {
                    throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallepedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido detallepedido;
            try {
                detallepedido = em.getReference(Detallepedido.class, id);
                detallepedido.getDetallepedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.", enfe);
            }
            Articulo articulo = detallepedido.getArticulo();
            if (articulo != null) {
                articulo.getDetallepedidoList().remove(detallepedido);
                articulo = em.merge(articulo);
            }
            Pedido pedido = detallepedido.getPedido();
            if (pedido != null) {
                pedido.getDetallepedidoList().remove(detallepedido);
                pedido = em.merge(pedido);
            }
            em.remove(detallepedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepedido> findDetallepedidoEntities() {
        return findDetallepedidoEntities(true, -1, -1);
    }

    public List<Detallepedido> findDetallepedidoEntities(int maxResults, int firstResult) {
        return findDetallepedidoEntities(false, maxResults, firstResult);
    }

    private List<Detallepedido> findDetallepedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detallepedido findDetallepedido(DetallepedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepedido> rt = cq.from(Detallepedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
