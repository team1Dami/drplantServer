package DrPlant.service;

import DrPlant.entity.User;
import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.ReadException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
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
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {

    private static final Logger LOGGER
            = Logger.getLogger("DrPlant.service.UserFacadeREST");

    @PersistenceContext(unitName = "drplantPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    /**
     * Method to create a new user
     *
     * @param entity
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(User entity) {

        try {

            User user = null;
            user = super.findUserByLogin(entity.getLogin());
           
            create(entity);
            LOGGER.log(Level.INFO, "UserRESTful service: create ");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception user already exists", ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Method to delete a especific user by the id
     *
     * @param entity
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(User entity) {

        try {
            super.edit(entity);
            LOGGER.log(Level.INFO, "UserRESTful service: update ");
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception updating user", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to delete a especific user by the id
     *
     * @param id
     * @throws ReadException
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) throws ReadException {

        try {
            super.remove(super.find(id));
            LOGGER.log(Level.INFO, "UserRESTful service: delete User by id");
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception deleting user by id", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to find a especific user by the id
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public User find(@PathParam("id") Integer id) {

        User user = null;
        try {
            user = super.find(id);
            LOGGER.log(Level.INFO, "UserRESTful service: find User by id");

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by id",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;
    }

    /**
     * Method to list every user in the database
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findAll() {

        try {

            LOGGER.log(Level.INFO, "UserRESTful service: find Users");
            return super.findAllUsers();

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading users",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * Method to find a especific user by the login and the password
     *
     * @param login
     * @param passwd
     * @return
     */
    @GET
    @Path("login/{login}/{passwd}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLoginAndPasswd(@PathParam("login") String login, @PathParam("passwd") String passwd) {
        User user;
        try {
              System.out.println("UserRESTful service: findUserByLoginAndPasswd User");
            LOGGER.log(Level.INFO, "UserRESTful service: findUserByLoginAndPasswd User");
            user = super.findUserByLoginAndPasswd(login, passwd);

            return user;
        } catch (NoResultException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception login or password not correct",
                    ex.getMessage());
            throw new NotAuthorizedException(ex);

        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
