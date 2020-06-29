package info.unproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Column(name = "creation_time")
    private Long creationTime;

    @Column(name = "status")
    private CartStatus status;

    public Cart(UserAccount userAccount, Long creationTime, CartStatus status) {
        this.userAccount = userAccount;
        this.creationTime = creationTime;
        this.status = status;
    }
}
