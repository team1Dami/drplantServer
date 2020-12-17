package DrPlant.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @ManyToOne
    private User user;
    @MapsId("scienceName")
    @ManyToOne
    private Plant plant;

    private Timestamp dateWatering;
    
    @XmlTransient
    public UserPlantId getId() {
        return id;
    }

    public void setId(UserPlantId id) {
        this.id = id;
    }
    
    @XmlTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //@XmlTransient
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Timestamp getDateWatering() {
        return dateWatering;
    }

    public void setDateWatering(Timestamp dateWatering) {
        this.dateWatering = dateWatering;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.plant);
        hash = 59 * hash + Objects.hashCode(this.user);
        return hash;
    }

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

    @Override
    public String toString() {
        return "UserPlant{" + "user=" + user + ", plant=" + plant + '}';
    }
}
