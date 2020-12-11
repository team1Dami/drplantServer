/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Plant;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;

/**
 *
 * @author Ruben
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
    
    public List<Plant> getAllPlants(){
        return getEntityManager().createNamedQuery("getAllPlants").getResultList();
    }
    public List<Plant> getPlantByType(String type){
        return getEntityManager().createNamedQuery("getPlantByType").setParameter("plantType", type).getResultList();
    }
     public List<Plant> getPlantByPetFriendly(String petFriendly){
        return getEntityManager().createNamedQuery("getPlantByPetFriendly").setParameter("petfriendly", petFriendly).getResultList();
    }
    public List<Plant> getPlantByClimate(String climate){
        return getEntityManager().createNamedQuery("getPlantByClimate").setParameter("climate", climate).getResultList();
    }
    public List<Plant> getPlantByTypeAndPetFriendly(String type, String petFriendly){
        return getEntityManager().createNamedQuery("getPlantByTypeAndPetFriendly").
                setParameter("plantType", type).setParameter("petfriendly", petFriendly).getResultList();
    }
    public List<Plant> getPlantByTypeAndClimate(String type, String climate){
        return getEntityManager().createNamedQuery("getPlantByTypeAndClimate").
                setParameter("plantType", type).setParameter("climate",climate).getResultList();
    }
    public List<Plant> getPlantByPetFriendlyAndClimate(String petFriendly, String climate){
        return getEntityManager().createNamedQuery("getPlantByPetFriendlyAndClimate")
                .setParameter("petfriendly", petFriendly).setParameter("climate", climate)
                .getResultList();
    }
    public List<Plant>  getPlantWithAll(String type,String climate, String petfriendly){
        return getEntityManager().createNamedQuery("getPlantWithAll").setParameter("plantType", type)
                .setParameter("climate", climate).setParameter("petfriendly",petfriendly).getResultList();
    }
    
    
}
