/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Plant;
import DrPlant.enumerations.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Logger LOGGER
            = Logger.getLogger("drplantserver");

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    public PlantFacadeREST() {
        super(Plant.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Plant plant) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: create: ", plant);
            super.create(plant);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception creating plant: "
                    ,ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Plant plant) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: edit: ");
            super.edit(plant);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception updating plant: "
                    ,ex.getMessage());

        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service:delete");
            super.remove(super.find(id));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by id: "
                    ,ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Plant find(@PathParam("id") String id) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by id", id);
            return super.find(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception updating plant: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getAllPlants() {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search all the plant");
            return super.getAllPlants();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching all plants: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("plantType/{plantType}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByType(@PathParam("plantType") PlantType plantType) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by PlantType", plantType);
            return super.getPlantByType(plantType);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by PlantType: "
                    ,ex.getMessage());
            return null;
        }

    }

    @GET
    @Path("petFriendly/{petFriendly}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByPetFriendly(@PathParam("petFriendly") PetFriendly petFriendly) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by PetFriendly", petFriendly);
            return super.getPlantByPetFriendly(petFriendly);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by PetFriendly: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("climate/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByClimate(@PathParam("climate") Climate climate) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by Climate", climate);
            return super.getPlantByClimate(climate);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by Climate: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("TypeAndPetFriendly/{plantType}/{petFriendly}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByTypeAndPetFriendly(@PathParam("plantType") PlantType plantType, @PathParam("petFriendly") PetFriendly petFriendly) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by PlantType and PetFriendly", plantType + " " + petFriendly);
            return super.getPlantByTypeAndPetFriendly(plantType, petFriendly);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by PlantType and PetFriendly: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("TypeAndClimate/{plantType}/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByTypeAndClimate(@PathParam("plantType") PlantType plantType, @PathParam("climate") Climate climate) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by PlantType and Climate", plantType + " " + climate);
            return super.getPlantByTypeAndClimate(plantType, climate);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by PlantType and Climate : "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("getPlantByPetFriendlyAndClimate/{petFriendly}/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByPetFriendlyAndClimate(@PathParam("petFriendly") PetFriendly petFriendly, @PathParam("climate") Climate climate) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by PetFriendly and Climate", petFriendly + " " + climate);
            return super.getPlantByPetFriendlyAndClimate(petFriendly, climate);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by PetFriendly and Climate: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("getPlantWithAll/{plantType}/{petFriendly}/{climate}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantWithAll(@PathParam("plantType") PlantType plantType, @PathParam("petFriendly") PetFriendly petFriendly, @PathParam("climate") Climate climate) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by PlantType, PetFriendly and Climate", plantType + " " + petFriendly + " " + climate);
            return super.getPlantWithAll(plantType, petFriendly, climate);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by PlantType, PetFriendly and Climate: "
                    ,ex.getMessage());
            return null;
        }
    }

    @GET
    @Path("getPlantByCommonName/{commonName}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plant> getPlantByCommonName(@PathParam("commonName") String commonName) {
        try {
            LOGGER.log(Level.INFO, "PlantRESTful service: search by common name", commonName);
            return super.getPlantByCommonName(commonName);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"PlantRESTful service: Exception searching plant by common name: "
                    ,ex.getMessage());
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
