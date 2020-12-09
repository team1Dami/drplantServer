/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrPlant.entity;

;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eneko
 */
@Entity
@Table(name = "Equipment", schema = "drplant")
@XmlRootElement
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

    public Long getId_equipment() {
        return id_equipment;
    }

    public void setId_equipment(Long id_equipment) {
        this.id_equipment = id_equipment;
    }

    public Set<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<User> usuarios) {
        this.usuarios = usuarios;
    }

    public Shop getShop() {
        return shop;
    }

    public Long getId() {
        return id_equipment;
    }

    public void setId(Long id_equipment) {
        this.id_equipment = id_equipment;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipment_description() {
        return equipment_description;
    }

    public void setEquipment_description(String equipment_description) {
        this.equipment_description = equipment_description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImagen(byte[] imagen) {
        this.image = image;
    }

    public Use getUse() {
        return uses;
    }

    public void setUse(Use uses) {
        this.uses = uses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_equipment != null ? id_equipment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.id_equipment == null && other.id_equipment != null) || (this.id_equipment != null && !this.id_equipment.equals(other.id_equipment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Equipment[ id=" + id_equipment + " ]";
    }

}
