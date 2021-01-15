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
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * This class encapsulate the methods to do the RESTful services CREATE, READ,
 * UPDATE, DELETE
 *
 * @author Ruben, Saray, Eneko, Gonzalo
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    /**
     * Method to set the entity
     *
     * @param entityClass the entity to be set
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Method to get the entityManager
     *
     * @return EntityManager the entityManager
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Method to create the entity
     *
     * @param entity the entity to be created
     * @throws CreateException if a server error ocurrs in creating action
     */
    public void create(T entity) throws CreateException {

        try {
            getEntityManager().persist(entity);
        } catch (Exception ex) {
            throw new CreateException(ex.getMessage());
        }
    }

    /**
     * Method to update an entity
     *
     * @param entity the entity to be updated
     * @throws UpdateException if a server error ocurrs in the updating action
     */
    public void edit(T entity) throws UpdateException {
        try {
            getEntityManager().merge(entity);
        } catch (Exception ex) {
            throw new UpdateException(ex.getMessage());
        }
    }

    /**
     * Method to remove an entity
     *
     * @param entity the entity to be removed
     * @throws DeleteException if a server error ocurrs in the deleting action
     */
    public void remove(T entity) throws DeleteException {

        try {
            getEntityManager()
                    .remove(getEntityManager()
                            .merge(entity));
        } catch (Exception ex) {
            throw new DeleteException(ex.getMessage());
        }
    }

    /**
     * Method to get the entity that has the id sended
     *
     * @param id the id to find the entity
     * @return entity the entity that has the id sended
     * @throws ReadException if a server error ocurrs during reading action
     */
    public T find(Object id) throws ReadException {
        try {
            return getEntityManager()
                    .find(entityClass, id);
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Select by the equipment name in the Database
     *
     * @param equipment_name the equipment name
     * @return The equipment object of the sended name
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findEquipmentByName(Object equipment_name) throws ReadException {

        try {
            return getEntityManager()
                    .createNamedQuery("findEquipmentByName")
                    .setParameter("equipment_name", "%" + equipment_name + "%")
                    .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Select of the equipments with a specific use
     *
     * @param uses The use of the equipment
     * @return A List of the equipment of the specified use
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findEquipmentByUse(Object uses) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("findEquipmentByUse")
                .setParameter("use_equipment", uses)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * List all the equipment stored
     *
     * @return A List with all the equipment
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findAllEquipment() throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("findAllEquipment")
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Find equipment by price
     *
     * @param minPrice
     * @param maxPrice
     * @return A List with all the equipment in the price balance
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Equipment> findEquipmentByPrice(Object minPrice, Object maxPrice) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("findEquipmentByPrice")
                .setParameter("min_price", minPrice)
                .setParameter("max_price", maxPrice)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Search all the plants in the database
     *
     * @return the list of all the plants
     * @throws ReadException
     */
    public List<Plant> getAllPlants() throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getAllPlants")
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Search all the plants search by the type in the database
     *
     * @param plantType
     * @return list of plant which are of this type
     * @throws ReadException
     */
    public List<Plant> getPlantByType(PlantType plantType) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByType")
                .setParameter("plantType", plantType)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Search all the plants search by the petfriendly in the database
     *
     * @param petFriendly
     * @return list of plants that have this petfriendly
     * @throws ReadException
     */
    public List<Plant> getPlantByPetFriendly(PetFriendly petFriendly) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByPetFriendly")
                .setParameter("petfriendly", petFriendly)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Search all the plants search by the climate in the database
     *
     * @param climate
     * @return list of plants that have this climate
     * @throws ReadException
     */
    public List<Plant> getPlantByClimate(Climate climate) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByClimate")
                .setParameter("climate", climate)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
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
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByTypeAndPetFriendly")
                .setParameter("plantType", plantType)
                .setParameter("petfriendly", petFriendly)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
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
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByTypeAndClimate")
                .setParameter("plantType", plantType)
                .setParameter("climate", climate)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
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
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByPetFriendlyAndClimate")
                .setParameter("petfriendly", petFriendly)
                .setParameter("climate", climate)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
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
        try {
            return getEntityManager()
                .createNamedQuery("getPlantWithAll")
                .setParameter("plantType", plantType)
                .setParameter("climate", climate)
                .setParameter("petfriendly", petfriendly)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Search all the plants search by the common name in the database
     *
     * @param commonName
     * @return list of plant that include the param
     * @throws ReadException
     */
    public List<Plant> getPlantByCommonName(String commonName) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getPlantByCommonName")
                .setParameter("commonName", "%" + commonName + "%")
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }

    }

    /**
     * Method to list every shop in the database
     *
     * @return
     * @throws ReadException
     */
    public List<Shop> findAllShops() throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getAllShops")
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Method to find a single shop inside the database with the name of the
     * shop
     *
     * @param shop_name
     * @return
     * @throws ReadException
     */
    public Shop findShopName(Object shop_name) throws ReadException {
        try {
            return (Shop) getEntityManager()
                .createNamedQuery("getShopByName")
                .setParameter("shop_name", shop_name)
                .getSingleResult();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    //Method to list every user in the database
    public List<User> findAllUsers() throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("getAllUsers")
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Method tofind a especific user by the login and the password
     *
     * @param login
     * @param passwd
     * @return
     * @throws ReadException
     */
    public User findUserByLoginAndPasswd(Object login, Object passwd) throws ReadException {
        try {
            return (User) getEntityManager()
                .createNamedQuery("findUserByLoginAndPasswd")
                .setParameter("login", login)
                .setParameter("passwd", passwd)
                .getSingleResult();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Method tofind a especific user by the login and the password
     *
     * @param login
     * @param passwd
     * @return
     * @throws ReadException
     */
    public User findUserByLogin(Object login){

         User u = null;
        try {
            u = (User) getEntityManager()
                .createNamedQuery("findUserByLogin")
                .setParameter("login", login)
                .getSingleResult();
              
        } catch (NoResultException ex) {
            return null;         
        }
        return u;
    }

    /**
     * Method to get the plague by the common name sended
     *
     * @param commonName string commonName to find the plague if it has
     * @return the object type Plague with the common Name sended
     * @throws DrPlant.exceptions.ReadException
     */
    public Plague findPlagueByCommonName(Object commonName) throws ReadException {
        try {
            return (Plague) getEntityManager()
                .createNamedQuery("findPlagueByCommonName")
                .setParameter("commonName", commonName)
                .getSingleResult();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Method to get the List of the plagues by the plague type sended
     *
     * @param type enum PlagueType sended to search all the plagues of this type
     * @return a List with the plagues that matches with the type sended
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Plague> findPlaguesByType(Object type) throws ReadException {
        try {
            return getEntityManager()
                .createNamedQuery("findPlaguesByType")
                .setParameter("type", type)
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }

    /**
     * Method to get all the plagues
     *
     * @return List with all the plagues in the DB
     * @throws DrPlant.exceptions.ReadException
     */
    public List<Plague> findAllPlagues() throws ReadException {  // throw new WebApplicationException(Response.Status.NOT_FOUND);  // NotFoundException  //InternalServerErrorException  // ServiceUnavailableException
        try {
            return getEntityManager()
                .createNamedQuery("findAllPlagues")
                .getResultList();
        } catch (Exception ex) {
            throw new ReadException(ex.getMessage());
        }
    }
}
