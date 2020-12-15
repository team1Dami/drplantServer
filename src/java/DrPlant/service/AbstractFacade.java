/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Equipment;
import DrPlant.exceptions.ReadException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;

/**
 *
 * @author rubir
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<Equipment> findEquipmentByName (Object equipment_name) throws ReadException{      
        return getEntityManager().createNamedQuery("findEquipmentByName").setParameter("equipment_name", "%"+equipment_name+"%").getResultList();
    }
    
    public List<Equipment> findEquipmentByUse (Object uses) throws ReadException{
        return getEntityManager().createNamedQuery("findEquipmentByUse").setParameter("use_equipment", uses).getResultList();
    }

    public List<Equipment> findAllEquipment() throws ReadException {
        return getEntityManager().createNamedQuery("findAllEquipment").getResultList();
    }

    public List<Equipment> findEquipmentByPrice(Object minPrice, Object maxPrice) throws ReadException{
        return getEntityManager().createNamedQuery("findEquipmentByPrice").setParameter("min_price", minPrice).setParameter("max_price", maxPrice).getResultList();
    
    }
}
