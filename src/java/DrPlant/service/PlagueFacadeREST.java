package DrPlant.service;

import DrPlant.entity.Plague;
import DrPlant.enumerations.PlagueType;
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
 * This class encapsulates the methods to do the RESTfull
 * @author Saray
 */
@Stateless
@Path("drplant.entity.plague")
public class PlagueFacadeREST extends AbstractFacade<Plague> {

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    /**
     * 
     */
    public PlagueFacadeREST() {
        super(Plague.class);
    }

    /**
     * 
     * @param entity the entity with the information to be set
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Plague entity) {
        super.create(entity);
    }

    /**
     * 
     * @param entity the entity to be update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Plague entity) {
        super.edit(entity);
    }

    /**
     * 
     * @param id the id of the entity to be remove
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    /**
     * 
     * @param id the id of the entity to be find
     * @return the entity Plague with the id sended
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Plague find(@PathParam("id") String id) {
        return super.find(id);
    }

    /**
     * 
     * @return em The EntityManager to manage the plague entity
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * 
     * @param commonName the common name of the plague
     * @return the plague with the common name sended
     */
    @GET  // capturar excepciones
    @Path("findPlagueByCommonName/{commonName}")
    @Produces({MediaType.APPLICATION_XML})
    public Plague findPlagueByCommonName (@PathParam("commonName") String commonName){      
        return super.findPlagueByCommonName(commonName);
    }
    
    /**
     * 
     * @param type the type of the plague
     * @return plague with 
     */
    @GET
    @Path("findPlaguesByType/{type}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Plague> findPlaguesByType (@PathParam("type") PlagueType type){     
        return super.findPlaguesByType(type);
    }
    
    
}
