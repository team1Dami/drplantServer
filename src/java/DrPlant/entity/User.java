/*
 * 
 *
 */
package DrPlant.entity;

import DrPlant.enumerations.UserPrivilege;
import DrPlant.enumerations.Userstatus;
import static com.oracle.jrockit.jfr.ContentType.Timestamp;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This entity class encapsulates the data of each Plague.
 * <ul>
 * <li><strong>id:</strong> The id of the user</li>
 * <li><strong>logIn:</strong> The name of the user in the app</li>
 * <li><strong>email:</strong> The email of the user</li>
 * <li><strong>fullname:</strong> The name and last name of the user</li>
 * <li><strong>status:</strong> It's the plague's type, that can be:
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
/*
<user>
    <logIn>gonza</logIn>
    <email>gon@gmail.com</email>
    <fullname>gonzalo</fullname>
    <status>enable</status>
    <privilage>admin</privilage>
    <passwd></passwd>
    <lastAccess></lastAccess>
    <lastPasswdChange></lastPasswdChange>
</user>

*/
@Entity
@Table(name = "User", schema = "drplant")
@NamedQueries({
    @NamedQuery(name = "findUserByLoginAndPasswd",
            query = "SELECT u FROM User u WHERE u.logIn=:login AND u.passwd=:passwd")
    ,
    @NamedQuery(name = "changeEmail",
            query = "UPDATE User u SET u.email=:email WHERE u.logIn=:logIn")
    ,
    @NamedQuery(name = "changePasswd",
            query = "UPDATE User u SET u.passwd =:passwd WHERE u.logIn=:logIn")
    ,
    @NamedQuery(name="getAllUsers",
            query="SELECT u FROM User u "),
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
    @JoinTable(name = "user_equipment", schema = "drplant", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipment", referencedColumnName = "id_equipment"))
    private Set<Equipment> equipments;

    public String getPrivilage() {
        return privilege.name();
    }

    public void setPrivilage(UserPrivilege privilage) {
        this.privilege = privilage;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(int status) {
        if (status == 1) {
            this.status = Userstatus.ENABLE;
        } else {
            this.status = Userstatus.DISABLE;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = (java.sql.Date) lastAccess;
    }

    public Date getLastPasswdChange() {
        return lastPasswdChange;
    }

    public void setLastPasswdChange(Date lastPasswdChange) {
        this.lastPasswdChange = (java.sql.Date) lastPasswdChange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "DrPlant.Entity.User[ id=" + id + " ]";
    }

}
