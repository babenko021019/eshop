package info.unproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items_category")
public class ItemCategory {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Item.class)
    private Item item;

    @Column(name = "name_category")
    private String nameCategory;

    public ItemCategory(Item item, String nameCategory) {
        this.item = item;
        this.nameCategory = nameCategory;
    }
}
