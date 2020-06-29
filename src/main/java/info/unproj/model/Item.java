package info.unproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "discount_price")
    private Integer discountPrice;

    public Item(String name, Integer price, Integer discount, Integer discountPrice) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.discountPrice = discountPrice;
    }
}
