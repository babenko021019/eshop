package info.unproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Item.class)
    private Item item;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(targetEntity = Cart.class)
    private Cart cart;

    public Order(Item item, Integer amount, Cart cart) {
        this.item = item;
        this.amount = amount;
        this.cart = cart;
    }
}
