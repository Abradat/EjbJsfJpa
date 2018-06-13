package model.service;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
import model.entity.User;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;

@Local
public interface UserDao {
    void insertUser(User usr);
    User readUser(User usr);
    void changeUser(User usr);
    public List<User>readUsers();
    //public void sortUser

}
