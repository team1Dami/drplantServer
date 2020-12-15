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
import static javax.persistence.CascadeType.ALL;
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
 * <li><strong>logIn:</strong> The name of the user in the app</li>
 * <li><strong>email:</strong> The email of the user</li>
 * <li><strong>fullname:</strong> The name and last name of the user</li>
 * <li><strong>status:</strong> It's the users's status, that can be:
 * <ul>
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
 * @author Ruben
 */
@Entity
@Table(name = "User", schema = "drplant")
@NamedQueries({
    @NamedQuery(name = "login",
            query = "SELECT u FROM User u WHERE u.logIn=:logIn")
    ,
    /*@NamedQuery(name = "login",
            query = "SELECT u FROM User u WHERE u.logIn=:logIn AND u.passwd=:passwd")
    ,*/
    @NamedQuery(name = "changeEmail",
            query = "UPDATE User u SET u.email=:email WHERE u.logIn=:logIn")
    ,
    @NamedQuery(name = "changePasswd",
            query = "UPDATE User u SET u.passwd =:passwd WHERE u.logIn=:logIn")
})
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String logIn;
    private String email;
    private String fullname;
    @Enumerated(EnumType.STRING)
    private Userstatus status;
    @Enumerated(EnumType.STRING)
    private UserPrivilege privilege;
    private String passwd;

    private java.sql.Date lastAccess;

    private java.sql.Date lastPasswdChange;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private Set<UserPlant> users;

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
    public String getPrivilage() {
        return privilege.name();
    }

    /**
     * This method set the privilege of the user
     * @param privilage 
     */
    public void setPrivilage(UserPrivilege privilage) {
        this.privilege = privilage;
    }

    /**
     * 
     * @return the status of the user
     */
    public String getStatus() {
        return status.name();
    }

    /**
     * Set the status of the user
     * @param status 
     */
    public void setStatus(int status) {
        if (status == 1) {
            this.status = Userstatus.ENABLE;
        } else {
            this.status = Userstatus.DISABLE;
        }
    }

    /**
     * 
     * @return the Id of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the id of the user
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return the login name of the user
     */
    public String getLogIn() {
        return logIn;
    }

    /**
     * Set the login name of the user
     * @param logIn 
     */
    public void setLogIn(String logIn) {
        this.logIn = logIn;
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
     * @param fullname 
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 
     * @return the password 
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Set the password 
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
     * @param lastPasswdChange 
     */
    public void setLastPasswdChange(Date lastPasswdChange) {
        this.lastPasswdChange = (java.sql.Date) lastPasswdChange;
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
     * @return boolean true if the object is user or return false if the object is not a user or if the id is null
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
