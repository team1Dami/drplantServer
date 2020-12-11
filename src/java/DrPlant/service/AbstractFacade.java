package DrPlant.service;

import DrPlant.entity.Plague;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * 
 * @author rubir
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    /**
     * Method to set the entity class to manage the FacadeREST service
     * @param entityClass the entity to be set
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Method to create persist of the entity sended
     * @param entity the entity
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Method to edit the entity
     * @param entity the entity to be edit
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Method to remove the entity sended
     * @param entity the entity to be remove
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Method to find the info of the entity by the id sended
     * @param id the id of the entity to be search
     * @return if exist the entity with the id finded
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Method to get the plague by the common name sended
     *
     * @param commonName string commonName to find the plague if it has
     * @return the object type Plague with the common Name sended
     */
    public Plague findPlagueByCommonName(Object commonName) {
        return (Plague) getEntityManager().createNamedQuery("findPlagueByCommonName").setParameter("commonName", commonName).getSingleResult();
    }

    /**
     * Method to get the List of the plagues by the plague type sended
     *
     * @param type enum PlagueType sended to search all the plagues of this type
     * @return a List with the plagues that matches with the type sended
     */
    public List<Plague> findPlaguesByType(Object type) {
        return getEntityManager().createNamedQuery("findPlaguesByType").setParameter("type", type).getResultList();
    }
    
    /**
     * Method to get all the plagues
     * @return List with all the plagues in the DB
     */
    public List<Plague> findAllPlagues() {
        return getEntityManager().createNamedQuery("findAllPlagues").getResultList();
    }
}
