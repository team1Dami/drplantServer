
package DrPlant.entity;
 

import java.io.Serializable;
import java.util.Set;
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
    @NamedQuery(name="getAllShops",
            query="SELECT p FROM Shop p") 
})
@Entity
@Table(name="Shop",schema="drplant")
@XmlRootElement
public class Shop implements Serializable {
    
    
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_shop;
    /**
     * Atribute type long use has the identifier of the entity
     */
    @NotNull
    private String shop_name;
    /**
     * atribute with the name of the shop
     */
    @NotNull
    private String url;
    /**
     * atribute with the url direction of the shop
     */
    @NotNull
    private String location;
    /**
     * atribute with the location of the shop
     */
    @NotNull
    private float commission;
    /**
     * atribute with the commission we earn off the shop
     */
    @NotNull
    private String email;
    /**
     * atribute with the email of the shop
     */

    
    
    @ManyToMany(mappedBy = "shops", fetch = FetchType.EAGER)
    private Set<Plant> plants;
    //public Set<Plant> plants;
    /**
     * relation with the entity Plant N:M
     */
    
    @OneToMany(mappedBy="shop",fetch = EAGER)
    private Set<Equipment> equipments;
    /**
     * 
     * relation with the entity Equipment 1:N
     */
    
    public Long getId() {
        return id_shop;
    }

    public void setId(Long id) {
        this.id_shop = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId_shop() {
        return id_shop;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_shop != null ? id_shop.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "classes.Shop[ id=" + id_shop + " ]";
    }
    
}
