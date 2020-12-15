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
    /**
     *  Search all the plants in the database
     * @return the list of all the plants
     * @throws ReadException 
     */
    public List<Plant> getAllPlants() throws ReadException{
        return getEntityManager().createNamedQuery("getAllPlants").getResultList();
    }
    /**
     * Search all the plants search by the type in the database 
     * @param plantType
     * @return list of plant which are of this type
     * @throws ReadException 
     */
    public List<Plant> getPlantByType(PlantType plantType) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByType").setParameter("plantType", plantType).getResultList();
    }
    /**
     * Search all the plants search by the petfriendly in the database 
     * @param petFriendly
     * @return list of plants that have this petfriendly
     * @throws ReadException 
     */
     public List<Plant> getPlantByPetFriendly(PetFriendly petFriendly) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByPetFriendly").setParameter("petfriendly", petFriendly).getResultList();
    }
     /**
      * Search all the plants search by the climate in the database  
      * @param climate
      * @return list of plants that have this climate
      * @throws ReadException 
      */
    public List<Plant> getPlantByClimate(Climate climate) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByClimate").setParameter("climate", climate).getResultList();
    }
    /**
     * Search all the plants search by the petfriendly and type in the database 
     * @param plantType
     * @param petFriendly
     * @return list of plants that have both params
     * @throws ReadException 
     */
    public List<Plant> getPlantByTypeAndPetFriendly(PlantType plantType, PetFriendly petFriendly) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByTypeAndPetFriendly").
                setParameter("plantType", plantType).setParameter("petfriendly", petFriendly).getResultList();
    }
    /**
     * Search all the plants search by the type and climate in the database 
     * @param plantType
     * @param climate
     * @return list of plants that have both params
     * @throws ReadException 
     */
    public List<Plant> getPlantByTypeAndClimate(PlantType plantType, Climate climate) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByTypeAndClimate").
                setParameter("plantType", plantType).setParameter("climate",climate).getResultList();
    }
    /**
     * Search all the plants search by the petfriendly and the climate in the database 
     * @param petFriendly
     * @param climate
     * @return list of plants that have both params
     * @throws ReadException 
     */
    public List<Plant> getPlantByPetFriendlyAndClimate(PetFriendly petFriendly, Climate climate) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByPetFriendlyAndClimate")
                .setParameter("petfriendly", petFriendly).setParameter("climate", climate)
                .getResultList();
    }
    /**
     * Search all the plants search by the type, petfriendly and the climate in the database 
     * @param plantType
     * @param petfriendly
     * @param climate
     * @return list of plants which have all the params
     * @throws ReadException 
     */
    List<Plant> getPlantWithAll(PlantType plantType, PetFriendly petfriendly, Climate climate) throws ReadException {
       return getEntityManager().createNamedQuery("getPlantWithAll").setParameter("plantType", plantType)
                .setParameter("climate", climate).setParameter("petfriendly",petfriendly).getResultList();
    }/**
     * Search all the plants search by the common name in the database 
     * @param commonName
     * @return list of plant that include the param
     * @throws ReadException 
     */
    public List<Plant> getPlantByCommonName(String commonName) throws ReadException{
        return getEntityManager().createNamedQuery("getPlantByCommonName").setParameter("commonName", "%"+commonName+"%").getResultList();
    }
}
