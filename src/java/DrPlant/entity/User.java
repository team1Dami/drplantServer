/*
 * 
 *
 */
package DrPlant.entity;

import DrPlant.enumerations.UserPrivilege;
import DrPlant.enumerations.Userstatus;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This entity class encapsulates the data of each Plague.
 * <ul>
 * <li><strong>id:</strong> The id of the user</li>
 * <li><strong>login:</strong> The name of the user in the app</li>
 * <li><strong>email:</strong> The email of the user</li>
 * <li><strong>fullname:</strong> The name and last name of the user</li>
 * <li><strong>status:</strong> It's the plants's status, that can be:
 <ul>
 * <li>enable</li>
 * <li>disable</li>
 * </ul>
 * </li>
 * <li><strong>passwd:</strong> Password of the user</li>
 * <li><strong>lastAccess:</strong> Last time the user login in the app</li>
 * <li><strong>lastPasswdChange:</strong> Last time the user change the password
 * </li>
 * </ul>
 *
 * @author Ruben, Eneko
 */



@Entity
@Table(name = "User", schema = "drplant")
@NamedQueries({
    @NamedQuery(name = "findUserByLoginAndPasswd",
            query = "SELECT u FROM User u WHERE u.login=:login AND u.passwd=:passwd")
    ,
    @NamedQuery(name = "changeEmail",
            query = "UPDATE User u SET u.email=:email WHERE u.login=:login")
    ,
    @NamedQuery(name = "changePasswd",
            query = "UPDATE User u SET u.passwd =:passwd WHERE u.login=:login")
    ,
    @NamedQuery(name = "validateEmail",
            query = "SELECT u FROM User u WHERE u.email = :email")
    ,
    @NamedQuery(name = "resetPassword",
            query = "UPDATE User u SET u.passwd =:contraseña WHERE u.email=:email")
    ,
    @NamedQuery(name = "getAllUsers",
            query = "SELECT u FROM User u "),
    
    @NamedQuery(name = "findUserByLogin",
            query = "SELECT u FROM User u WHERE u.login=:login"),
        })
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER =
            Logger.getLogger("DrPlant.entity.User");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="login",unique=true)
    private String login;
    private String email;
    private String fullname;
    @Enumerated(EnumType.STRING)
    private Userstatus status;
    @Enumerated(EnumType.STRING)
    private UserPrivilege privilege;
    private String passwd;

    private java.sql.Date lastAccess;

    private java.sql.Date lastPasswdChange;

    @OneToMany(cascade = ALL, mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserPlant> plants;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_equipment", schema = "drplant",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipment",
                    referencedColumnName = "id_equipment"))

    private Set<Equipment> equipments;

    /**
     *
     * @return the privilege of the user
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * This method set the privilege of the user
     *
     * @param privilege
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     *
     * @return the status of the user
     */
    public Userstatus getStatus() {
        return status;
    }

    /**
     * Set the status of the user
     *
     * @param status
     */
    public void setStatus(Userstatus status) {        
        this.status = status;       
    }

    /**
     *
     * @return the Id of the user
     */
   // @XmlTransient  // para indicar que no queremos que se envie esta información de vuelta al cliente
    public Integer getId() {
        return id;
    }

    /**
     * Set the id of the user
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return the login name of the user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the login name of the user
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return get the fullname of the user
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Set the fullname of the user
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return the password
     */
    //@XmlTransient  // para indicar que no queremos que se envie esta información de vuelta al cliente
    public String getPasswd() {
        return passwd;
    }

    /**
     * Set the password
     *
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     *
     * @return last access date of the user
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Set the last access date
     *
     * @param lastAccess
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = (java.sql.Date) lastAccess;
    }

    /**
     *
     * @return last password change date
     */
    public Date getLastPasswdChange() {
        return lastPasswdChange;
    }

    /**
     * Set last password change date
     *
     * @param lastPasswdChange
     */
    public void setLastPasswdChange(Date lastPasswdChange) {
        this.lastPasswdChange = (java.sql.Date) lastPasswdChange;
    }

    /**
     * Get list of plants asociated with the user
     * @return list of plants
     */
    public Set<UserPlant> getPlants() {
        return plants;
    }

    /**
     * Set all user´s plants 
     * @param plants 
     */
    public void setPlants(Set<UserPlant> plants) {
        this.plants = plants;
    }
    /**
    * Get list of equipments
    * @return list of user´s equipment
    */
    public Set<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * Set user´s equipments
     * @param equipments 
     */
    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

    
    /**
     *
     * @return hash if the id is not null or return 0 if the id is null
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return boolean true if the object is user or return false if the object
     * is not a user or if the id is null
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return id as a String
     */
    @Override
    public String toString() {
        return "DrPlant.Entity.User[ id=" + id + " ]";
    }

}
