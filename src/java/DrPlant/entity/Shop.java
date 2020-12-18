package DrPlant.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gonzalo
 */

@NamedQueries({
    //get all shops from the database
    @NamedQuery(name = "getAllShops",
            query = "SELECT p FROM Shop p")
    ,
    //get the shop with that name from the databse
    @NamedQuery(name = "getShopByName",
            query = "SELECT p FROM Shop p WHERE p.shop_name = :shop_name")
//.setParameter("shop_name",getShop_name());
})
@Entity
@Table(name = "Shop", schema = "drplant")
@XmlRootElement
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER =
            Logger.getLogger("DrPlant.entity.Shop");
    /**
     * Atribute type long use has the identifier of the entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_shop;

    /**
     * atribute with the name of the shop
     */
    @NotNull
    private String shop_name;

     /**
     * atribute with the url direction of the shop
     */
    @NotNull
    private String url;
   
     /**
     * atribute with the location of the shop
     */
    @NotNull
    private String location;
   
    /**
     * atribute with the commission we earn off the shop
     */
    @NotNull
    private float commission;
    
    /**
     * atribute with the email of the shop
     */
    @NotNull
    private String email;
    
     /**
     * relation with the entity Plant N:M
     */
    @ManyToMany(mappedBy = "shops", fetch = FetchType.EAGER)
    private Set<Plant> plants;
   
    /**
     * relation with the entity Equipment 1:N
     */
    @OneToMany(mappedBy = "shop", fetch = EAGER)
    private Set<Equipment> equipments;

    
    /**
     * 
     * @return the id_shop(long)
     */
    public Long getId() {
        return id_shop;
    }
    /**
     * 
     * @param id set the id_shop
     */
    public void setId(Long id) {
        this.id_shop = id;
    }
    /**
     * 
     * @return the shop_name
     */
    public String getShop_name() {
        return shop_name;
    }
    /**
     * 
     * @param shop_name set shop_name
     */
    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
    /**
     * 
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * 
     * @param url set the url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    /**
     * 
     * @param location set the location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * 
     * @return the commission
     */
    public float getCommission() {
        return commission;
    }
    /**
     * 
     * @param commission  set the commission
     */
    public void setCommission(float commission) {
        this.commission = commission;
    }
    /**
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email set the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return the plants of the shop
     */
    public Set<Plant> getPlants() {
        return plants;
    }
    /**
     * 
     * @return the equipments of the shop
     */
    public Set<Equipment> getEquipments() {
        return equipments;
    }
    /**
     * 
     * @return  hash if the id is not null or return 0 if the
     * id is null
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_shop != null ? id_shop.hashCode() : 0);
        return hash;
    }
    /**
     * 
     * @param object
     * @return return boolean true if the object is shop or return false if the
     * object is not a shop or if the id_shop is null
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) object;
        if ((this.id_shop == null && other.id_shop != null) || (this.id_shop != null && !this.id_shop.equals(other.id_shop))) {
            return false;
        }
        return true;
    }
    /**
     * 
     * @return as String
     */
    @Override
    public String toString() {
        return "classes.Shop[ id=" + id_shop + " ]";
    }

}
