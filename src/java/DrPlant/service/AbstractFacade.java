/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Equipment;
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
    
    public Equipment findEquipmentByName (@PathParam("equipment_name") String equipment_name){      
        return (Equipment) getEntityManager().createNamedQuery("findEquipmentByName").setParameter("equipment_name", equipment_name).getSingleResult();
    }
}
