package controller;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import model.entity.User;
import model.service.UserDao;
import java.util.List;

@ManagedBean(name = "userController")
public class UserController {

    private User user = new User();

    //private List<User> users;

    private User resultUser;

    @EJB
    private UserDao userDao;

    public User getUser() {
        return user;
    }


    public List<User> getUsersFromDB() {
        return userDao.readUsers();
    }
    public void setUser(User user) {
        this.user = user;
    }

    //public List<User> getUsers() {
    //    return users;
    //}

    //public void setUsers(List<User> users) {
    //    this.users = users;
   // }

    public User getResultUser() {
        return resultUser;
    }

    public void setResultUser(User resultUser) {
        this.resultUser = resultUser;
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public void readUser(User user) {
        resultUser = userDao.readUser(user);
    }

    public void changeUser(User user) {
        userDao.changeUser(user);
    }

    //public void readUsers() {
    //    users = userDao.readUsers();
    //}


}
