package persistencia;

import negocio.Pedido;
import negocio.Empleado;
import negocio.Cliente;
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

public class PedidoJpaController implements Serializable {

    public PedidoJpaController() {
        emf=Persistence.createEntityManagerFactory("SupermercadoPiscoyaPU");
    }

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente codcli = pedido.getCodcli();
            if (codcli != null) {
                codcli = em.getReference(codcli.getClass(), codcli.getCod());
                pedido.setCodcli(codcli);
            }
            Empleado codemp = pedido.getCodemp();
            if (codemp != null) {
                codemp = em.getReference(codemp.getClass(), codemp.getCod());
                pedido.setCodemp(codemp);
            }
            em.persist(pedido);
            if (codcli != null) {
                codcli.getPedidoList().add(pedido);
                codcli = em.merge(codcli);
            }
            if (codemp != null) {
                codemp.getPedidoList().add(pedido);
                codemp = em.merge(codemp);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPedido(pedido.getNum()) != null) {
                throw new PreexistingEntityException("Pedido " + pedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getNum());
            Cliente codcliOld = persistentPedido.getCodcli();
            Cliente codcliNew = pedido.getCodcli();
            Empleado codempOld = persistentPedido.getCodemp();
            Empleado codempNew = pedido.getCodemp();
            if (codcliNew != null) {
                codcliNew = em.getReference(codcliNew.getClass(), codcliNew.getCod());
                pedido.setCodcli(codcliNew);
            }
            if (codempNew != null) {
                codempNew = em.getReference(codempNew.getClass(), codempNew.getCod());
                pedido.setCodemp(codempNew);
            }
            pedido = em.merge(pedido);
            if (codcliOld != null && !codcliOld.equals(codcliNew)) {
                codcliOld.getPedidoList().remove(pedido);
                codcliOld = em.merge(codcliOld);
            }
            if (codcliNew != null && !codcliNew.equals(codcliOld)) {
                codcliNew.getPedidoList().add(pedido);
                codcliNew = em.merge(codcliNew);
            }
            if (codempOld != null && !codempOld.equals(codempNew)) {
                codempOld.getPedidoList().remove(pedido);
                codempOld = em.merge(codempOld);
            }
            if (codempNew != null && !codempNew.equals(codempOld)) {
                codempNew.getPedidoList().add(pedido);
                codempNew = em.merge(codempNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pedido.getNum();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Cliente codcli = pedido.getCodcli();
            if (codcli != null) {
                codcli.getPedidoList().remove(pedido);
                codcli = em.merge(codcli);
            }
            Empleado codemp = pedido.getCodemp();
            if (codemp != null) {
                codemp.getPedidoList().remove(pedido);
                codemp = em.merge(codemp);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
