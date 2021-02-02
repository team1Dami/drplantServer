package DrPlant.service;

import DrPlant.entity.Shop;
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
 * This class encapsultaes the data of each shop:
 * <ul>
 *  <li><strong>id_shop:</strong> It's the id of the shop. It's the identifier</li>
 *  <li><strong>shop_name:</strong> It's the shop's name</li>
 *  <li><strong>url:</strong> It's the shop's url</li>
 *  <li><strong>location:</strong> It's the shop's location</li>
 *  <li><strong>commission:</strong> It's the commission that we receive of the shop</li>
 *  <li><strong>email:</strong> It's the shop's email</li>
 *  <li><strong>plants:</strong> Collection of objects of the Plant class that have to sold</li>
 *  <li><strong>equipments:</strong> Collection of objects of the Equipment class that have to sold</li>
 * </ul>
 * 
 * @author Gonza
 */ 
@Stateless
@Path("shop")
public class ShopFacadeREST extends AbstractFacade<Shop> {

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;
    private static final Logger LOGGER =
            Logger.getLogger("DrPlant.service.ShopFacadeREST");

    public ShopFacadeREST() {
        super(Shop.class);
    }

    /**
     * Method to create a new shop
     * @param entity 
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Shop entity) {

        try {

            super.create(entity);
            LOGGER.log(Level.INFO, "ShopRESTful service: create Shop");

        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception creating Shop",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to update shop by id from the database
     * @param entity 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Shop entity) {
        try {
            super.edit(entity);
            LOGGER.log(Level.INFO, "ShopRESTful service: update Shop ");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception updating Shop", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }

    }

    /**
     * Method to delete shop by id from the database
     * @param id
     * @throws ReadException 
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) throws ReadException {

        try {
            super.remove(super.find(id));
            LOGGER.log(Level.INFO, "ShopRESTful service: delete shop by id");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception deleting shop by id", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to find shop by id from the database
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Shop find(@PathParam("id") Long id) {

        Shop shop = null;
        try {
            shop=super.find(id);
            LOGGER.log(Level.INFO, "ShopRESTful service: find shop by id");

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception reading shop by id",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return shop;
    }

    /**
     * Method to find all shops from the database
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Shop> findAllShops() {

        List <Shop> shops = null;
        try {

            LOGGER.log(Level.INFO, "ShopRESTful service: find shops");
            shops = super.findAllShops();

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception reading shops",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return shops;
    }

    /**
     * Method to find shop by name from the database
     * @param shop_name
     * @return 
     */
    @GET
    @Path("shop_name/{shop_name}")
    @Produces({MediaType.APPLICATION_XML})
    public Shop getShopByName(@PathParam("shop_name") String shop_name) {

        try {

            LOGGER.log(Level.INFO, "ShopRESTful service: find shops");
            return super.findShopName(shop_name);

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception reading shops",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    /**
     * 
     * @return 
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
