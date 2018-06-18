package model.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import org.hibernate.annotations.NamedQuery;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
@Entity
@Table(name = "TB_USER")
@NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT c FROM User c WHERE c.userName = :userName")
public class User implements Serializable{

    public static final String FIND_BY_USERNAME = "FindByUsername";
    public User() {
    }

    public User(String userName, Long amount, Character access) {
        this.userName = userName;
        this.amount = amount;
        this.access = access;
    }

    public User(String userName) {
        this.userName = userName;
    }

    private int userId;
    private String userName;
    private Long amount;
    private Character access;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name = "user_name", nullable = false, unique = true)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "amount", nullable =  false)
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Column(name = "user_access", nullable = false)
    public Character getAccess() {
        return access;
    }
    public void setAccess(Character access) {
        this.access = access;
    }


}
