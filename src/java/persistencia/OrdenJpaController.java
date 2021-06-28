package persistencia;

import negocio.Orden;
import negocio.Proveedor;
import negocio.Empleado;
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

public class OrdenJpaController implements Serializable {

    public OrdenJpaController() {
        emf=Persistence.createEntityManagerFactory("SupermercadoPiscoyaPU");
    }

    public OrdenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orden orden) throws PreexistingEntityException, Exception {
        if (orden.getDetalleordenList() == null) {
            orden.setDetalleordenList(new ArrayList<Detalleorden>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado codemp = orden.getCodemp();
            if (codemp != null) {
                codemp = em.getReference(codemp.getClass(), codemp.getCod());
                orden.setCodemp(codemp);
            }
            Proveedor codprov = orden.getCodprov();
            if (codprov != null) {
                codprov = em.getReference(codprov.getClass(), codprov.getCod());
                orden.setCodprov(codprov);
            }
            List<Detalleorden> attachedDetalleordenList = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenListDetalleordenToAttach : orden.getDetalleordenList()) {
                detalleordenListDetalleordenToAttach = em.getReference(detalleordenListDetalleordenToAttach.getClass(), detalleordenListDetalleordenToAttach.getDetalleordenPK());
                attachedDetalleordenList.add(detalleordenListDetalleordenToAttach);
            }
            orden.setDetalleordenList(attachedDetalleordenList);
            em.persist(orden);
            if (codemp != null) {
                codemp.getOrdenList().add(orden);
                codemp = em.merge(codemp);
            }
            if (codprov != null) {
                codprov.getOrdenList().add(orden);
                codprov = em.merge(codprov);
            }
            for (Detalleorden detalleordenListDetalleorden : orden.getDetalleordenList()) {
                Orden oldOrdenOfDetalleordenListDetalleorden = detalleordenListDetalleorden.getOrden();
                detalleordenListDetalleorden.setOrden(orden);
                detalleordenListDetalleorden = em.merge(detalleordenListDetalleorden);
                if (oldOrdenOfDetalleordenListDetalleorden != null) {
                    oldOrdenOfDetalleordenListDetalleorden.getDetalleordenList().remove(detalleordenListDetalleorden);
                    oldOrdenOfDetalleordenListDetalleorden = em.merge(oldOrdenOfDetalleordenListDetalleorden);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrden(orden.getNum()) != null) {
                throw new PreexistingEntityException("Orden " + orden + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orden orden) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orden persistentOrden = em.find(Orden.class, orden.getNum());
            Empleado codempOld = persistentOrden.getCodemp();
            Empleado codempNew = orden.getCodemp();
            Proveedor codprovOld = persistentOrden.getCodprov();
            Proveedor codprovNew = orden.getCodprov();
            List<Detalleorden> detalleordenListOld = persistentOrden.getDetalleordenList();
            List<Detalleorden> detalleordenListNew = orden.getDetalleordenList();
            List<String> illegalOrphanMessages = null;
            for (Detalleorden detalleordenListOldDetalleorden : detalleordenListOld) {
                if (!detalleordenListNew.contains(detalleordenListOldDetalleorden)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleorden " + detalleordenListOldDetalleorden + " since its orden field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codempNew != null) {
                codempNew = em.getReference(codempNew.getClass(), codempNew.getCod());
                orden.setCodemp(codempNew);
            }
            if (codprovNew != null) {
                codprovNew = em.getReference(codprovNew.getClass(), codprovNew.getCod());
                orden.setCodprov(codprovNew);
            }
            List<Detalleorden> attachedDetalleordenListNew = new ArrayList<Detalleorden>();
            for (Detalleorden detalleordenListNewDetalleordenToAttach : detalleordenListNew) {
                detalleordenListNewDetalleordenToAttach = em.getReference(detalleordenListNewDetalleordenToAttach.getClass(), detalleordenListNewDetalleordenToAttach.getDetalleordenPK());
                attachedDetalleordenListNew.add(detalleordenListNewDetalleordenToAttach);
            }
            detalleordenListNew = attachedDetalleordenListNew;
            orden.setDetalleordenList(detalleordenListNew);
            orden = em.merge(orden);
            if (codempOld != null && !codempOld.equals(codempNew)) {
                codempOld.getOrdenList().remove(orden);
                codempOld = em.merge(codempOld);
            }
            if (codempNew != null && !codempNew.equals(codempOld)) {
                codempNew.getOrdenList().add(orden);
                codempNew = em.merge(codempNew);
            }
            if (codprovOld != null && !codprovOld.equals(codprovNew)) {
                codprovOld.getOrdenList().remove(orden);
                codprovOld = em.merge(codprovOld);
            }
            if (codprovNew != null && !codprovNew.equals(codprovOld)) {
                codprovNew.getOrdenList().add(orden);
                codprovNew = em.merge(codprovNew);
            }
            for (Detalleorden detalleordenListNewDetalleorden : detalleordenListNew) {
                if (!detalleordenListOld.contains(detalleordenListNewDetalleorden)) {
                    Orden oldOrdenOfDetalleordenListNewDetalleorden = detalleordenListNewDetalleorden.getOrden();
                    detalleordenListNewDetalleorden.setOrden(orden);
                    detalleordenListNewDetalleorden = em.merge(detalleordenListNewDetalleorden);
                    if (oldOrdenOfDetalleordenListNewDetalleorden != null && !oldOrdenOfDetalleordenListNewDetalleorden.equals(orden)) {
                        oldOrdenOfDetalleordenListNewDetalleorden.getDetalleordenList().remove(detalleordenListNewDetalleorden);
                        oldOrdenOfDetalleordenListNewDetalleorden = em.merge(oldOrdenOfDetalleordenListNewDetalleorden);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = orden.getNum();
                if (findOrden(id) == null) {
                    throw new NonexistentEntityException("The orden with id " + id + " no longer exists.");
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
            Orden orden;
            try {
                orden = em.getReference(Orden.class, id);
                orden.getNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orden with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleorden> detalleordenListOrphanCheck = orden.getDetalleordenList();
            for (Detalleorden detalleordenListOrphanCheckDetalleorden : detalleordenListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Orden (" + orden + ") cannot be destroyed since the Detalleorden " + detalleordenListOrphanCheckDetalleorden + " in its detalleordenList field has a non-nullable orden field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado codemp = orden.getCodemp();
            if (codemp != null) {
                codemp.getOrdenList().remove(orden);
                codemp = em.merge(codemp);
            }
            Proveedor codprov = orden.getCodprov();
            if (codprov != null) {
                codprov.getOrdenList().remove(orden);
                codprov = em.merge(codprov);
            }
            em.remove(orden);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orden> findOrdenEntities() {
        return findOrdenEntities(true, -1, -1);
    }

    public List<Orden> findOrdenEntities(int maxResults, int firstResult) {
        return findOrdenEntities(false, maxResults, firstResult);
    }

    private List<Orden> findOrdenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orden.class));
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

    public Orden findOrden(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orden.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orden> rt = cq.from(Orden.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
