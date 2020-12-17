/*
 * 
 *
 */
package DrPlant.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * This entity class encapsulates the ids of the plant and user.
 * <ul>
 * <li><strong>userId: </strong>The id of the user</li>
 * <li><strong>scienceName: </strong>The id of the plant</li>
 * </ul>
 *
 *
 * @author Ruben
 */
@Embeddable
public class UserPlantId implements Serializable {

    private static final Logger LOGGER
            = Logger.getLogger("DrPlant.entity.UserPlantId");
    
    private Integer userId;
    private String scienceName;

    public UserPlantId() {
    }

    public UserPlantId(Integer userId, String scienceName) {
        this.userId = userId;
        this.scienceName = scienceName;
    }
    
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.userId);
        hash = 31 * hash + Objects.hashCode(this.scienceName);
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
        final UserPlantId other = (UserPlantId) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.scienceName, other.scienceName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserPlantId{" + "userId=" + userId + ", scienceName=" + scienceName + '}';
    }

}
