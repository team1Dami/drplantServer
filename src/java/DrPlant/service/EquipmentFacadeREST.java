/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Equipment;
import DrPlant.enumerations.Use;
import DrPlant.exceptions.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This class encapsule the methods of the Equipment 
 * @author Eneko
 */
@Stateless
@Path("equipment")
public class EquipmentFacadeREST extends AbstractFacade<Equipment> {

    private static final Logger LOGGER
            = Logger.getLogger("drplantserver");

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    public EquipmentFacadeREST() {
        super(Equipment.class);
    }
/**
 * Method that insert a equipment
 * @param entity 
 */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Equipment entity) {
        try {
            super.create(entity);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
        } catch (UserExistException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
        }
    }
/**
 * Method that update a equipment
 * @param entity get the equipment
 */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Equipment entity) {
        try {
            super.edit(entity);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
        }
    }
/**
 * Method that delete a equipment
 * @param id id of the equipment
 */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            super.remove(super.find(id));
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
        }
    }
/**
 * Method that get a equipment by id
 * @param id id of the equipment
 * @return equipment  
 */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Equipment find(@PathParam("id") Long id) {
        Equipment equipment = null;
        try {
            equipment = super.find(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
        }
        return equipment;
    }
/**
 * 
 * @return entityManager 
 */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Select by the equipment name in the Database
     *
     * @param equipment_name the equipment name
     * @return The equipment object of the sended name
     */
    @GET
    @Path("equipment_name/{equipment_name}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Equipment> findEquipmentByName(@PathParam("equipment_name") String equipment_name) {

        List<Equipment> equipment;
        try {
            equipment = super.findEquipmentByName(equipment_name);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return equipment;
    }

    /**
     * Select of the equipments with a specific use
     * @param uses The use of the equipment
     * @return A List of the equipment of the specified use
     */
    @GET
    @Path("uses/{uses}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Equipment> findEquipmentByUse(@PathParam("uses") Use uses) {

        List<Equipment> equipment;
        try {
            equipment = super.findEquipmentByUse(uses);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return equipment;
    }

    /**
     * List all the equipment stored
     * @return A List with all the equipment
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public List<Equipment> findAllEquipment() {

        List<Equipment> equipment;
        try {
            equipment = super.findAllEquipment();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return equipment;
    }

    /**
     * Find equipment by price
     * @param minPrice 
     * @param maxPrice
     * @return A List with all the equipment in the price balance
     */
    @GET
    @Path("price/{minPrice}/{maxPrice}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Equipment> findEquipmentByPrice(@PathParam("minPrice") float minPrice, @PathParam("maxPrice") float maxPrice) {
        List<Equipment> equipment;
        try {
            equipment = super.findEquipmentByPrice(minPrice, maxPrice);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "EquipmentRESTful service: server Error ", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return equipment;
    }
}
