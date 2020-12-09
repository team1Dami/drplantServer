/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.service;

import DrPlant.entity.Shop;
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
@Path("shop")
public class ShopFacadeREST extends AbstractFacade<Shop> {

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    public ShopFacadeREST() {
        super(Shop.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Shop entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(Shop entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    //find shop by id from the database
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Shop find(@PathParam("id") Long id) {
        return super.find(id);
    }
    //find all shops from the database
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Shop> findAll() {
        return super.findAll();
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
