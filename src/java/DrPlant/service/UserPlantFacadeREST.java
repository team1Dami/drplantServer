package DrPlant.service;

import DrPlant.entity.UserPlant;
import DrPlant.entity.UserPlantId;
import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.ReadException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
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
import javax.ws.rs.core.PathSegment;

/**
 * This class encapsultes the method to do the RESTful services of the userPlant
 * 
 * @author rubir
 */
@Stateless
@Path("userplant")
public class UserPlantFacadeREST extends AbstractFacade<UserPlant> {

    private static final Logger LOGGER = Logger.getLogger("DrPlant.service.EquipmentFacadeREST");

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;
    
    /**
     * Method to set the pahtSegment
     * @param pathSegment the pathSegment
     * @return key the key of the userPlantId
     */
    private UserPlantId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;scienceName=scienceNameValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        DrPlant.entity.UserPlantId key = new DrPlant.entity.UserPlantId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Integer(userId.get(0)));
        }
        java.util.List<String> scienceName = map.get("scienceName");
        if (scienceName != null && !scienceName.isEmpty()) {
            key.setScienceName(scienceName.get(0));
        }
        return key;
    }
    
    /**
     * 
     */
    public UserPlantFacadeREST() {
        super(UserPlant.class);
    }

    /**
     * Method to create the entity
     * @param entity the entity to be created
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(UserPlant entity) {
        try {
            super.create(entity);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "UserPlanttRESTful service: server Error ", ex.getMessage());
        } 
    }

    /**
     * Method to edit the entity
     * @param entity the entity to be edited
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(UserPlant entity) {
        try {
            super.edit(entity);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "UserPlanttRESTful service: server Error ", ex.getMessage());
        }
    }

    /**
     * Method to remove the entity
     * @param id the id of the entity to be removed
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        DrPlant.entity.UserPlantId key = getPrimaryKey(id);
        try {
            super.remove(super.find(key));
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserPlanttRESTful service: server Error ", ex.getMessage());
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "UserPlanttRESTful service: server Error ", ex.getMessage());
        }
    }
    
    /**
     * Method to find the UserPlant
     * @param id the id to be searched
     * @return userPlant the userPlant that has the id sended
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public UserPlant find(@PathParam("id") PathSegment id) {
        DrPlant.entity.UserPlantId key = getPrimaryKey(id);
        UserPlant userPlant = null;
        try {
            userPlant = super.find(key);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "UserPlanttRESTful service: server Error ", ex.getMessage());
        }
        return userPlant;
    }
    
    /**
     * Method to get the entityManager
     * @return em the EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
