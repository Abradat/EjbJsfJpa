package model.service;

import model.entity.User;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;


@Stateless
public class UserDaoImpl implements UserDao {


    @PersistenceContext(name = "UserApp", unitName = "UserApp")
    private EntityManager entityManager;
    @Override
    public boolean insertUser(User user) {
        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        query.setParameter("userName", user.getUserName());
        User tmpUser = null;
        try {
            tmpUser = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }

        if(tmpUser == null) {
            entityManager.persist(user);

            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public User readUser(User user) {

        User myUser = null;
        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        query.setParameter("userName", user.getUserName());
        try {
            myUser = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return myUser;
    }

    @Override
    public int changeUser(User user) {
        User myUser = null;
        TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        query.setParameter("userName", user.getUserName());
        try {
            myUser = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }

        if(myUser != null) {
            if(myUser.getAccess() == 'w') {
                myUser.setAmount(user.getAmount());
                return 1; // 1 for success changing
            }
            else {
                return 2; // for wrong permission
            }
        }
        else {
            //System.out.println("User does not exist");
            return 3; // user does not exists
        }

    }

    @Override
    public List<User> readUsers() {
        List<User> myUsers = entityManager.createNativeQuery("SELECT * FROM TB_USER", User.class).getResultList();
        return myUsers;
    }
}
