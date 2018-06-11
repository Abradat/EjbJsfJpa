package model.service;

import model.entity.User;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

//import model.entity.User;

@Stateless
public class UserDaoImpl implements UserDao {

    @PersistenceContext(name = "UserApp", unitName = "UserApp")
    private EntityManager entityManager;

    @Override
    public void insertUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void readUser(User user) {

    }

    @Override
    public void changeUser(User user) {

    }
}
