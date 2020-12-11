/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Plant;
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
@Path("plant")
public class PlantFacadeREST extends AbstractFacade<Plant> {

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    public PlantFacadeREST() {
        super(Plant.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Plant entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Plant entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Plant find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getAllPlants() {
        return super.getAllPlants();
    }

    @GET
    @Path("plantType/{plantType}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByType(@PathParam("plantType") String plantType){
        return super.getPlantByType(plantType);
        
    }

    @GET
    @Path("petFriendly/{petFriendly}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByPetFriendly(@PathParam("petFriendly") String petFriendly){
        return super.getPlantByType(petFriendly);
}
    @GET
    @Path("climate/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByClimate(@PathParam("climate") String climate){
        return super.getPlantByType(climate);
        
    }
    @GET
    @Path("TypeAndPetFriendly/{plantType}/{petFriendly}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByTypeAndPetFriendly(@PathParam("plantType") String plantType,@PathParam("petFriendly")  String petFriendly){
        return super.getPlantByTypeAndPetFriendly(plantType, petFriendly);
        
    }
    @GET
    @Path("TypeAndClimate/{plantType}/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByTypeAndClimate(@PathParam("plantType") String plantType,@PathParam("climate") String climate){
        return super.getPlantByTypeAndClimate(plantType,climate);
        
    }
    @GET
    @Path("getPlantByPetFriendlyAndClimate/{petFriendly}/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByPetFriendlyAndClimate(@PathParam("petFriendly") String petFriendly,@PathParam("climate") String climate){
        return super.getPlantByPetFriendlyAndClimate(petFriendly,climate);
    
        
    }
    @GET
    @Path("getPlantWithAll/{plantType}/{petFriendly}/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantWithAll(@PathParam("plantType") String plantType, @PathParam("petFriendly") String petFriendly, @PathParam("climate") String climate){
        return super.getPlantWithAll(plantType,petFriendly,climate);
        
    }
    @GET
    @Path("getPlantByCommonName/{commonName}")
    @Produces({MediaType.APPLICATION_XML})
    public Plant getPlantByCommonName(@PathParam("commonName") String commonName){
        return super.getPlantByCommonName(commonName);
        
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
