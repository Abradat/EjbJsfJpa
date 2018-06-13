package model.service;

import model.entity.User;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

//import model.entity.User;

@Stateless
public class UserDaoImpl implements UserDao {

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserApp");
    //private EntityManager entityManager;

    @PersistenceContext(name = "UserApp", unitName = "UserApp")
    private EntityManager entityManager;
    @Override
    public void insertUser(User user) {
        User tmpUser = entityManager.find(User.class, user.getUserName());
        //System.out.println(tmpUser + "\n\n\n\n\n\n");
        if(tmpUser == null) {
            entityManager.persist(user);
        }
        else {
            System.out.println("User is already in the database");
        }
        //entityManager.persist(user);

    }

    @Override
    public User readUser(User user) {


        User myUser = entityManager.find(User.class, user.getUserName());
        return myUser;
    }

    @Override
    public void changeUser(User user) {
        User myUser = entityManager.find(User.class, user.getUserName());
        if(myUser != null) {
            if(myUser.getAccess() == 'w') {
                myUser.setAmount(user.getAmount());
            }
            else {
                System.out.println("User does not have read permission");
            }
        }
        else {
            System.out.println("User does not exist");
        }

    }

    @Override
    public List<User> readUsers() {
        List<User> myUsers = entityManager.createNativeQuery("SELECT * FROM TB_USER", User.class).getResultList();
        return myUsers;
    }
}
