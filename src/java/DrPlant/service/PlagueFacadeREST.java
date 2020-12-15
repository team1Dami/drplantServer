package DrPlant.service;

import DrPlant.entity.Plague;
import DrPlant.enumerations.PlagueType;
import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.ReadException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
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
 * This class encapsulates the methods to do the RESTfull
 *
 * @author Saray
 */
@Stateless
@Path("plague")
public class PlagueFacadeREST extends AbstractFacade<Plague> {

    private static final Logger LOGGER
            = Logger.getLogger("drplantServer");

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    /**
     * The constructor
     */
    public PlagueFacadeREST() {
        super(Plague.class);
    }

    /**
     * Method to create a plague
     *
     * @param entity the entity with the information to be set
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Plague entity) {

        LOGGER.log(Level.INFO, "PlagueRESTful service: create ", entity);

        try {
            super.create(entity);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());

            throw new InternalServerErrorException(ex);
        } catch (UserExistException ex) {
            Logger.getLogger(PlagueFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to update a plague
     *
     * @param entity the entity to be update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Plague entity) {

        LOGGER.log(Level.INFO, "PlagueRESTful service: update ", entity);

        try {
            super.edit(entity);

        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());

            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to remove a plague by the id
     *
     * @param id the id of the entity to be remove
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {

        LOGGER.log(Level.INFO, "PlagueRESTful service: delete ", id);
        try {
            super.remove(super.find(id));

        } catch (DeleteException | ReadException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());

            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to find a plague by the id
     *
     * @param id the id of the entity to be find
     * @return the entity Plague with the id sended
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Plague find(@PathParam("id") String id) {
        LOGGER.log(Level.INFO, "PlagueRESTful service: read ", id);

        Plague plague = null;

        try {
            plague = super.find(id);

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());

            throw new InternalServerErrorException(ex);
        }

        return plague;
    }

    /**
     * Method to obtain the entity manager
     *
     * @return em The EntityManager to manage the plague entity
     */
    @Override
    protected EntityManager getEntityManager() {
        LOGGER.log(Level.INFO, "PlagueRESTful service: get EntityManager ");

        return em;
    }

    /**
     * Method to obtain the plague with the requested common name
     *
     * @param commonName the common name of the plague
     * @return the plague with the common name sended
     */
    @GET
    @Path("findPlagueByCommonName/{commonName}")
    @Produces({MediaType.APPLICATION_XML})
    public Plague findPlagueByCommonName(@PathParam("commonName") String commonName) {

        LOGGER.log(Level.INFO, "PlagueRESTful service: find by ", commonName);

        Plague plague = null;

        try {
            plague = super.findPlagueByCommonName(commonName);

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());

            throw new InternalServerErrorException(ex);
        }

        return plague;
    }

    /**
     * Method to obtain a plague list of a specific type
     *
     * @param type the type of the plague
     * @return List plagues the list of plagues that have the searched type
     */
    @GET
    @Path("findPlaguesByType/{type}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plague> findPlaguesByType(@PathParam("type") PlagueType type) {

        LOGGER.log(Level.INFO, "PlagueRESTful service: find all by ", type);

        List<Plague> plagues = null;

        try {
            plagues = super.findPlaguesByType(type);

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());

            throw new InternalServerErrorException(ex);
        }

        return plagues;
    }

    /**
     * Method to obtain all the plagues
     *
     * @return List plagues the list with all the plagues
     */
    @GET
    public List<Plague> findAllPlagues() {

        LOGGER.log(Level.INFO, "PlagueRESTful service: find all plagues");

        List<Plague> plagues = null;

        try {
            plagues = super.findAllPlagues();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "PlagueRESTful service: server Error ", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return plagues;
    }
}
