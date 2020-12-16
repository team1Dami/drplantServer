/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Equipment;
import DrPlant.exceptions.ReadException;
import DrPlant.entity.*;
import DrPlant.enumerations.*;
import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ruben
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws CreateException, UserExistException {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) throws UpdateException {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) throws DeleteException {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) throws ReadException {
        return getEntityManager().find(entityClass, id);
    }
    /**
     * Select by the equipment name in the Database
     *
     * @param equipment_name the equipment name
     * @return The equipment object of the sended name
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findEquipmentByName(Object equipment_name) throws ReadException {
        return getEntityManager()
                .createNamedQuery("findEquipmentByName")
                .setParameter("equipment_name", "%" + equipment_name + "%")
                .getResultList();
    }
    /**
     * Select of the equipments with a specific use
     * @param uses The use of the equipment
     * @return A List of the equipment of the specified use
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findEquipmentByUse(Object uses) throws ReadException {
        return getEntityManager()
                .createNamedQuery("findEquipmentByUse")
                .setParameter("use_equipment", uses)
                .getResultList();
    }
    /**
     * List all the equipment stored
     * @return A List with all the equipment
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findAllEquipment() throws ReadException {
        return getEntityManager()
                .createNamedQuery("findAllEquipment")
                .getResultList();
    }
    /**
     * Find equipment by price
     * @param minPrice 
     * @param maxPrice
     * @return A List with all the equipment in the price balance
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findEquipmentByPrice(Object minPrice, Object maxPrice) throws ReadException {
        return getEntityManager()
                .createNamedQuery("findEquipmentByPrice")
                .setParameter("min_price", minPrice)
                .setParameter("max_price", maxPrice)
                .getResultList();
    }

    /**
     * Search all the plants in the database
     *
     * @return the list of all the plants
     * @throws ReadException
     */
    public List<Plant> getAllPlants() throws ReadException {
        return getEntityManager()
                .createNamedQuery("getAllPlants")
                .getResultList();
    }

    /**
     * Search all the plants search by the type in the database
     *
     * @param plantType
     * @return list of plant which are of this type
     * @throws ReadException
     */
    public List<Plant> getPlantByType(PlantType plantType) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByType")
                .setParameter("plantType", plantType)
                .getResultList();
    }

    /**
     * Search all the plants search by the petfriendly in the database
     *
     * @param petFriendly
     * @return list of plants that have this petfriendly
     * @throws ReadException
     */
    public List<Plant> getPlantByPetFriendly(PetFriendly petFriendly) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByPetFriendly")
                .setParameter("petfriendly", petFriendly)
                .getResultList();
    }

    /**
     * Search all the plants search by the climate in the database
     *
     * @param climate
     * @return list of plants that have this climate
     * @throws ReadException
     */
    public List<Plant> getPlantByClimate(Climate climate) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByClimate")
                .setParameter("climate", climate)
                .getResultList();
    }

    /**
     * Search all the plants search by the petfriendly and type in the database
     *
     * @param plantType
     * @param petFriendly
     * @return list of plants that have both params
     * @throws ReadException
     */
    public List<Plant> getPlantByTypeAndPetFriendly(PlantType plantType, PetFriendly petFriendly) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByTypeAndPetFriendly")
                .setParameter("plantType", plantType)
                .setParameter("petfriendly", petFriendly)
                .getResultList();
    }

    /**
     * Search all the plants search by the type and climate in the database
     *
     * @param plantType
     * @param climate
     * @return list of plants that have both params
     * @throws ReadException
     */
    public List<Plant> getPlantByTypeAndClimate(PlantType plantType, Climate climate) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByTypeAndClimate")
                .setParameter("plantType", plantType)
                .setParameter("climate", climate)
                .getResultList();
    }

    /**
     * Search all the plants search by the petfriendly and the climate in the
     * database
     *
     * @param petFriendly
     * @param climate
     * @return list of plants that have both params
     * @throws ReadException
     */
    public List<Plant> getPlantByPetFriendlyAndClimate(PetFriendly petFriendly, Climate climate) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByPetFriendlyAndClimate")
                .setParameter("petfriendly", petFriendly)
                .setParameter("climate", climate)
                .getResultList();
    }

    /**
     * Search all the plants search by the type, petfriendly and the climate in
     * the database
     *
     * @param plantType
     * @param petfriendly
     * @param climate
     * @return list of plants which have all the params
     * @throws ReadException
     */
    List<Plant> getPlantWithAll(PlantType plantType, PetFriendly petfriendly, Climate climate) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantWithAll")
                .setParameter("plantType", plantType)
                .setParameter("climate", climate)
                .setParameter("petfriendly", petfriendly)
                .getResultList();
    }

    /**
     * Search all the plants search by the common name in the database
     *
     * @param commonName
     * @return list of plant that include the param
     * @throws ReadException
     */
    public List<Plant> getPlantByCommonName(String commonName) throws ReadException {
        return getEntityManager()
                .createNamedQuery("getPlantByCommonName")
                .setParameter("commonName", "%" + commonName + "%")
                .getResultList();

    }
    //Method to list every shop in the database

    public List<Shop> findAllShops() throws ReadException {
        return getEntityManager()
                .createNamedQuery("getAllShops")
                .getResultList();
    }

    //Method to find a single shop inside the database with the name of the shop
    public Shop findShopName(Object shop_name) throws ReadException {
        return (Shop) getEntityManager()
                .createNamedQuery("getShopByName")
                .setParameter("shop_name", shop_name)
                .getSingleResult();
    }

    //Method to list every user in the database
    public List<User> findAllUsers() throws ReadException {
        return getEntityManager()
                .createNamedQuery("getAllUsers")
                .getResultList();
    }

    //Method tofind a especific user by the login and the password
    public User findLogin(Object login, Object passwd) throws ReadException {
        return (User) getEntityManager()
                .createNamedQuery("findUserByLoginAndPasswd")
                .setParameter("login", login)
                .setParameter("passwd", passwd)
                .getSingleResult();
    }

    /**
     * Method to get the plague by the common name sended
     *
     * @param commonName string commonName to find the plague if it has
     * @return the object type Plague with the common Name sended
     * @throws DrPlant.exceptions.ReadException
     */
    public Plague findPlagueByCommonName(Object commonName) throws ReadException {
        return (Plague) getEntityManager()
                .createNamedQuery("findPlagueByCommonName")
                .setParameter("commonName", commonName)
                .getSingleResult();
    }

    /**
     * Method to get the List of the plagues by the plague type sended
     *
     * @param type enum PlagueType sended to search all the plagues of this type
     * @return a List with the plagues that matches with the type sended
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Plague> findPlaguesByType(Object type) throws ReadException {
        return getEntityManager()
                .createNamedQuery("findPlaguesByType")
                .setParameter("type", type)
                .getResultList();
    }

    /**
     * Method to get all the plagues
     *
     * @return List with all the plagues in the DB
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Plague> findAllPlagues() throws ReadException {  // throw new WebApplicationException(Response.Status.NOT_FOUND);  // NotFoundException  //InternalServerErrorException  // ServiceUnavailableException
        return getEntityManager()
                .createNamedQuery("findAllPlagues")
                .getResultList();
    }
}
