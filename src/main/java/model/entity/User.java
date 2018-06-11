package model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
@Entity
@Table(name = "USER_TEST")
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "ACCESS")
    private Character access;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Character getAccess() {
        return access;
    }

    public void setAccess(Character access) {
        this.access = access;
    }
}
