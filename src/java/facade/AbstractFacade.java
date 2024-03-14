package facade;

import jakarta.persistence.EntityManager;
import java.util.List;


public abstract class AbstractFacade<T> {
    private final Class<T> entityClass ;
    
    public AbstractFacade(Class<T> entityClass){
        this.entityClass = entityClass ;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public T find(Object id){
        return getEntityManager().find(entityClass, id) ;
    }
    
    public List<T> findAll() { 
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList(); 
    }
    
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    
} 