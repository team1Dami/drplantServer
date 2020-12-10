package DrPlant.entity;

import DrPlant.enumerations.PlagueType;
import java.io.Serializable;
import java.util.Set;
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
    @NamedQuery(
            name = "updatePlague",
            query = "UPDATE Plague p SET p.commonName =:commonName, p.description =:description, p.type =:type, p.control =:control, p.remedy =:remedy, p.photo =photo"
    )
    ,
    @NamedQuery(name = "deletePlague",
            query = "DELETE FROM Plague p where p.scienceName=:scienceName"),
})

@XmlRootElement
public class Plague implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plantPlague", schema = "drplant", joinColumns = @JoinColumn(name = "plant_scienceName", referencedColumnName = "scienceName"),
            inverseJoinColumns = @JoinColumn(name = "plague_scienceName", referencedColumnName = "scienceName"))
    
    private Set<Plant> plants;

    /**
     *
     * @return type the PlagueType enum
     */
    public PlagueType getType() {
        return type;
    }

    /**
     *
     * @param type the PlagueType enum to be set
     */
    public void setType(PlagueType type) {
        this.type = type;
    }
    
    /**
     * 
     * @return plants the plants of the Plant class that suffers this plague
     */
    public Set<Plant> getPlants() {
        return plants;
    }

    /**
     * 
     * @param plants the plants that suffers the plague to be set
     */
    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }

    /**
     *
     * @return photo the photo of the plague
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo the photo to be set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     *
     * @return commonName the common name of the plague (if it has)
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     *
     * @param commonName the common name to be set
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     *
     * @return description the description of the plague
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return control the control of the plague
     */
    public String getControl() {
        return control;
    }

    /**
     *
     * @param control the information of the control to be set
     */
    public void setControl(String control) {
        this.control = control;
    }

    /**
     *
     * @return remedy the remedy of the plague
     */
    public String getRemedy() {
        return remedy;
    }

    /**
     *
     * @param remedy the information of the remedy to be set
     */
    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    /**
     *
     * @return scient name the scient name of the plague
     */
    public String getScienceName() {
        return scienceName;
    }

    /**
     *
     * @param scienceName the scient name to be set
     */
    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    /**
     *
     * @return hash if the scienceName is not null or return 0 if the
     * scienceName is null
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scienceName != null ? scienceName.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return boolean true if the object is plague or return false if the
     * object is not a plague or if the scient name is null
     */
    @Override
    public boolean equals(Object object) {
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
     *
     * @return scienceName as String
     */
    @Override
    public String toString() {
        return "DrPlant.Entity.Plague[ id=" + scienceName + " ]";
    }
}
