package DrPlant.service;

import DrPlant.entity.User;
import DrPlant.exceptions.CreateException;
import DrPlant.exceptions.DeleteException;
import DrPlant.exceptions.ReadException;
import DrPlant.exceptions.UpdateException;
import DrPlant.exceptions.UserExistException;
import java.util.Date;
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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rubir, Eneko
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
            User user = super.findUserByLogin(entity.getLogin());
            if (user == null) {
                Date dateToday = new Date(System.currentTimeMillis());
                java.sql.Date date = new java.sql.Date(dateToday.getTime());
                entity.setLastAccess(date);
                entity.setLastPasswdChange(date);

                LOGGER.log(Level.INFO, "UserRESTservice: create: ");
                super.create(entity);

            } else {
                throw new UserExistException();
            }
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "UserRESTful service: Exception creating user: ",
                    ex.getMessage());
            throw new InternalServerErrorException();
        } catch (UserExistException e) {
            LOGGER.log(Level.SEVERE, "User already exist");
            throw new InternalServerErrorException();
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

    /**
     * Method to view if the introduced e-mail is in the database and in case it
     * is in it will change it and send it to the user via e-mail
     *
     * @param email
     */
    @GET
    @Path("email/{email}")
    public void resetPassword(@PathParam("email") String email) {

        User u = null;
        try {
            u = super.validateEmail(email);
            LOGGER.log(Level.INFO, "UserRESTful service: validate email");
            //PrivadaEmail priv = null;
            String nuevaContraseña;
            //Genera una contraseña nueva
            nuevaContraseña = DrPlant.emailService.passwordGenerator.getPassword();
            //Manda la nueva contraseña
            DrPlant.emailService.EmailService.mandarEmail(nuevaContraseña, u.getEmail());
            //Hashea la contraseña nueva de el usuario
            nuevaContraseña = DrPlant.encryption.Hash.cifrarTexto(nuevaContraseña);
            
            //Cambia la contraseña en la base de datos
            super.resetPassword(nuevaContraseña, u.getEmail());

        } catch (ReadException | NoResultException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by email",
                    ex.getMessage());
            throw new NotFoundException(ex);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
