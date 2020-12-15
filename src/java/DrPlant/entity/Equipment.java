/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.entity;

import DrPlant.enumerations.Use;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * * This entity class encapsulates the data of each equipment.
 * <ul>
 *  <li><strong>equipment_id:</strong> The id of the equipment.</li>
 *  <li><strong>equipment_name:</strong> The name of the equipment</li>
 *  <li><strong>equipment_description:</strong> The description of the equipment</li>
 *  <li><strong>use:</strong> The use you can give to the equipment</li>
 *  <li><strong>price:</strong>The equipment price</li>
 *  <li><strong>image:</strong>An image of the equipment</li>
 * </ul>
 * @author Eneko
 */

 @NamedQuery(
            name = "findEquipmentByName",
            query = "SELECT e FROM Equipment e WHERE e.equipment_name =:equipment_name"
    )
@Entity
@Table(name = "Equipment", schema = "drplant")
@XmlRootElement
@NamedQueries ({
    @NamedQuery(
            name = "findEquipmentByName",
            query = "SELECT e FROM Equipment e WHERE e.equipment_name like :equipment_name ORDER BY e.equipment_name"
    ),
    @NamedQuery(
            name = "findEquipmentByUse",
            query = "SELECT e FROM Equipment e WHERE e.uses = :use_equipment ORDER BY e.id_equipment"
    ),
    
    @NamedQuery(
            name = "findAllEquipment",
            query = "SELECT e FROM Equipment e ORDER BY e.id_equipment"
    ),
        
    @NamedQuery(
            name = "findEquipmentByPrice",
            query = "SELECT e FROM Equipment e WHERE :min_price <= e.price AND :max_price >= e.price ORDER BY e.id_equipment"
    )

})
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_equipment;
    @NotNull
    private String equipment_name;
    @NotNull
    private String equipment_description;
    @NotNull
    private float price;

    
    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private Use uses;

    @ManyToOne
    @JoinColumn(name = "equipment_shop")
    private Shop shop;

    @ManyToMany(mappedBy = "equipments", fetch = FetchType.EAGER)
    private Set<User> usuarios;
    /**
     * 
     * @return ID
     */
    public Long getId_equipment() {
        return id_equipment;
    }
    /**
     * 
     * @param id_equipment 
     */
    public void setId_equipment(Long id_equipment) {
        this.id_equipment = id_equipment;
    }
    /**
     * 
     * @return A list of Users
     */
    public Set<User> getUsuarios() {
        return usuarios;
    }
    /**
     * 
     * @param usuarios 
     */
    public void setUsuarios(Set<User> usuarios) {
        this.usuarios = usuarios;
    }
    /**
     * 
     * @return Shop
     */
    public Shop getShop() {
        return shop;
    }
    /**
     * 
     * @return String name of the equipment
     */
    public String getEquipment_name() {
        return equipment_name;
    }
    /**
     * 
     * @param equipment_name 
     */
    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }
    /**
     * 
     * @return String equipment description
     */
    public String getEquipment_description() {
        return equipment_description;
    }
    /**
     * 
     * @param equipment_description 
     */
    public void setEquipment_description(String equipment_description) {
        this.equipment_description = equipment_description;
    }
    /**
     * 
     * @return Float equipment price
     */
    public float getPrice() {
        return price;
    }
    /**
     * 
     * @param price 
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * 
     * @return The equipment Image
     */
    public byte[] getImagen() {

        return image;
    }
    /**
     * 
     * @param imagen 
     */
    public void setImagen(byte[] imagen) {
      
        this.image = imagen;

    }
    /**
     * 
     * @return Use the use of the equioment
     */
    public Use getUse() {
        return uses;
    }

    /**
     * 
     * @param use 
     */


    public void setUse(Use uses) {
        this.uses = uses;

    }
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_equipment != null ? id_equipment.hashCode() : 0);
        return hash;
    }
    /**
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.id_equipment == null && other.id_equipment != null) || (this.id_equipment != null && !this.id_equipment.equals(other.id_equipment))) {
            return false;
        }
        return true;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "classes.Equipment[ id=" + id_equipment + " ]";
    }

}
