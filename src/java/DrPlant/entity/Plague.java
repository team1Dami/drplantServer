package DrPlant.entity;

import DrPlant.enumerations.PlagueType;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This entity class encapsulates the data of each Plague.
 * <ul>
 *  <li><strong>scienceName:</strong> It's the scientist name of the plague. It's the identifier</li>
 *  <li><strong>commonName:</strong> It's the plague's common name</li>
 *  <li><strong>description:</strong> It's the plague's description</li>
 *  <li><strong>control:</strong> It's the plague's information about the control</li>
 *  <li><strong>remedy:</strong> It's the plague's remedy</li>
 *  <li><strong>type:</strong> It's the plague's type, that can be:
 *      <ul>
 *          <li>light</li>
 *          <li>middle</li>
 *          <li>severe</li>
 *      </ul>
 *  </li>
 *  <li><strong>photo:</strong> It's the plague's photo to help the user in order to recognize the plague</li>
 *  <li><strong>plants:</strong> Collection of objects of the Plant class that contains the data of the plants</li>
 * </ul>
 *
 * @author Saray
 */
@Entity
@Table(name = "Plague", schema = "drplant")
@NamedQueries({
    @NamedQuery(
            name = "findAllPlagues",
            query = "SELECT p FROM Plague p order by scienceName"
    )
    ,
    @NamedQuery(
            name = "findPlagueByCommonName",
            query = "SELECT p FROM Plague p WHERE p.commonName =:commonName"
    )
    ,
    @NamedQuery(
            name = "findPlaguesByType",
            query = "SELECT p FROM Plague p WHERE p.type =:type order by scienceName"
    )
    ,
    @NamedQuery(
            name = "findPlagueById",
            query = "SELECT p FROM Plague p WHERE p.scienceName =:scienceName"
    )
    ,
})

@XmlRootElement
public class Plague implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER =
            Logger.getLogger("DrPlant.entity.Plague");

    @Id
    private String scienceName;

    private String commonName;
    @NotNull
    private String description;
    @NotNull
    private String control;
    @NotNull
    private String remedy;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PlagueType type;
    @Lob
    private byte[] photo;

    // relation ManyToMany with Plant entity
    //@XmlTransient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plantPlague", schema = "drplant", joinColumns = @JoinColumn(name = "plague_scienceName", referencedColumnName = "scienceName"),
            inverseJoinColumns = @JoinColumn(name = "plant_scienceName", referencedColumnName = "scienceName"))
    
    private Set<Plant> plants;

    /**
     * Method to get the enum PlagueType of the plague
     * @return type the PlagueType enum
     */
    public PlagueType getType() {
        LOGGER.log(Level.INFO, "Plague entity: get type");
        return type;
    }

    /**
     * Method to set the enum PlagueType
     * @param type the PlagueType enum to be set
     */
    public void setType(PlagueType type) {
        LOGGER.log(Level.INFO, "Plague entity: set type");
        this.type = type;
    }
    
    /**
     * Method to get the plants that suffers this plague
     * @return plants the plants of the Plant class that suffers this plague
     */
    @XmlTransient
    public Set<Plant> getPlants() {
        LOGGER.log(Level.INFO, "Plague entity: get plants");
        return plants;
    }

    /**
     * Method to set the plants that suffers this plague
     * @param plants the plants that suffers the plague to be set
     */
    public void setPlants(Set<Plant> plants) {
        LOGGER.log(Level.INFO, "Plague entity: set plants");
        this.plants = plants;
    }

    /**
     * Method to get the photo of the plague
     * @return photo the photo of the plague
     */
    public byte[] getPhoto() {
        LOGGER.log(Level.INFO, "Plague entity: get photo");
        return photo;
    }

    /**
     * Method to set the photo of the plague
     * @param photo the photo to be set
     */
    public void setPhoto(byte[] photo) {
        LOGGER.log(Level.INFO, "Plague entity: set photo");
        this.photo = photo;
    }

    /**
     * Method to get the common name of the plague if it has
     * @return commonName the common name of the plague (if it has)
     */
    public String getCommonName() {
        LOGGER.log(Level.INFO, "Plague entity: get common name");
        return commonName;
    }

    /**
     * Method to set the common name of the plague if it has
     * @param commonName the common name to be set
     */
    public void setCommonName(String commonName) {
        LOGGER.log(Level.INFO, "Plague entity: set common name");
        this.commonName = commonName;
    }

    /**
     * Method to get the description of the plague
     * @return description the description of the plague
     */
    public String getDescription() {
        LOGGER.log(Level.INFO, "Plague entity: get description");
        return description;
    }

    /**
     * Method to set the description of the plague
     * @param description the description to be set
     */
    public void setDescription(String description) {
        LOGGER.log(Level.INFO, "Plague entity: set description");
        this.description = description;
    }

    /**
     * Method to get the control of the plague
     * @return control the control of the plague
     */
    public String getControl() {
         LOGGER.log(Level.INFO, "Plague entity: get control");
        return control;
    }

    /**
     * Method to set the control of the plague
     * @param control the information of the control to be set
     */
    public void setControl(String control) {
         LOGGER.log(Level.INFO, "Plague entity: set control");
        this.control = control;
    }

    /**
     * Method to get the remedy of the plague
     * @return remedy the remedy of the plague
     */
    public String getRemedy() {
         LOGGER.log(Level.INFO, "Plague entity: get remedy");
        return remedy;
    }

    /**
     * Method to set the remedy of the plague
     * @param remedy the information of the remedy to be set
     */
    public void setRemedy(String remedy) {
         LOGGER.log(Level.INFO, "Plague entity: set remedy");
        this.remedy = remedy;
    }

    /**
     * Method to get the science name of the plague
     * @return scient name the scient name of the plague
     */
    public String getScienceName() {
         LOGGER.log(Level.INFO, "Plague entity: get science name");
        return scienceName;
    }

    /**
     * Method to set the science name of the plague
     * @param scienceName the scient name to be set
     */
    public void setScienceName(String scienceName) {
         LOGGER.log(Level.INFO, "Plague entity: set science name");
        this.scienceName = scienceName;
    }

    /**
     * Method to obtain the hash
     * @return hash if the scienceName is not null or return 0 if the
     * scienceName is null
     */
    @Override
    public int hashCode() {
         LOGGER.log(Level.INFO, "Plague entity: get hash");
        int hash = 0;
        hash += (scienceName != null ? scienceName.hashCode() : 0);
        return hash;
    }

    /**
     * Method to check the object
     * @param object
     * @return boolean true if the object is plague or return false if the
     * object is not a plague or if the scient name is null
     */
    @Override
    public boolean equals(Object object) {
        LOGGER.log(Level.INFO, "Plague entity: search object plague");
        if (!(object instanceof Plague)) {
            return false;
        }
        Plague other = (Plague) object;
        if ((this.scienceName == null && other.scienceName != null) || (this.scienceName != null && !this.scienceName.equals(other.scienceName))) {
            return false;
        }
        return true;
    }

    /**
     * Method to get the string
     * @return scienceName as String
     */
    @Override
    public String toString() {
         LOGGER.log(Level.INFO, "Plague entity: get id");
        return "DrPlant.Entity.Plague[ id=" + scienceName + " ]";
    }
}
