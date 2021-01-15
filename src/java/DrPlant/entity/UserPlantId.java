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
    
    /**
     * The constructor
     */
    public UserPlantId() {
    }
    
    /**
     * Method to set the values of userId and scienceName
     * @param userId the userId to be set
     * @param scienceName the scienceName of the plant to be set
     */
    public UserPlantId(Integer userId, String scienceName) {
        this.userId = userId;
        this.scienceName = scienceName;
    }
    
    /**
     * Method to obtain the userId
     * @return userId the id of the user
     */
    public Integer getUserId() {
        return userId;
    }
    
    /**
     * Method to set the value of userId
     * @param userId the userId to be set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    /**
     * Method to get the scienceName
     * @return scienceName the scienceName of the plant
     */
    public String getScienceName() {
        return scienceName;
    }

    /**
     * Method to set the value of scienceName of the plant
     * @param scienceName the scienceName of the plant
     */
    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }
    
    /**
     * Method to obtain the hash
     * @return hash the hash of userId and scienceName
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.userId);
        hash = 31 * hash + Objects.hashCode(this.scienceName);
        return hash;
    }
    
    /**
     * Method to check the object
     * @param obj the object to be checked
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
        final UserPlantId other = (UserPlantId) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.scienceName, other.scienceName)) {
            return false;
        }
        return true;
    }
    
    /**
     * Method to get string
     * @return string String of userId that matchs with user and plant
     */
    @Override
    public String toString() {
        return "UserPlantId{" + "userId=" + userId + ", scienceName=" + scienceName + '}';
    }

}
