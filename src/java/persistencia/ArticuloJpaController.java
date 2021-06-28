package persistencia;

import negocio.Articulo;
import negocio.Detalleorden;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController() {
        emf=Persistence.createEntityManagerFactory("SupermercadoPiscoyaPU");
    }

    public ArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) throws PreexistingEntityException, Exception {
        if (articulo.getDetalleordenList() == null) {
            articulo.setDetalleordenList(new ArrayList<Detalleorden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleorden> attachedDetalleordenList = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenListDetalleordenToAttach : articulo.getDetalleordenList()) {
                detalleordenListDetalleordenToAttach = em.getReference(detalleordenListDetalleordenToAttach.getClass(), detalleordenListDetalleordenToAttach.getDetalleordenPK());
                attachedDetalleordenList.add(detalleordenListDetalleordenToAttach);
            }
            articulo.setDetalleordenList(attachedDetalleordenList);
            em.persist(articulo);
            for (Detalleorden detalleordenListDetalleorden : articulo.getDetalleordenList()) {
                Articulo oldArticuloOfDetalleordenListDetalleorden = detalleordenListDetalleorden.getArticulo();
                detalleordenListDetalleorden.setArticulo(articulo);
                detalleordenListDetalleorden = em.merge(detalleordenListDetalleorden);
                if (oldArticuloOfDetalleordenListDetalleorden != null) {
                    oldArticuloOfDetalleordenListDetalleorden.getDetalleordenList().remove(detalleordenListDetalleorden);
                    oldArticuloOfDetalleordenListDetalleorden = em.merge(oldArticuloOfDetalleordenListDetalleorden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArticulo(articulo.getCod()) != null) {
                throw new PreexistingEntityException("Articulo " + articulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getCod());
            List<Detalleorden> detalleordenListOld = persistentArticulo.getDetalleordenList();
            List<Detalleorden> detalleordenListNew = articulo.getDetalleordenList();
            List<String> illegalOrphanMessages = null;
            for (Detalleorden detalleordenListOldDetalleorden : detalleordenListOld) {
                if (!detalleordenListNew.contains(detalleordenListOldDetalleorden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleorden " + detalleordenListOldDetalleorden + " since its articulo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleorden> attachedDetalleordenListNew = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenListNewDetalleordenToAttach : detalleordenListNew) {
                detalleordenListNewDetalleordenToAttach = em.getReference(detalleordenListNewDetalleordenToAttach.getClass(), detalleordenListNewDetalleordenToAttach.getDetalleordenPK());
                attachedDetalleordenListNew.add(detalleordenListNewDetalleordenToAttach);
            }
            detalleordenListNew = attachedDetalleordenListNew;
            articulo.setDetalleordenList(detalleordenListNew);
            articulo = em.merge(articulo);
            for (Detalleorden detalleordenListNewDetalleorden : detalleordenListNew) {
                if (!detalleordenListOld.contains(detalleordenListNewDetalleorden)) {
                    Articulo oldArticuloOfDetalleordenListNewDetalleorden = detalleordenListNewDetalleorden.getArticulo();
                    detalleordenListNewDetalleorden.setArticulo(articulo);
                    detalleordenListNewDetalleorden = em.merge(detalleordenListNewDetalleorden);
                    if (oldArticuloOfDetalleordenListNewDetalleorden != null && !oldArticuloOfDetalleordenListNewDetalleorden.equals(articulo)) {
                        oldArticuloOfDetalleordenListNewDetalleorden.getDetalleordenList().remove(detalleordenListNewDetalleorden);
                        oldArticuloOfDetalleordenListNewDetalleorden = em.merge(oldArticuloOfDetalleordenListNewDetalleorden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = articulo.getCod();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleorden> detalleordenListOrphanCheck = articulo.getDetalleordenList();
            for (Detalleorden detalleordenListOrphanCheckDetalleorden : detalleordenListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articulo (" + articulo + ") cannot be destroyed since the Detalleorden " + detalleordenListOrphanCheckDetalleorden + " in its detalleordenList field has a non-nullable articulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
