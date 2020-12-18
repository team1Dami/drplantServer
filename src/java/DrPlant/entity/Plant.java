/*
 * 
 *
 */
package DrPlant.entity;

import DrPlant.enumerations.Climate;
import DrPlant.enumerations.PetFriendly;
import DrPlant.enumerations.PlantType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This entity class encapsulates the data of each Plague.
 * <ul>
 * <li><strong>scienceName:</strong> The id of the plant. Name of the plant</li>
 * <li><strong>commonName:</strong> Also the name of the plant known by
 * users</li>
 * <li><strong>description:</strong> The description of the plant</li>
 * <li><strong>cares:</strong> The description how to care the plant</li>
 * <li><strong>climate:</strong> It's the clima of the plant, that can be:
 * <ul>
 * <li>dry</li>
 * <li>wet</li>
 * <li>hot</li>
 * <li>Cold<li </ul> </li>
 * <li><strong>type:</strong> Password of the user</li>
 * <ul>
 * <li>succulent</li>
 * <li>indoor</li>
 * <li>outdoor</li>
 * </ul>
 * <li><strong>petfriendly:</strong> Last time the user login in the app</li>
 * <ul>
 * <li>dog</li>
 * <li>cat</li>
 * <li>both</li>
 * <li>no</li>
 * </ul>
 * <li><strong>image:</strong> Last time the user change the password </li>
 * </ul>
 *
 * @author Ruben
 */
@NamedQueries({
    @NamedQuery(name = "getAllPlants",
            query = "SELECT p FROM Plant p")
    ,
    
    @NamedQuery(name = "getPlantByType",
            query = "SELECT p FROM Plant p WHERE p.plantType =:plantType")
    ,
    
    @NamedQuery(name = "getPlantByPetFriendly",
            query = "SELECT p FROM Plant p WHERE p.petfriendly=:petfriendly")
    ,
    
    @NamedQuery(name = "getPlantByClimate",
            query = "SELECT p FROM Plant p WHERE p.climate=:climate")
    ,
    @NamedQuery(name = "getPlantByTypeAndPetFriendly",
            query = "SELECT p FROM Plant p WHERE p.plantType=:plantType AND p.petfriendly=:petfriendly")
    ,
    
    @NamedQuery(name = "getPlantByTypeAndClimate",
            query = "SELECT p FROM Plant p WHERE p.plantType=:plantType AND p.climate=:climate")
    ,
    
    @NamedQuery(name = "getPlantByPetFriendlyAndClimate",
            query = "SELECT p FROM Plant p WHERE p.petfriendly=:petfriendly AND p.climate=:climate")
    ,
    
    @NamedQuery(name = "getPlantWithAll",
            query = "SELECT p FROM Plant p WHERE p.plantType=:plantType AND p.climate=:climate AND p.petfriendly=:petfriendly")
    ,
    
    @NamedQuery(name = "getPlantByCommonName",
            query = "SELECT p FROM Plant p WHERE p.commonName LIKE :commonName ORDER BY commonName")
})
@Entity
@Table(name = "Plant", schema = "drplant")
@XmlRootElement
public class Plant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private String scienceName;
    @NotNull
    private String commonName;
    private String description;
    private String cares;
    private float wateringFrequence;
    
    @Enumerated(EnumType.STRING)
    private PetFriendly petfriendly;
    
    @Enumerated(EnumType.STRING)
    private PlantType plantType;

    @Enumerated(EnumType.STRING)
    private Climate climate;
    @Lob
    private byte[] image;

    @OneToMany(cascade = ALL, mappedBy = "plant")

    private Set<UserPlant> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plant_shop", schema = "drplant", joinColumns = @JoinColumn(name = "plant_scienceName", referencedColumnName = "scienceName"),
            inverseJoinColumns = @JoinColumn(name = "shop", referencedColumnName = "id_shop"))
    private Set<Shop> shops;

    @XmlTransient
    @ManyToMany(mappedBy = "plants", fetch = FetchType.EAGER)
    private Set<Plague> plagues;

    /**
     *
     * @return list of plagues
     */
    public Set<Plague> getPlagues() {
        return plagues;
    }

    /**
     * Set list of plagues
     * @param plagues 
     */
    public void setPlagues(Set<Plague> plagues) {
        this.plagues = plagues;
    }

    /**
     *
     * @return science name the science name (id) of the plant
     */
    public String getScienceName() {
        return scienceName;
    }

    /**
     * Set the scient name 
     * @param scienceName
     */
    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    /**
     *
     * @return commonName of the plant
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     *
     * @param commonName set common name 
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     *
     * @return description of the plant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return cares of the plant
     */
    public String getCares() {
        return cares;
    }

    /**
     * Set cares
     * @param cares
     */
    public void setCares(String cares) {
        this.cares = cares;
    }

    /**
     *
     * @return wateringFrequence time to water the plant
     */
    public float getWateringFrequence() {
        return wateringFrequence;
    }

    /**
     * Set the watering frequence
     * @param wateringFrequence
     */
    public void setWateringFrequence(float wateringFrequence) {
        this.wateringFrequence = wateringFrequence;
    }

    /**
     *
     * @return petFriendly the PetFrindly enum
     */
    public PetFriendly getPetfriendly() {
        return petfriendly;
    }

    /**
     * Set the petfriendly enum
     * @param petfriendly
     */

    public void setPetfriendly(PetFriendly petfriendly) {
        this.petfriendly = petfriendly;
    }

    /**
     *
     * @return plantType enum
     */
    public PlantType getPlantType() {
        return plantType;
    }

    /**
     * Set plantType enum
     * @param plantType
     */
    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    /**
     *
     * @return climate Climate enum
     */
    public Climate getClimate() {
        return climate;
    }

    /**
     * Set the climate enum
     * @param climate
     */
    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    /**
     *
     * @return image the byte array 
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Set the array of bytes which is the image
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;

    }

    /**
     *
     * @return list of users
     */
    @XmlTransient
    public Set<UserPlant> getUsers() {
        return users;
    }

    /**
     * Set list of users
     * @param users
     */
    public void setUsers(Set<UserPlant> users) {
        this.users = users;
    }

    /**
     *
     * @return list of shops
     */
    @XmlTransient
    public Set<Shop> getShops() {
        return shops;
    }

    /**
     * Set list of shops
     * @param shops
     */
    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

    /**
     *
     * @param object
     * @return boolean true if the object is plague or return false if the
     * object is not a plant or if the scient name is null
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Plant)) {
            return false;
        }
        Plant other = (Plant) object;
        if ((this.scienceName == null && other.scienceName != null)
                || (this.scienceName != null && !this.scienceName.equals(other.scienceName))) {
            return false;
        }
        return true;
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
     * @return scienceName as String
     */
    @Override
    public String toString() {
        return "DrPlant.Entity.Plant[ id=" + scienceName + " ]";
    }

}
