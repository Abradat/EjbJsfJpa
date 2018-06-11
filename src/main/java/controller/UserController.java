package controller;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.entity.User;
import model.service.UserDao;

@ManagedBean(name = "userController")
public class UserController {

    private User user = new User();

    @EJB
    private UserDao userDao;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public void readUser(User user) {
        userDao.readUser(user);
    }

    public void changeUser(User user) {
        userDao.changeUser(user);
    }
}
