package persistencia;

import negocio.Articulo;
import negocio.DetalleordenPK;
import negocio.Orden;
import negocio.Detalleorden;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

public class DetalleordenJpaController implements Serializable {

    public DetalleordenJpaController() {
        emf=Persistence.createEntityManagerFactory("SupermercadoPiscoyaPU");
    }

    public DetalleordenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleorden detalleorden) throws PreexistingEntityException, Exception {
        if (detalleorden.getDetalleordenPK() == null) {
            detalleorden.setDetalleordenPK(new DetalleordenPK());
        }
        detalleorden.getDetalleordenPK().setNum(detalleorden.getOrden().getNum());
        detalleorden.getDetalleordenPK().setCod(detalleorden.getArticulo().getCod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo = detalleorden.getArticulo();
            if (articulo != null) {
                articulo = em.getReference(articulo.getClass(), articulo.getCod());
                detalleorden.setArticulo(articulo);
            }
            Orden orden = detalleorden.getOrden();
            if (orden != null) {
                orden = em.getReference(orden.getClass(), orden.getNum());
                detalleorden.setOrden(orden);
            }
            em.persist(detalleorden);
            if (articulo != null) {
                articulo.getDetalleordenList().add(detalleorden);
                articulo = em.merge(articulo);
            }
            if (orden != null) {
                orden.getDetalleordenList().add(detalleorden);
                orden = em.merge(orden);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleorden(detalleorden.getDetalleordenPK()) != null) {
                throw new PreexistingEntityException("Detalleorden " + detalleorden + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleorden detalleorden) throws NonexistentEntityException, Exception {
        detalleorden.getDetalleordenPK().setNum(detalleorden.getOrden().getNum());
        detalleorden.getDetalleordenPK().setCod(detalleorden.getArticulo().getCod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleorden persistentDetalleorden = em.find(Detalleorden.class, detalleorden.getDetalleordenPK());
            Articulo articuloOld = persistentDetalleorden.getArticulo();
            Articulo articuloNew = detalleorden.getArticulo();
            Orden ordenOld = persistentDetalleorden.getOrden();
            Orden ordenNew = detalleorden.getOrden();
            if (articuloNew != null) {
                articuloNew = em.getReference(articuloNew.getClass(), articuloNew.getCod());
                detalleorden.setArticulo(articuloNew);
            }
            if (ordenNew != null) {
                ordenNew = em.getReference(ordenNew.getClass(), ordenNew.getNum());
                detalleorden.setOrden(ordenNew);
            }
            detalleorden = em.merge(detalleorden);
            if (articuloOld != null && !articuloOld.equals(articuloNew)) {
                articuloOld.getDetalleordenList().remove(detalleorden);
                articuloOld = em.merge(articuloOld);
            }
            if (articuloNew != null && !articuloNew.equals(articuloOld)) {
                articuloNew.getDetalleordenList().add(detalleorden);
                articuloNew = em.merge(articuloNew);
            }
            if (ordenOld != null && !ordenOld.equals(ordenNew)) {
                ordenOld.getDetalleordenList().remove(detalleorden);
                ordenOld = em.merge(ordenOld);
            }
            if (ordenNew != null && !ordenNew.equals(ordenOld)) {
                ordenNew.getDetalleordenList().add(detalleorden);
                ordenNew = em.merge(ordenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleordenPK id = detalleorden.getDetalleordenPK();
                if (findDetalleorden(id) == null) {
                    throw new NonexistentEntityException("The detalleorden with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleordenPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleorden detalleorden;
            try {
                detalleorden = em.getReference(Detalleorden.class, id);
                detalleorden.getDetalleordenPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleorden with id " + id + " no longer exists.", enfe);
            }
            Articulo articulo = detalleorden.getArticulo();
            if (articulo != null) {
                articulo.getDetalleordenList().remove(detalleorden);
                articulo = em.merge(articulo);
            }
            Orden orden = detalleorden.getOrden();
            if (orden != null) {
                orden.getDetalleordenList().remove(detalleorden);
                orden = em.merge(orden);
            }
            em.remove(detalleorden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleorden> findDetalleordenEntities() {
        return findDetalleordenEntities(true, -1, -1);
    }

    public List<Detalleorden> findDetalleordenEntities(int maxResults, int firstResult) {
        return findDetalleordenEntities(false, maxResults, firstResult);
    }

    private List<Detalleorden> findDetalleordenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleorden.class));
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

    public Detalleorden findDetalleorden(DetalleordenPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleorden.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleordenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleorden> rt = cq.from(Detalleorden.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
