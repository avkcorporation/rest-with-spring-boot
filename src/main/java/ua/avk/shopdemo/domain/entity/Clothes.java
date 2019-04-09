package ua.avk.shopdemo.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.avk.shopdemo.domain.enums.ClothesType;
import ua.avk.shopdemo.domain.enums.ColorType;
import ua.avk.shopdemo.domain.enums.OfficeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@ToString
//@Builder
@Entity
@Table(name = "clothes")
public class Clothes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLOTHES_SEQ")
    @SequenceGenerator(name = "CLOTHES_SEQ", sequenceName = "clothes_id_seq", allocationSize = 1)
    private int id;

    @NotNull
    @Column(name = "size", length = 2)
    private int size;

    @NotNull
    @Column(name = "price", length = 19)
    private int price;

    @NotNull
    @Column(name = "color")
    private ColorType color;

    @NotNull
    @Column(name = "type", length = 2)
    private ClothesType type;

    @Column(name = "description", length = 150)
    private String description;

    @NotNull
    @Column(name = "office_type")
    private OfficeType officeType;

    /**
     * Default constructor
     */
    public Clothes() {
    }

    /**
     * Constructor
     *
     * @param id int
     */
    public Clothes(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }


    /*
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Clothes{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", color='").append(color).append('\'');
        sb.append(", type=").append(type);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
    */

}
