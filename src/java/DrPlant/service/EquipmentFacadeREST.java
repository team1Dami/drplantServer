/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Equipment;
import DrPlant.enumerations.Use;
import DrPlant.exceptions.ReadException;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
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
 *
 * @author rubir
 */
@Stateless
@Path("equipment")
public class EquipmentFacadeREST extends AbstractFacade<Equipment> {

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    public EquipmentFacadeREST() {
        super(Equipment.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Equipment entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Equipment entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Equipment find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /*@GET 
    @Path("equipment_name/{equipment_name}")
    @Produces ({MediaType.APPLICATION_XML})
    public Equipment findEquipmentByName (@PathParam("equipment_name") String equipment_name){      
        //List <Equipment> equipment;
        //equipment = em.createNamedQuery("findEquipmentByName").setParameter("equipment_name", equipment_name).getResultList();  // throws exception query: IllegalArgumentException 
        //return equipment;
        return super.findEquipmentByName(equipment_name);
    }*/
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Select by the equipment name in the Database
     *
     * @param equipment_name the equipment name
     * @return The equipment of the sended name
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
     *
     * @param uses
     * @return plague with
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
