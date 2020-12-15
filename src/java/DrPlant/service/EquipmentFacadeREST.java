/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Equipment;
import DrPlant.enumerations.Use;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    /**
     * Select by the equipment name in the Database
     * @param equipment_name the equipment name
     * @return The equipment of the sended name
     */
    @GET 
    @Path("equipment_name/{equipment_name}")
    @Produces ({MediaType.APPLICATION_XML})
    public List<Equipment> findEquipmentByName (@PathParam("equipment_name") String equipment_name){   
        return super.findEquipmentByName(equipment_name);
    }
    
    /**
     * 
     * @param uses
     * @return plague with 
     */
    @GET
    @Path("uses/{uses}")
    @Produces ({MediaType.APPLICATION_XML})
    public List<Equipment> findEquipmentByUse (@PathParam("uses") Use uses){     
        return super.findEquipmentByUse(uses);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Override
    public List<Equipment> findAllEquipment() {
        return super.findAllEquipment();
    }
    
    @GET
    @Path("price/{minPrice}/{maxPrice}")
    @Produces ({MediaType.APPLICATION_XML})
    public List<Equipment> findEquipmentByPrice (@PathParam("minPrice") float minPrice, @PathParam("maxPrice") float maxPrice){     
        return super.findEquipmentByPrice(minPrice, maxPrice);
    }
}
