/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Gonza
 */ 
@Stateless
@Path("shop")
public class ShopFacadeREST extends AbstractFacade<Shop> {

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;
    private Logger LOGGER;

    public ShopFacadeREST() {
        super(Shop.class);
    }

    //Method to create a new shop
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Shop entity) {

      
         try {
            
            super.create(entity);
            LOGGER.log(Level.INFO,"ShopRESTful service: create Shop");
            
        }catch (CreateException  | UserExistException ex) {
            LOGGER.log(Level.SEVERE, 
                    "ShopRESTful service: Exception creating Shop",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    //Method to update shop by id from the database
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Shop entity) {
         try {
            super.edit(entity);
            LOGGER.log(Level.INFO,"ShopRESTful service: update Shop ");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception updating Shop",ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
    }

    //Method to delete shop by id from the database
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) throws ReadException {

        try {
            super.remove(super.find(id));
            LOGGER.log(Level.INFO,"ShopRESTful service: delete shop by id");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception deleting shop by id",ex.getMessage());
            throw new InternalServerErrorException(ex);
        } 
    }
    
    //Method to find shop by id from the database
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Shop find(@PathParam("id") Long id) {
       
        Shop shop=null;
        try {
            super.find(id);
            LOGGER.log(Level.INFO,"ShopRESTful service: find shop by id");
           
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception reading shop by id",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return shop;
    }
    
    //Method to find all shops from the database
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Shop> findAllShops() {
        
        try {
            
            LOGGER.log(Level.INFO,"ShopRESTful service: find shops");
            return super.findAllShops();
           
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception reading shops",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    //Method to find shop by name from the database
    @GET
    @Path("shop_name/{shop_name}")
    @Produces({MediaType.APPLICATION_XML})
    public Shop getShopByName(@PathParam("shop_name") String shop_name) {
         
        try {
            
            LOGGER.log(Level.INFO,"ShopRESTful service: find shops");
            return super.findShopName(shop_name);
           
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "ShopRESTful service: Exception reading shops",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
