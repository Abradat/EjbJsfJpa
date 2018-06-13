package model.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
@Entity
@Table(name = "TB_USER")
public class User implements Serializable{

    public User() {
    }

    public User(String userName, Integer amount, Character access) {
        this.userName = userName;
        this.amount = amount;
        this.access = access;
    }

    public User(String userName) {
        this.userName = userName;
    }

    private int userId;
    private String userName;
    private Integer amount;
    private Character access;

    @GeneratedValue
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Id
    @Column(name = "user_name", nullable = false, unique = true)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "amount", nullable =  false)
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
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
