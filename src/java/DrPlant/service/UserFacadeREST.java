package DrPlant.service;

import DrPlant.encryption.PrivadaEmail;
import DrPlant.entity.User;
import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.ReadException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {

        try {

            super.create(entity);
            LOGGER.log(Level.INFO, "UserRESTful service: create ");

        } catch (CreateException | UserExistException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception creating user",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
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

    /**
     * Method to view if the introduced e-mail is in the database
     *
     * @param email
     * @return
     */
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML})
    public String validateEmail(@PathParam("email") String email) {

        //User u;
        String userEmail = null;
        try {
            userEmail = super.validateEmail(email);
            LOGGER.log(Level.INFO, "UserRESTful service: validate email");

        } catch (ReadException | NoResultException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by email",
                    ex.getMessage());
            throw new NotFoundException(ex);
        }

        //PrivadaEmail priv = null;
        String nuevaContraseña;
        nuevaContraseña = DrPlant.emailService.passwordGenerator.getPassword();
        DrPlant.emailService.EmailService.mandarEmail(nuevaContraseña, userEmail);
        /*byte[] bytes = priv.fileReader("./src/java/DrPlant/encryption/Private");
        String str = new String(bytes);
        nuevaContraseña=priv.cifrarTexto(str, nuevaContraseña);*/
        super.changePassword(nuevaContraseña, userEmail);
        
        
        return userEmail;
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
