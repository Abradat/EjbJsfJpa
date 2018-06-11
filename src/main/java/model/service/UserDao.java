package model.service;

/**
 * Created by Sh_Khorsandi on 6/11/2018.
 */
import model.entity.User;

import javax.ejb.Local;

@Local
public interface UserDao {
    void insertUser(User usr);
    void readUser(User usr);
    void changeUser(User usr);
    //public void sortUser

}
