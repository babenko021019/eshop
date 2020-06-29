package info.unproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_account")
public class UserAccount {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "user_balance")
    private Integer userBalance;

    public UserAccount(User user, Integer userBalance) {
        this.user = user;
        this.userBalance = userBalance;
    }
}
