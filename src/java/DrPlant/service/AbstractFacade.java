/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Plant;
import DrPlant.enumerations.*;
import java.util.List;
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
    public List<Plant> getPlantByType(PlantType plantType){
        return getEntityManager().createNamedQuery("getPlantByType").setParameter("plantType", plantType).getResultList();
    }
     public List<Plant> getPlantByPetFriendly(PetFriendly petFriendly){
        return getEntityManager().createNamedQuery("getPlantByPetFriendly").setParameter("petfriendly", petFriendly).getResultList();
    }
    public List<Plant> getPlantByClimate(Climate climate){
        return getEntityManager().createNamedQuery("getPlantByClimate").setParameter("climate", climate).getResultList();
    }
    public List<Plant> getPlantByTypeAndPetFriendly(PlantType plantType, PetFriendly petFriendly){
        return getEntityManager().createNamedQuery("getPlantByTypeAndPetFriendly").
                setParameter("plantType", plantType).setParameter("petfriendly", petFriendly).getResultList();
    }
    public List<Plant> getPlantByTypeAndClimate(PlantType plantType, Climate climate){
        return getEntityManager().createNamedQuery("getPlantByTypeAndClimate").
                setParameter("plantType", plantType).setParameter("climate",climate).getResultList();
    }
    public List<Plant> getPlantByPetFriendlyAndClimate(PetFriendly petFriendly, Climate climate){
        return getEntityManager().createNamedQuery("getPlantByPetFriendlyAndClimate")
                .setParameter("petfriendly", petFriendly).setParameter("climate", climate)
                .getResultList();
    }
    
    List<Plant> getPlantWithAll(PlantType plantType, PetFriendly petfriendly, Climate climate) {
       return getEntityManager().createNamedQuery("getPlantWithAll").setParameter("plantType", plantType)
                .setParameter("climate", climate).setParameter("petfriendly",petfriendly).getResultList();
    }
    public List<Plant> getPlantByCommonName(String commonName){
        return getEntityManager().createNamedQuery("getPlantByCommonName").setParameter("commonName", "%"+commonName+"%").getResultList();
    }
}
