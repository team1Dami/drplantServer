package DrPlant.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This entity class encapsulates the data of each Plague.
 * <ul>
 * <li><strong>userId:</strong> The user id that is in the embeddable class
 * </li>
 * <li><strong>scienceName:</strong> The plant id that is in the embeddable
 * class</li>
 * <li><strong>dateWatering:</strong> The last wateringo of the plant</li>
 * </ul>
 *
 * @author Ruben
 */
@Entity
@Table(name = "user_plant", schema = "drplant")
@XmlRootElement
public class UserPlant implements Serializable {
    
    private static final Logger LOGGER
            = Logger.getLogger("DrPlant.entity.UserPlant");
    
    @EmbeddedId
    private UserPlantId id;
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @MapsId("scienceName")
    @ManyToOne(fetch = FetchType.EAGER)
    private Plant plant;

    private Timestamp dateWatering;
    
    /**
     * 
     * @return id the id of the user_plant
     */
    @XmlTransient
    public UserPlantId getId() {
        return id;
    }

    /**
     * 
     * @param id the id to be set
     */
    public void setId(UserPlantId id) {
        this.id = id;
    }
    
    /**
     * 
     * @return user the user that have this plants
     */
    @XmlTransient
    public User getUser() {
        return user;
    }
    
    /**
     * 
     * @param user the user to be set
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * 
     * @return plant the plant of the user
     */
    public Plant getPlant() {
        return plant;
    }
    
    /**
     * 
     * @param plant the plant to be set
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    
    /**
     * 
     * @return dateWatering the dateWatering of the plant
     */
    public Timestamp getDateWatering() {
        return dateWatering;
    }

    /**
     * 
     * @param dateWatering the dateWatering to be set
     */
    public void setDateWatering(Timestamp dateWatering) {
        this.dateWatering = dateWatering;
    }
    
    /**
     * 
     * @return hash the hash of plants and hash of user
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.plant);
        hash = 59 * hash + Objects.hashCode(this.user);
        return hash;
    }
    
    /**
     * 
     * @param obj the object to be analize
     * @return boolean true if the object is correct or false if the object is other
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPlant other = (UserPlant) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.plant, other.plant)) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @return string String userPlant that match with user and plant
     */
    @Override
    public String toString() {
        return "UserPlant{" + "user=" + user + ", plant=" + plant + '}';
    }
}
